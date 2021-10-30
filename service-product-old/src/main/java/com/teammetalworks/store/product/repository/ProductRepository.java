package com.teammetalworks.store.product.repository;

import java.util.List;

import com.teammetalworks.store.product.entity.Category;
import com.teammetalworks.store.product.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByCategory(Category category);
}
