package com.teammetalworks.store.customer.dto;

public class ResponseMessage<T>
{
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
