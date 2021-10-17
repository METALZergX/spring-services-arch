package com.teammetalworks.store.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teammetalworks.store.shopping.client.CustomerClient;
import com.teammetalworks.store.shopping.client.ProductClient;
import com.teammetalworks.store.shopping.dto.ResponseMessage;
import com.teammetalworks.store.shopping.entity.Invoice;
import com.teammetalworks.store.shopping.entity.InvoiceItem;
import com.teammetalworks.store.shopping.model.Customer;
import com.teammetalworks.store.shopping.model.Product;
import com.teammetalworks.store.shopping.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService
{
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;

    public InvoiceService()
    {}

    public List<Invoice> getInvoices()
    {
        return this.invoiceRepository.findAll();
    }

    public Invoice createInvoice(Invoice invoice)
    {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        
        if(invoiceDB !=null)
        {
            return invoiceDB;
        }
        
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        /*invoiceDB.getItems().forEach( invoiceItem -> {
            productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getStock() * -1);
        });*/

        return invoiceDB;
    }

    public Invoice getInvoiceById(Long id)
    {
        Invoice existsInvoice = this.invoiceRepository.getById(id);

        if(existsInvoice != null)
        {
            //Customer customerInfo = this.customerClient.findCustomer(existsInvoice.getCustomerId()).getBody();
            var customerInfo = this.customerClient.findCustomer(existsInvoice.getCustomerId()).getBody();
            //System.out.println(customerInfo);
            ObjectMapper mapper = new ObjectMapper();
            ResponseMessage data = mapper.convertValue(customerInfo, ResponseMessage.class);

            ObjectMapper mapperTwo = new ObjectMapper();
            Customer customer = mapperTwo.convertValue(data.getData(), Customer.class);
            System.out.println("QA. "+data.getData().toString());
            System.out.println("CUSTOMER. "+customer.toString());
            existsInvoice.setCustomer(customer);

            List<InvoiceItem> invoceItemList = existsInvoice.getItems().stream().map(invoiceItem -> {
                var requestInfo = this.productClient.getProduct(invoiceItem.getProductId()).getBody();
                ObjectMapper mapperResp = new ObjectMapper();
                ResponseMessage dataProd = mapperResp.convertValue(requestInfo, ResponseMessage.class);

                ObjectMapper _mapper = new ObjectMapper();
                Product productInfo = _mapper.convertValue(dataProd.getData(), Product.class);

                System.out.println("[INPRINT] "+productInfo.toString());
                invoiceItem.setProduct(productInfo);

                return invoiceItem;
            }).collect(Collectors.toList());
            existsInvoice.setItems(invoceItemList);
        }

        return existsInvoice;
    }

}
