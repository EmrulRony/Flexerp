package com.solutionia.flexerp.dao;

import com.solutionia.flexerp.entity.Tax;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TaxDAO implements IDataAccessObject {
    @Autowired
    EntityManager em;

    @Override
    public Object find(Integer id) {
        return null;
    }

    @Override
    public Integer create(Object entity) {
        return null;
    }

    @Override
    public List listAll() {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer update(Object product) {
        return null;
    }

    // Tax.findTaxByName&Type", query = "SELECT t FROM Tax t WHERE t.name = :name AND t.typeTaxUse = :typeTaxUse
    public Tax getSalesTaxByName(String taxName, String type){
        Session session = em.unwrap(Session.class);
        Query<Tax> query = session.createNamedQuery("Tax.findTaxByName&Type",Tax.class);
        query.setParameter("name", taxName);
        query.setParameter("typeTaxUse", type);
        return  query.getSingleResult();
    }
}
