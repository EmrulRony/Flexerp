package com.solutionia.flexerp.service.uom;

import com.solutionia.flexerp.dao.uom.ProductUOMDao;
import com.solutionia.flexerp.entity.ProductUom;
import com.solutionia.flexerp.entity.ProductUomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductUOMService {
    @Autowired
    private ProductUOMDao productUOMDao;

    @Autowired
    ProductUOMCategoryService productUOMCategoryService;

    @Transactional
    public Integer createNewProductUOM(ProductUom productUom){
        ProductUomCategory productUomCategory = productUOMCategoryService.findProductUOMCategoryByName(productUom.getCategory().getName());
        System.out.println("ProductUOMCategory -->> "+productUomCategory);

        if (productUom != null){
            if (productUom.getDecimals() == null || productUom.getDecimals() <= 0){
                productUom.setDecimals(1);
            }
            if (productUom.getActive() == null){
                productUom.setActive(true);
            }

            if (productUomCategory != null){
                productUom.setCategory(productUomCategory);

            } else {
                System.out.println("Product category does not exist.");
            }
        }
       return productUOMDao.create(productUom);
    }

    @Transactional
    public  ProductUom findActiveProductUOMByName(String productUOMName){
        return productUOMDao.findActiveProductUOMByName(productUOMName);
    }
}
