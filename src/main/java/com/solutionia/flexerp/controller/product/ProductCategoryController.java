package com.solutionia.flexerp.controller.product;

import com.solutionia.flexerp.entity.ProductCategory;
import com.solutionia.flexerp.service.product.ProductCategoryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/product-category/test")
    public ProductCategory controllerTest(){
        return new ProductCategory();
    }

    @PostMapping("/product-category")
    public ResponseEntity<?> createNewProductCategory(@RequestBody ProductCategory productCategory){
        if (productCategory != null){
            Integer productCategoryId = productCategoryService.createNewProductCategory(productCategory);

            if (productCategoryId != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ProductCategoryID", productCategoryId);
                return new ResponseEntity(jsonObject, HttpStatus.OK);
            } else {
                return new ResponseEntity("Could not create product Category",HttpStatus.NO_CONTENT);
            }

        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not create an empty Product Category");
        }
    }
    @GetMapping("/product-category/{productCategoryName}")
     ResponseEntity<?> findActiveProductCategoryByName(@PathVariable("productCategoryName") String productCategoryName){
        ProductCategory productCategory = productCategoryService.findActiveProductCategoryByName(productCategoryName);
        return  new ResponseEntity(productCategory,HttpStatus.OK);
    }
}
