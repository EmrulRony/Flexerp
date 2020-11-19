package com.solutionia.flexerp.service.uom;

import com.solutionia.flexerp.dao.uom.ProductUOMCategoryDao;
import com.solutionia.flexerp.entity.ProductUomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductUOMCategoryService {

    @Autowired
    ProductUOMCategoryDao productUOMCategoryDao;

    @Transactional
    public  Integer createNewProductUOMCategory(ProductUomCategory productUomCategory){
        if (productUomCategory == null){
            return  null;
        } else {
            productUomCategory.setUoms(null);
            return productUOMCategoryDao.create(productUomCategory);
        }
    }

    @Transactional
    public  ProductUomCategory findProductUOMCategoryByName(String productUomCategoryName){
        return  productUOMCategoryDao.findProductUOMCategoryByName(productUomCategoryName);
    }
}
