package com.teammetalworks.store.product.controller;

import java.util.List;

import com.teammetalworks.store.product.dto.MessageResponse;
import com.teammetalworks.store.product.entity.Product;
import com.teammetalworks.store.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired()
    ProductService productService;

    public ProductController()
    {}
    
    @GetMapping("/")
    public ResponseEntity<?> getAllProducts()
    {   
        try
        {
            var products = productService.getProducts();
            log.info("[REQUEST] "+products.toString());
            return new ResponseEntity<Object>(new MessageResponse<List<Product>>("success", products), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new MessageResponse<String>("success", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody Product product)
    {
        log.info("Creating Product : {}", product);
        
        try
        {
            Product customerDB = this.productService.createProduct(product);

            log.info("[Product] "+customerDB.toString());

            return new ResponseEntity<Object>(new MessageResponse<Product>("success", customerDB), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new MessageResponse<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Long id)
    {
        try
        {
            Product product = productService.getProductById(id);
            log.info("[REQUEST] "+product.toString());
            return new ResponseEntity<Object>(new MessageResponse<Product>("success", product), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new MessageResponse<String>("success", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
