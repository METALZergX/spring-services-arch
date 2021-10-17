package com.teammetalworks.store.customer.repository;

import java.util.List;

import com.teammetalworks.store.customer.entity.Customer;
import com.teammetalworks.store.customer.entity.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    public List<Customer> findByLastName(String lastName);
    
    public List<Customer> findByRegion(Region region);

}
