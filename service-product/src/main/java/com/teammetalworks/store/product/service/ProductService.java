package com.teammetalworks.store.product.service;

import java.util.List;

import com.teammetalworks.store.product.entity.Category;
import com.teammetalworks.store.product.entity.Product;
import com.teammetalworks.store.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    @Autowired
    ProductRepository productRepository;

    public ProductService()
    {}

    public List<Product> getProducts()
    {
        return this.productRepository.findAll();
    }

    public Product createProduct(Product product)
    {
        Product new_product = this.productRepository.save(product);
        return new_product;
    }

    public List<Product> getProductByCategory(Category category)
    {
        return this.productRepository.findByCategory(category);
    }

    public Product getProductById(Long id)
    {
        return this.productRepository.findById(id).get();
    }

    public Boolean changeStatusById(Long id, Boolean newStatus)
    {
        Boolean isModify = false;
        var exist = this.productRepository.findById(id).get();

        if(exist != null)
        {
            exist.setStatus(newStatus);
            this.productRepository.save(exist);

            isModify = true;
        }

        return isModify;
    }

}
