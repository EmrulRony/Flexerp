package com.solutionia.flexerp.dao;

import com.solutionia.flexerp.dao.IDataAccessObject;
import com.solutionia.flexerp.entity.Partner;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class PartnerDAO implements IDataAccessObject<Partner> {

    @Autowired
    EntityManager em;

    @Override
    public Partner find(Integer id) {
        Session session = em.unwrap(Session.class);
        Partner partner = session.find(Partner.class, id);
        return partner;
    }

    @Override
    public Integer create(Partner partner) {
        Session session = em.unwrap(Session.class);
        session.save(partner);
        return partner.getId();
    }

    @Override
    public List<Partner> listAll() {
        Session session = em.unwrap(Session.class);
        Query query = session.createNamedQuery("Partner.findAll", Partner.class);
        return query.getResultList();
    }

    // SELECT p FROM Partner p WHERE p.name = :name AND p.customer = :isCustomer
    public Partner findCustomerByName(String customerName){
        Session session = em.unwrap(Session.class);
        Query<Partner> query = session.createNamedQuery("Partner.findCustomerByName", Partner.class);
        query.setParameter("name", customerName);
        query.setParameter("isCustomer", true);
        return  query.getSingleResult();
    }

    // SELECT p FROM Partner p WHERE p.customer = true
    public List<Partner> findAllCustomer(){
        Session session = em.unwrap(Session.class);
        Query<Partner> query = session.createNamedQuery("Partner.findByCustomer", Partner.class);
        return query.getResultList();
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer update(Partner product) {
        return null;
    }

}
