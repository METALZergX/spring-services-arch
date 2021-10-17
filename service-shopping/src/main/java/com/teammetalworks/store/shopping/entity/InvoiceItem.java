package com.teammetalworks.store.shopping.entity;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import com.teammetalworks.store.shopping.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
public class InvoiceItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Positive(message = "El stock debe ser mayor que cero")
    private int stock;

    private Double price;

    @Column(name = "product_id")
    private Long productId;

    // Transient evita que se registre como atributo pero puede ser devuelto en las peticiones con datos procesados de la entidad
    @Transient
    private Double subTotal;

    @Transient
    private Product product;
    
    public InvoiceItem()
    {
        this.stock = 0;
        this.price = (double) 0;
    }

    // Realiza el calculo de la respuesta para devolverla en subTotal
    public Double getSubTotal()
    {
        if (this.price > 0  && this.stock > 0 )
        {
            return this.stock * this.price;
        }
        else
        {
            return (double) 0;
        }
    }
}
