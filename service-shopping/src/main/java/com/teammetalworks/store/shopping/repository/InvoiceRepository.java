package com.teammetalworks.store.shopping.repository;

import java.util.List;

import com.teammetalworks.store.shopping.entity.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{
    public List<Invoice> findByCustomerId(Long customerId);
    public Invoice findByNumberInvoice(String numberInvoice);
}
