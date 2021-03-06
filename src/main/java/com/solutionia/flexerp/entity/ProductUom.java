package com.solutionia.flexerp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@Entity
@Table(name = "product_uom")
@NamedQueries({
        @NamedQuery(name = "ProductUom.findAll", query = "SELECT p FROM ProductUom p"),
        @NamedQuery(name = "ProductUom.findById", query = "SELECT p FROM ProductUom p WHERE p.id = :id"),
        @NamedQuery(name = "ProductUom.findByName", query = "SELECT p FROM ProductUom p WHERE p.name = :name"),
        @NamedQuery(name = "ProductUom.findByActive", query = "SELECT p FROM ProductUom p WHERE p.active = :active"),
        @NamedQuery(name = "ProductUom.findByNameAndActive", query = "SELECT p FROM ProductUom p WHERE p.name = :name AND p.active = :active")
})

public class ProductUom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "decimals")
    private Integer decimals;
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "uom")
    private List<Product> products;
    @OneToMany(mappedBy = "uom")
    private List<JournalItem> journalItems;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    @ManyToOne(optional = false)
    private ProductUomCategory category;


    public ProductUom() {
    }


    public ProductUom(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public ProductUomCategory getCategory() {
        return category;
    }

    public void setCategory(ProductUomCategory category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<JournalItem> getJournalItems() {
        return journalItems;
    }

    public void setJournalItemList(List<JournalItem> journalItems) {
        this.journalItems = journalItems;
    }


    @Override
    public String toString() {
        return "--- ProductUom[ id=" + super.getId() + " ] ---";
    }

}
