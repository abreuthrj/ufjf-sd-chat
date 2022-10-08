package br.ufjf.chat.model;

import java.util.UUID;

/**
 *
 * @author ltoscano
 */
public class Message
{
    private final String id;
    
    private String senderId;
    
    private String message;
    
    public Message(String senderId, String message)
    {
        this.id = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.message = message;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the senderId
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * @param senderId the senderId to set
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
