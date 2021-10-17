package com.teammetalworks.store.product;

import java.util.Date;
import java.util.List;

import com.teammetalworks.store.product.entity.Category;
import com.teammetalworks.store.product.entity.Product;
import com.teammetalworks.store.product.service.ProductService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTest
{
    @Autowired()
    public ProductService productService;

    @Test
    public void whenFindByCategory()
    {
        Product product = Product.builder()
            .name("Producto Test")
            .category(Category.builder().id(1L).build())
            .description("")
            .status(true)
            .price(15.00)
            .stock(1)
            .createdAt(new Date())
            .updatedAt(null)
            .build();

        this.productService.createProduct(product);

        List<Product> products = this.productService.getProductByCategory(product.getCategory());

        System.out.println(products.size());

        Assertions.assertEquals(products.size(), 1);
    }
}
