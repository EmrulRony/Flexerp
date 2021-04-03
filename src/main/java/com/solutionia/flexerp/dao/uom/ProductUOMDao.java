package com.solutionia.flexerp.dao.uom;

import com.solutionia.flexerp.dao.IDataAccessObject;
import com.solutionia.flexerp.entity.ProductUom;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductUOMDao implements IDataAccessObject<ProductUom> {

    @Autowired
    private EntityManager entityManager;

    public ProductUom findActiveProductUOMByName(String productUOMName){
        Session session = entityManager.unwrap(Session.class);
        Query<ProductUom> query = session.createNamedQuery("ProductUom.findByNameAndActive",ProductUom.class);
        query.setParameter("name",productUOMName);
        query.setParameter("active", true);
        ProductUom productUOM = query.getSingleResult();
        return  productUOM;
    }

    public Integer create(ProductUom productUom){
        Session session = entityManager.unwrap(Session.class);
        session.save(productUom);
        return productUom.getId();
    }

    @Override
    public ProductUom find(Integer id) {
        return null;
    }

    @Override
    public List<ProductUom> listAll() {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer update(ProductUom product) {
        return null;
    }

}
