package com.solutionia.flexerp;

import com.solutionia.flexerp.dao.uom.ProductUOMDao;
import com.solutionia.flexerp.entity.ProductUom;
import com.solutionia.flexerp.service.uom.ProductUOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlexerpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexerpApplication.class, args);
	}
}
