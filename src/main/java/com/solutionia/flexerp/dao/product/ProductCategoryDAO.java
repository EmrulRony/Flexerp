package com.solutionia.flexerp.dao.product;

import com.solutionia.flexerp.dao.IDataAccessObject;
import com.solutionia.flexerp.entity.ProductCategory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductCategoryDAO implements IDataAccessObject<ProductCategory> {

    @Autowired
    private EntityManager entityManager;

    public Integer create(ProductCategory productCategory){
        Session session = entityManager.unwrap(Session.class);
        session.save(productCategory);
        return productCategory.getId();
    }

    public ProductCategory findActiveProductCategoryByName(String productCategoryName){
        Session session = entityManager.unwrap(Session.class);
        Query<ProductCategory> query = session.createNamedQuery("ProductCategory.findByNameAndActive", ProductCategory.class);
        // "SELECT p FROM ProductCategory p WHERE p.name = :name AND p.active = :active"
        query.setParameter("name",productCategoryName);
        query.setParameter("active",true);
        ProductCategory productCategory = query.getSingleResult();
        return productCategory;
    }

    @Override
    public ProductCategory find(Integer id) {
        // Not implemented yet
        return null;
    }

    @Override
    public List<ProductCategory> listAll() {
        // Not implemented yet
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        // Not implemented yet
        return null;
    }

    @Override
    public Integer update(ProductCategory product) {
        // Not implemented yet
        return null;
    }

}
