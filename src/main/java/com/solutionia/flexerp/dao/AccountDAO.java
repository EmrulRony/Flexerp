package com.solutionia.flexerp.dao;

import com.solutionia.flexerp.entity.Account;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AccountDAO implements IDataAccessObject<Account> {
    @Autowired
    EntityManager em;

    @Override
    public Account find(Integer id) {
        return null;
    }

    @Override
    public Integer create(Account entity) {
        return null;
    }

    @Override
    public List<Account> listAll() {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer update(Account product) {
        return null;
    }

    public Account getAccountByName(String accountName) {
        System.out.println("Accout name ===> "+ accountName);
        Session session = em.unwrap(Session.class);
        Query<Account> query = session.createNamedQuery("Account.findByName", Account.class);
        query.setParameter("name", accountName);
        return query.getSingleResult();
    }
}
