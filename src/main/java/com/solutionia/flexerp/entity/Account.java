
package com.solutionia.flexerp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(name = "Account.findByType", query = "SELECT a FROM Account a WHERE a.type = :type"),         
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
    @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
    @NamedQuery(name = "Account.findByActive", query = "SELECT a FROM Account a WHERE a.active = :active")})

public class Account extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    

    @Basic(optional = false)
    @Column(name = "name")
    private String name; 
    @Basic(optional = false)
    @Column(name = "title")
    private String title;    
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;    
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "account")
    private List<JournalItem> journalItems;
    @OneToMany(mappedBy = "account")
    private List<InvoiceLine> invoiceLines;
    @OneToMany(mappedBy = "account")
    private List<InvoiceTax> invoiceTaxes;
    @OneToMany(mappedBy = "account")
    private List<Payment> payments;
    @OneToMany(mappedBy = "account")
    private List<Invoice> invoices;
    
    @OneToMany(mappedBy= "accountReceivable")

    @JsonIgnore
    private List<Partner> partners;
    @OneToMany(mappedBy= "accountPayable")
    @JsonIgnore
    private List<Partner> morePartners;

    public Account() {
    }


    public Account(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<JournalItem> getJournalItems() {
        return journalItems;
    }

    public void setJournalItems(List<JournalItem> journalItems) {
        this.journalItems = journalItems;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public List<InvoiceTax> getInvoiceTaxes() {
        return invoiceTaxes;
    }

    public void setInvoiceTaxes(List<InvoiceTax> invoiceTaxes) {
        this.invoiceTaxes = invoiceTaxes;
    }
    
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

 
    public List<Partner> getPartners() {
		return partners;
	}


	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}


	public List<Partner> getMorePartners() {
		return morePartners;
	}


	public void setMorePartners(List<Partner> morePartners) {
		this.morePartners = morePartners;
	}


	@Override
    public String toString() {
        return "--- Account[ id=" + super.getId() + " ] --- ";
    }
    
}
