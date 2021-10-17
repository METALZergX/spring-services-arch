package com.teammetalworks.store.shopping.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product
{
    private Long id;
    private String name;
    private String description;
    private int stock;
    private Double price;
    private boolean status;
    private Date createdAt;
    private Date updatedAt;
    private Category category;
}
