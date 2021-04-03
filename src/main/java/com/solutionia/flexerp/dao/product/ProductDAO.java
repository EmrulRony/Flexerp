package com.solutionia.flexerp.dao.product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.solutionia.flexerp.dao.IDataAccessObject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solutionia.flexerp.entity.Product;

import java.util.List;

@Repository
public class ProductDAO implements IDataAccessObject<Product> {

	@Autowired
	private EntityManager em;

	public Integer create(Product product) {
		Session session = em.unwrap(Session.class);
		session.save(product);
		return product.getId();
	}

	public Product find(Integer productId){
		Session session = em.unwrap(Session.class);
		Product product = session.find(Product.class, productId);
		return product;
	}

	public List<Product> listAll(){
		Session session = em.unwrap(Session.class);
		Query<Product> query = session.createNamedQuery("Product.findAll", Product.class);
		List<Product> productList= query.getResultList();
		return productList;
	}

	public Integer delete(Integer productId){
		Session session = em.unwrap(Session.class);
		Product product = em.find(Product.class, productId);
		session.delete(product);
		return  product.getId();
	}

	public Integer update(Product product) {
		Session session = em.unwrap(Session.class);
		session.merge(product);
		return product.getId();
	}


	// SELECT p FROM Product  p WHERE p.name = :name AND p.active = :isActive
	public Product findActiveProductByName(String productName){
		Session session = em.unwrap(Session.class);
		Product product = null;
		Query<Product> query = session.createNamedQuery("Product.findActiveByName", Product.class);
		query.setParameter("name", productName);
		query.setParameter("isActive", true);
		try {
			product = query.getSingleResult();
			product.getCategory().setProducts(null);
			product.getUom().setProducts(null);
		} catch (NoResultException ex){
		}
		return product;
	}
}