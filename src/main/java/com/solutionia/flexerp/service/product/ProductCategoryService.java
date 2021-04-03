package com.solutionia.flexerp.service.product;

import com.solutionia.flexerp.dao.product.ProductCategoryDAO;
import com.solutionia.flexerp.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryDAO productCategoryDao;

    @Transactional
    public Integer createNewProductCategory(ProductCategory productCategory){
        if (productCategory.getActive() == null){
            productCategory.setActive(true);
        }
        return productCategoryDao.create(productCategory);
    }

    @Transactional
    public ProductCategory findActiveProductCategoryByName(String productCategoryName){
        if (productCategoryName != null){
            return productCategoryDao.findActiveProductCategoryByName(productCategoryName);
        } else {
            return null;
        }
    }

}
