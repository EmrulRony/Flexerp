package com.solutionia.flexerp.dao.uom;

import com.solutionia.flexerp.dao.IDataAccessObject;
import com.solutionia.flexerp.entity.Product;
import com.solutionia.flexerp.entity.ProductUomCategory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductUOMCategoryDao implements IDataAccessObject<ProductUomCategory> {
    @Autowired
    private EntityManager entityManager;

    public Integer create(ProductUomCategory productUomCategory){
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(productUomCategory);
        return  productUomCategory.getId();
    }

    public  ProductUomCategory findProductUOMCategoryByName(String productUOMCategoryName){
        Session session = entityManager.unwrap(Session.class);
        Query<ProductUomCategory> query = session.createNamedQuery("ProductUomCategory.findByName",ProductUomCategory.class);
        query.setParameter("name",productUOMCategoryName);
        ProductUomCategory productUomCategory = query.getSingleResult();
        return  productUomCategory;
    }

    @Override
    public ProductUomCategory find(Integer id) {
        return null;
    }


    @Override
    public List<ProductUomCategory> listAll() {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer update(ProductUomCategory product) {
        return null;
    }
}
