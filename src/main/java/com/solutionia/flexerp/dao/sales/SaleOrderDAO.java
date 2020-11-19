package com.solutionia.flexerp.dao.sales;

import com.solutionia.flexerp.dao.IDataAccessObject;
import com.solutionia.flexerp.entity.SaleOrder;
import com.solutionia.flexerp.util.IDGenerator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SaleOrderDAO implements IDataAccessObject<SaleOrder> {

    @Autowired
    EntityManager em;

    @Autowired
    IDGenerator idGenerator;

    @Override
    public SaleOrder find(Integer id) {
        return null;
    }

    @Override
    public Integer create(SaleOrder saleOrder) {
        Session session = em.unwrap(Session.class);
        session.save(saleOrder);
        saleOrder.setName(idGenerator.generateSaleId(saleOrder.getId()));
        return saleOrder.getId();
    }

    @Override
    public List<SaleOrder> listAll() {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer update(SaleOrder product) {
        return null;
    }
}
