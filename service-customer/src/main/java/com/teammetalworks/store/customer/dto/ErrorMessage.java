package com.teammetalworks.store.customer.dto;

public class ErrorMessage<T>
{
    T message;
    private String status;
    
    public ErrorMessage()
    {}

    public ErrorMessage(String status, T message)
    {
        this.status = status;
        this.message = message;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public T getMessage() 
    {
        return message;
    }

    public void setMessage(T message) 
    {
        this.message = message;
    }
}