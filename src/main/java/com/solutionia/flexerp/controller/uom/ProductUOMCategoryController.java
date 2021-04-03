package com.solutionia.flexerp.controller.uom;

import com.solutionia.flexerp.entity.ProductUomCategory;
import com.solutionia.flexerp.service.uom.ProductUOMCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductUOMCategoryController {

    @Autowired
    ProductUOMCategoryService productUOMCategoryService;

    @GetMapping("/uom-category/single-uom-category")
    public ProductUomCategory getSingleProdUOMCatag(){
        return new ProductUomCategory();
    }

    @PostMapping("/uom-category")
    public ResponseEntity<?> createNewProductUOMCategory(@RequestBody ProductUomCategory productUomCategory){
        if (productUomCategory != null){
            if (productUomCategory.getName() == null || productUomCategory.getName().equals("")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name can not be empty or null");
            }
            Integer productId = productUOMCategoryService.createNewProductUOMCategory(productUomCategory);
            return new ResponseEntity(productId,HttpStatus.OK);
//            return (tmpProductUomCategory != null) ? new ResponseEntity(tmpProductUomCategory,HttpStatus.OK)
//                    : ResponseEntity.status(HttpStatus.NO_CONTENT).body("Category not found. Please create first");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not create an empty ProductUOMCategory");
        }
    }
}
