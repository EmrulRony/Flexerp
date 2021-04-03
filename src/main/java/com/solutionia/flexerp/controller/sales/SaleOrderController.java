package com.solutionia.flexerp.controller.sales;

import com.solutionia.flexerp.entity.*;
import com.solutionia.flexerp.service.sales.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleOrderController {

    @Autowired
    SaleOrderService saleOrderService;

    @GetMapping("/test")
    public SaleOrder test(){
        SaleOrder saleOrder =  new SaleOrder();
        saleOrder.setPartner(new Partner());
        SaleOrderLine saleOrderLine = new SaleOrderLine();
        saleOrderLine.setTax(new Tax());
        saleOrderLine.setProduct(new Product());
        List<SaleOrderLine> saleOrderLineList = new ArrayList<>();
        saleOrderLineList.add(saleOrderLine);
        saleOrder.setSaleOrderLines(saleOrderLineList);
        return saleOrder;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody SaleOrder saleOrder) {
        Integer orderId = saleOrderService.create(saleOrder);
        return new ResponseEntity("OrderId: " +orderId, HttpStatus.OK);
    }
}
