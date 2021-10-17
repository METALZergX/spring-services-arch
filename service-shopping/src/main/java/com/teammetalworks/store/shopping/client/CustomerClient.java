package com.teammetalworks.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
// @RequestMapping("/customer") se elimina ya que se aplica el Circuit-Breaker, ahora esta dentro la interfaz. En caso de fallar el FeignClient lo mandara al Fallback preparado
public interface CustomerClient
{
    @GetMapping("/customer/find/{id}") // @GetMapping("/find/{id}") Se agrega el RequestMapping URL ya que ira directo al tener el circuit-breaker cerrado
    public ResponseEntity<?> findCustomer(@PathVariable Long id);
}