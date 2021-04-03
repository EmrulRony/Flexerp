package com.solutionia.flexerp.controller.product;

import com.solutionia.flexerp.exceptions.EntityNotFoundException;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.solutionia.flexerp.entity.Product;
import com.solutionia.flexerp.service.product.ProductService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
		if ( product != null){
			Integer productId = productService.createNewProduct(product);
			System.out.println("Product ID ---> "+productId);

			if (productId != null){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("new_product_id", productId);
				return  new ResponseEntity(jsonObject, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Could not create new Product");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not create empty product");
		}
	}

	@PutMapping("/product")
	public ResponseEntity<?> updateProduct (@RequestBody Product product){
		if (product != null){
			Product _product =  productService.findProductById(product.getId());
			if (_product == null){
				return new ResponseEntity("Product not found", HttpStatus.BAD_REQUEST);
			} else {
				Integer productId = productService.updateProduct(product);
				System.out.println("Returned value" + productId);
				if (productId != null){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("updateProductId", productId);
					return new ResponseEntity(jsonObject, HttpStatus.OK);
				} else{
					return new ResponseEntity("Could not update", HttpStatus.NO_CONTENT);
				}
			}
		} else {
			return new ResponseEntity("Can not update an empty product", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/product")
	public ResponseEntity<?> findAllProducts(){
		List<Product> allProducts = productService.findAllProducts();
		if (allProducts.size() == 0){
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no product in the database. Please create one.");
		} else{
			return new ResponseEntity(allProducts, HttpStatus.OK);
		}
	}
	
	@GetMapping("/product/test")
	public Product getSingleProduct() {
		return new Product();
	}

	@GetMapping("/product/findProdByName")
	public ResponseEntity<Object> findProductByName(@RequestParam(name = "name") String name) throws EntityNotFoundException {
		Product product = productService.findProductByName(name);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<?> findProductById(@PathVariable("productId") Integer productId){
		Product product = productService.findProductById(productId);
		if (product != null){
			return  new ResponseEntity(product, HttpStatus.OK);
		} else{
			return new ResponseEntity("Product doesn't exist", HttpStatus.NO_CONTENT );
		}
	}

	@DeleteMapping("/product/{productID}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productID") Integer productId){
		if (productId != null){
			Integer deletedProductId = productService.deleteProductById(productId);
			return deletedProductId != null ? new ResponseEntity(deletedProductId, HttpStatus.OK)
					: new ResponseEntity("Could not deleted the product", HttpStatus.NO_CONTENT);
		} else {
			return  new ResponseEntity("Please provide product Id to be deleted",HttpStatus.BAD_REQUEST);
		}
	}
}
