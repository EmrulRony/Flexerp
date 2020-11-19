package com.solutionia.flexerp.service.sales;

import com.solutionia.flexerp.dao.AccountDAO;
import com.solutionia.flexerp.dao.PartnerDAO;
import com.solutionia.flexerp.entity.Account;
import com.solutionia.flexerp.entity.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    PartnerDAO partnerDAO;

    @Autowired
    AccountDAO accountDAO;

    @Transactional
    public Integer create(Partner partner){
        partner.setId(null);
        partner.setCreateDate(new Date());
        partner.setCredit(0d);
        partner.setDebit(0d);
        partner.setActive(true);
        Account accountPayable = accountDAO.getAccountByName(partner.getAccountPayable().getName());
        Account accountReceivable = accountDAO.getAccountByName(partner.getAccountReceivable().getName());
        partner.setAccountPayable(accountPayable);
        partner.setAccountReceivable(accountReceivable);
        return  partnerDAO.create(partner);
    }

    @Transactional
    public List<Partner> findAllCustomer(){
        return partnerDAO.findAllCustomer();
    }

    @Transactional
    public Partner find(Integer id){
        return partnerDAO.find(id);
    }

}
