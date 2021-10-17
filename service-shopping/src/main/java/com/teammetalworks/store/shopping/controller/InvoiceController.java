package com.teammetalworks.store.shopping.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.HashMap;

import com.teammetalworks.store.shopping.dto.ErrorMessage;
import com.teammetalworks.store.shopping.dto.ResponseMessage;
import com.teammetalworks.store.shopping.entity.Invoice;
import com.teammetalworks.store.shopping.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.*;

@Slf4j
@RestController
@RequestMapping("/invoice")
public class InvoiceController
{
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers()
    {   
        try
        {
            var invoiceList = invoiceService.getInvoices();
            log.info("[REQUEST] "+invoiceList.toString());
            return new ResponseEntity<Object>(new ResponseMessage<List<Invoice>>("success", invoiceList), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new ErrorMessage<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewInvoice(@Valid @RequestBody Invoice invoice, BindingResult result)
    {
        log.info("Creating Invoice : {}", invoice);
        
        try
        {
            if(result.hasErrors())
            {
                var errorList = this.returnEntityErrors(result);

                return new ResponseEntity<Object>(new ErrorMessage<Object>("error", errorList), HttpStatus.ACCEPTED);
            }

            Invoice invoiceRecord = this.invoiceService.createInvoice(invoice);

            System.out.println("[Invoice] "+invoiceRecord.toString());

            return new ResponseEntity<Object>(new ResponseMessage<Invoice>("success", invoiceRecord), HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new ErrorMessage<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private List<HashMap<String,String>> returnEntityErrors(BindingResult errorResult)
    {
        List<HashMap<String,String>> errors = errorResult.getFieldErrors().stream().map(err -> {
            HashMap<String,String> error =  new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;

        }).collect(Collectors.toList());

        return errors;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getInvoceById(@PathVariable Long id)
    {   
        try
        {
            Invoice invoiceData = this.invoiceService.getInvoiceById(id);
            log.info("[REQUEST] "+invoiceData.toString());
            return new ResponseEntity<Object>(new ResponseMessage<Invoice>("success", invoiceData), HttpStatus.OK);
        }
        catch (Exception e)
        {
            log.info("[CATCH-ERROR] "+e.getMessage());
            return new ResponseEntity<Object>(new ErrorMessage<String>("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
