package com.teammetalworks.store.customer.service;

import java.util.List;

import com.teammetalworks.store.customer.entity.Customer;
import com.teammetalworks.store.customer.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;
    
    public CustomerService()
    {}

    public List<Customer> getCustomers()
    {
        return this.customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer)
    {
        return this.customerRepository.save(customer);
    }

    public Customer getCustomerById(Long customerId)
    {
        return this.customerRepository.findById(customerId).get();
    }

}
