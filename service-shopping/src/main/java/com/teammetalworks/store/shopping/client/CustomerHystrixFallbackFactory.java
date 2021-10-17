package com.teammetalworks.store.shopping.client;

import com.teammetalworks.store.shopping.dto.ResponseMessage;
import com.teammetalworks.store.shopping.model.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

// Esta clase se encarga de retornar una entidad de tipo customer cuando el servicio falla, es un plan B
@Component // Se debe añadir este decorador para realizar la inyeccion de dependecias al ser instanciado
public class CustomerHystrixFallbackFactory implements CustomerClient
{
    @Override
    public ResponseEntity<?> findCustomer(Long id)
    {
        Customer customer = Customer.builder()
            .firstName("none")
            .lastName("none")
            .email("none")
            .photoUrl("none").build();
        
        return new ResponseEntity<Object>(new ResponseMessage<Customer>("success", customer), HttpStatus.OK);
    }
}
