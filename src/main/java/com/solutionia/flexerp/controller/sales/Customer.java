package com.solutionia.flexerp.controller.sales;

import com.solutionia.flexerp.entity.Account;
import com.solutionia.flexerp.entity.Partner;
import com.solutionia.flexerp.service.sales.CustomerService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/partner")
public class Customer {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createPartner(@RequestBody Partner partner){
        if (partner != null){
            partner.setSupplier(false);
            partner.setCustomer(true);
            partner.setIsCompany(false);
            Integer partnerID = customerService.create(partner);
            if (partnerID != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("New partner ID: ", partnerID);
                return  new ResponseEntity(jsonObject, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Could not create new Product");
            }
        } else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not create empty partner");
        }
    }

    @GetMapping("/{partnerID}")
    public ResponseEntity<?> findPartner(@PathVariable("partnerID") Integer partnerID){
        if (partnerID != null){
            Partner partner = customerService.find(partnerID);
            if (partner == null){
                return  new ResponseEntity("User doens't exist", HttpStatus.NO_CONTENT);
            } else{
                return  new ResponseEntity(partner, HttpStatus.OK);
            }
        } else {
            return  new ResponseEntity("Please provide partner Id to be deleted",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-customers")
    public  ResponseEntity<Object> getAllCustomer(){
        List<Partner> customers = customerService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/test")
    public Partner getPartner(){
        System.out.println("Inside test method!!!");
        Partner partner = new Partner();
        partner.setAccountPayable(new Account());
        partner.setAccountReceivable(new Account());
        return partner;
    }
}
