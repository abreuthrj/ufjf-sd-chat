package br.ufjf.chat.model;

import java.util.UUID;

/**
 *
 * @author ltoscano
 */
public class User 
{
    private final String id;
    
    private String name;
    
    private String ip;
    
    private int port;
    
    public User()
    {
        this.id = UUID.randomUUID().toString();
        this.name = null;
        this.ip = "127.0.0.1";
        this.port = 8080;
    }
    
    public User(String name, int port)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.ip = "127.0.0.1";
        this.port = port;
    }
    
    public User(String name, String ip, int port)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.ip = ip;
        this.port = port;
    }
    
    public String getId()
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getIP() 
    {
        return ip;
    }

    public void setIP(String ip) 
    {
        this.ip = ip;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }
}
