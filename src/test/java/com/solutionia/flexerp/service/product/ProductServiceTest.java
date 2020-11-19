package com.solutionia.flexerp.service.product;

import com.solutionia.flexerp.dao.product.ProductDAO;
import com.solutionia.flexerp.entity.Product;
import com.solutionia.flexerp.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductDAO productDAO;

    @InjectMocks
    ProductService productService;

    @Test
    void findProductByName() throws EntityNotFoundException {

        Product product = productService.findProductByName("Samsung TV 4K");
        System.out.println(product);
    }
}