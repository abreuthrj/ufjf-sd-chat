package br.ufjf.chat.model;

/**
 *
 * @author ltoscano
 */
public class ChatResponse 
{
    private int statusCode;
    
    private String status;
    
    private Object response;

    public int getStatusCode() 
    {
        return statusCode;
    }

    public void setStatusCode(int statusCode) 
    {
        this.statusCode = statusCode;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public Object getResponse()
    {
        return response;
    }

    public void setResponse(Object response)
    {
        this.response = response;
    }
    
}
