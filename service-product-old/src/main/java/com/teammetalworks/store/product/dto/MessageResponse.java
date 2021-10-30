package com.teammetalworks.store.product.dto;

public class MessageResponse<T>
{
    T data;
    private String status;
    
    public MessageResponse()
    {}

    public MessageResponse(String status, T data)
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
