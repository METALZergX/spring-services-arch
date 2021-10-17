package com.teammetalworks.store.shopping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ResponseMessage<T>
{
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    T data;
    private String status;
    
    public ResponseMessage()
    {}

    public ResponseMessage(String status, T data)
    {
        this.status = status;
        this.data = data;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public T getData() 
    {
        return data;
    }

    public void setData(T data) 
    {
        this.data = data;
    }
}
