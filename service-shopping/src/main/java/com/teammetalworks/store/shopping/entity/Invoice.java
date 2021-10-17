package com.teammetalworks.store.shopping.entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teammetalworks.store.shopping.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    private String state;

    @Transient
    private Customer customer;

    public Invoice()
    {
        items = new ArrayList<>();
    }

    // Le dice que inserte la fecha en memoria antes de llegar a la base de datos
    @PrePersist
    public void prePersist()
    {
        this.createdAt = new Date();
    }
}