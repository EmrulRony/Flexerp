package com.solutionia.flexerp.controller.uom;

import com.solutionia.flexerp.entity.ProductUom;
import com.solutionia.flexerp.entity.ProductUomCategory;
import com.solutionia.flexerp.service.uom.ProductUOMService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductUOMController {

    @Autowired
    private ProductUOMService productUOMService;

    @GetMapping("/product-uom/single-uom")
    public ProductUom getSingleProductUOM(){
        ProductUom uom = new ProductUom();
        uom.setCategory(new ProductUomCategory());
        return  uom;
    }

    @PostMapping("/product-uom")
    public ResponseEntity<?> createNewProductUOM(@RequestBody ProductUom productUom){
        System.out.println(productUom.getName() + " - "+ productUom.getActive() + " - "+productUom.getCategory().getName());
        if (productUom != null){
             Integer productUomId = productUOMService.createNewProductUOM(productUom);
             if (productUomId != null){
                 JSONObject jsonObject = new JSONObject();
                 jsonObject.put("Product_UOM_ID", productUomId);
                 return new ResponseEntity(jsonObject, HttpStatus.OK);
             } else {
                 return new ResponseEntity("Could not create product UOM",HttpStatus.NO_CONTENT);
             }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not create an empty ProductUOM");
        }
    }

    @GetMapping("/product-uom/{activeProduct}")
    public  ProductUom getActiveProductByProductName(@PathVariable("activeProduct") String activeProduct){
        System.out.println(">>>>>>>>>>>> "+activeProduct);
        return productUOMService.findActiveProductUOMByName(activeProduct);
    }
}
