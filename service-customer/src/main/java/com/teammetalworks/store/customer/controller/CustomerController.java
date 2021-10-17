package com.teammetalworks.store.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.teammetalworks.store.customer.dto.ErrorMessage;
import com.teammetalworks.store.customer.dto.ResponseMessage;
import com.teammetalworks.store.customer.entity.Customer;
import com.teammetalworks.store.customer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    CustomerService customerService;

    CustomerController()
    {}

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers()
    {   
        try
        {
            var customerList = customerService.getCustomers();
            log.info("[REQUEST] "+customerList.toString());
            return new ResponseEntity<Object>(new ResponseMessage<List<Customer>>("success", customerList), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new ErrorMessage<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, BindingResult result)
    {
        log.info("Creating Customer : {}", customer);
        
        try
        {
            if(result.hasErrors())
            {
                var errorList = this.returnEntityErrors(result);

                return new ResponseEntity<Object>(new ErrorMessage<Object>("error", errorList), HttpStatus.ACCEPTED);
            }

            Customer customerDB = this.customerService.createCustomer(customer);

            log.info("[Customer] "+customerDB.toString());

            return new ResponseEntity<Object>(new ResponseMessage<Customer>("success", customerDB), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new ErrorMessage<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private List<HashMap<String,String>> returnEntityErrors(BindingResult errorResult)
    {
        List<HashMap<String,String>> errors = errorResult.getFieldErrors().stream().map(err -> {
            HashMap<String,String> error =  new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;

        }).collect(Collectors.toList());

        return errors;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findCustomer(@PathVariable Long id)
    {
        try
        {
            var customerList = this.customerService.getCustomerById(id);
            log.info("[REQUEST] "+customerList.toString());
            return new ResponseEntity<Object>(new ResponseMessage<Customer>("success", customerList), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new ErrorMessage<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
