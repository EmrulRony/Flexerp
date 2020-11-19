package com.solutionia.flexerp.service.sales;

import com.solutionia.flexerp.dao.PartnerDAO;
import com.solutionia.flexerp.dao.TaxDAO;
import com.solutionia.flexerp.dao.product.ProductDAO;
import com.solutionia.flexerp.dao.sales.SaleOrderDAO;
import com.solutionia.flexerp.dao.uom.ProductUOMDao;
import com.solutionia.flexerp.entity.Product;
import com.solutionia.flexerp.entity.SaleOrder;
import com.solutionia.flexerp.entity.SaleOrderLine;
import com.solutionia.flexerp.entity.Tax;
import com.solutionia.flexerp.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SaleOrderService {

    @Autowired
    SaleOrderDAO saleOrderDAO;
    @Autowired
    PartnerDAO partnerDAO;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    MathUtil mathUtil;
    @Autowired
    TaxDAO taxDAO;

    @Transactional
    public Integer create(SaleOrder saleOrder){
        BigDecimal amountUntaxed = new BigDecimal(0d);
        BigDecimal amountTaxed = new BigDecimal(0d);
        saleOrder.setId(null);
        saleOrder.setDate(new Date());
        saleOrder.setActive(true);
        saleOrder.setPartner(partnerDAO.findCustomerByName(saleOrder.getPartner().getName()));
        saleOrder.setState("To Invoice");

        // Prepare invoice line
        List<SaleOrderLine> saleOrderLineList = saleOrder.getSaleOrderLines();

        for (SaleOrderLine saleOrderLine : saleOrderLineList){
            Tax tax = null;
            Product product = productDAO.findActiveProductByName(saleOrderLine.getProduct().getName());

            saleOrderLine.setId(null);
            saleOrderLine.setDate(new Date());
            saleOrderLine.setActive(true);
            saleOrderLine.setInvoiced(false);
            saleOrderLine.setPrice(mathUtil.round(saleOrderLine.getPrice()));
            saleOrderLine.setDiscount(mathUtil.round(saleOrderLine.getDiscount()));
            saleOrderLine.setQuantity(mathUtil.round(saleOrderLine.getQuantity(),product.getUom().getDecimals()));
            saleOrderLine.setUom(product.getUom().getName());

            if (saleOrderLine.getPrice() == 0d) {
                saleOrderLine.setDiscount(0d);
                saleOrderLine.setTax(null);
            }

            if (saleOrderLine.getQuantity() == 0d) {
                saleOrderLine.setQuantity(1d);
            }

            if (saleOrderLine.getDiscount() > 0d) {
                double total = mathUtil.round(saleOrderLine.getQuantity() * saleOrderLine.getPrice());
                double discount = mathUtil.round((saleOrderLine.getPrice() * saleOrderLine.getDiscount())/100);
                saleOrderLine.setSubTotal(total - discount);
            } else {
                saleOrderLine.setSubTotal(saleOrderLine.getQuantity() * saleOrderLine.getPrice());
            }

            amountUntaxed =amountUntaxed.add(BigDecimal.valueOf(saleOrderLine.getSubTotal()));
            if (saleOrderLine.getTax().getName() != null && !saleOrderLine.getTax().getName().isEmpty() ){
                tax = taxDAO.getSalesTaxByName(saleOrderLine.getTax().getName(), "Sale");
                amountTaxed = amountTaxed.add(BigDecimal.valueOf(tax.getAmount() * saleOrderLine.getSubTotal()));
            }

            if (Double.compare(saleOrderLine.getQuantity(), product.getInventory().getQuantityAvailable()) > 0){
                throw new RuntimeException();
            }

            saleOrderLine.setProduct(product);
            saleOrderLine.setTax(tax);
            saleOrderLine.setSaleOrder(saleOrder);
        }

        System.out.println("Amount tax: "+ amountTaxed.doubleValue() + "\n \n" +"Amount total: "+ amountUntaxed.add(amountTaxed).doubleValue());
        saleOrder.setAmountTax(mathUtil.round(amountTaxed.doubleValue()));
        saleOrder.setAmountUntaxed(mathUtil.round(amountUntaxed.doubleValue()));
        saleOrder.setAmountTotal(mathUtil.round(amountUntaxed.add(amountTaxed).doubleValue()));

        return saleOrderDAO.create(saleOrder);
    }
}
