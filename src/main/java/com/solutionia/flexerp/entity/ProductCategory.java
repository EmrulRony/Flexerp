package com.solutionia.flexerp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author MOHAMMED BOUNAGA
 * <p>
 * github.com/medbounaga
 */

@Entity
@Table(name = "product_category")
@NamedQueries({
        @NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p"),
        @NamedQuery(name = "ProductCategory.findById", query = "SELECT p FROM ProductCategory p WHERE p.id = :id"),
        @NamedQuery(name = "ProductCategory.findByName", query = "SELECT p FROM ProductCategory p WHERE p.name = :name"),
        @NamedQuery(name = "ProductCategory.findByActive", query = "SELECT p FROM ProductCategory p WHERE p.active = :active"),
        @NamedQuery(name = "ProductCategory.findByNameAndActive", query = "SELECT p FROM ProductCategory p WHERE p.name = :name AND p.active = :active")
})


public class ProductCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(String name, Boolean active) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "--- ProductCategory[ id=" + super.getId() + " ] ---";
    }

}
