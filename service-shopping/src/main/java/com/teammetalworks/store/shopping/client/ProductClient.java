package com.teammetalworks.store.shopping.client;

import com.teammetalworks.store.shopping.model.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service")
@RequestMapping(value = "/product")
public interface ProductClient
{
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id);

    //@GetMapping(value = "/find/{id}/stock")
    //public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id, @RequestParam(name = "quantity", required = true) Double quantity);
}