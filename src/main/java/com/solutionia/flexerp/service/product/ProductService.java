package com.solutionia.flexerp.service.product;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.solutionia.flexerp.entity.ProductCategory;
import com.solutionia.flexerp.entity.ProductUom;
import com.solutionia.flexerp.exceptions.EntityNotFoundException;
import com.solutionia.flexerp.service.uom.ProductUOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutionia.flexerp.dao.product.ProductDAO;
import com.solutionia.flexerp.entity.Product;

import java.util.List;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDao;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductUOMService productUOMService;
	
	@Transactional
	public Product find(int productId) {
		return productDao.find(productId);
	}
	
	@Transactional
	public Integer createNewProduct(Product product) {
		processProduct(product);

		return productDao.create(product);
	}

	@Transactional
	public List<Product> findAllProducts(){
		List<Product> allProducts = productDao.listAll();
		allProducts.forEach(product -> {
			product.getUom().setProducts(null);
			product.getCategory().setProducts(null);

		});
		return allProducts;
	}

	@Transactional
	public Product findProductById(Integer productId){
		Product product = null;
		try{
			product = productDao.find(productId);
			product.getCategory().setProducts(null);
			product.getUom().setProducts(null);
			return  product;
		} catch (NullPointerException ex){
			ex.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
			System.out.println();
		}
		return product;
	}

	@Transactional
	public  Product findProductByName(String name) throws EntityNotFoundException {
			Product product = productDao.findActiveProductByName(name);
			if (product == null){
				throw new  EntityNotFoundException("No product found by this name");
			}
			return product;
	}

	@Transactional
	public  Integer deleteProductById(Integer productId){
		Integer _productId = null;
		try{
			return productDao.delete(productId);
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return  _productId;
	}

	@Transactional
	public Integer updateProduct(Product product){
		Integer productId = null;

		processProduct(product);

		try {
			productId = productDao.update(product);
			return productId;
		}
		catch (Exception ex){
			System.out.println("Catched");
			ex.printStackTrace();
		}
		System.out.println("After catching exception...");
		return  null;
	}

	private void processProduct(Product product) {
		if (product.getDescription() == null || product.getDescription().isEmpty()){
			product.setDescription(product.getName());
		}

		ProductCategory productCategory = productCategoryService.findActiveProductCategoryByName(product.getCategory().getName());
		ProductUom productUom = productUOMService.findActiveProductUOMByName(product.getUom().getName());

		product.setCategory(productCategory);
		product.setUom(productUom);

		product.getInventory().setUnitCost(product.getPurchasePrice());
		product.getInventory().setActive(true);
		product.getInventory().setTotalCost((product.getInventory().getUnitCost() * product.getInventory().getQuantityOnHand()));
		product.getInventory().setProduct(product);
	}
}
