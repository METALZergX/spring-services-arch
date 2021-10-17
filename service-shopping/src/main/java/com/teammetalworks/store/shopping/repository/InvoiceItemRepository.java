package com.teammetalworks.store.shopping.repository;

import com.teammetalworks.store.shopping.entity.InvoiceItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long>
{}
