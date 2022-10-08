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
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * @return the ip
     */
    public String getIP() 
    {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIP(String ip) 
    {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort()
    {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port)
    {
        this.port = port;
    }
}
