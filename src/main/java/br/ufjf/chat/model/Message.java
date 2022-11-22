package br.ufjf.chat.model;

import java.util.UUID;

/**
 *
 * @author ltoscano
 */
public class Message
{
    public static enum MESSAGE_TYPE {
        UNDEFINED,
        CONNECTION,
        MESSAGE,
    }
    
    private final String id;
    
    private String senderId;
    
    private String senderName;
    
    private String recipientId;
    
    private String message;
    
    private MESSAGE_TYPE type;
    
    public Message()
    {
        this.id = UUID.randomUUID().toString();
        this.senderId = null;
        this.senderName = null;
        this.message = null;
        this.type = Message.MESSAGE_TYPE.UNDEFINED;
    }
    
    public Message(String senderId, String senderName, String recipientId, String message)
    {
        this.id = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.senderName = senderName;
        this.recipientId = recipientId;
        this.message = message;
        this.type = Message.MESSAGE_TYPE.UNDEFINED;
    }
    
    public Message(String senderId, String senderName, String recipientId, String message, MESSAGE_TYPE type)
    {
        this.id = UUID.randomUUID().toString();
        this.senderId = senderId;
        this.senderName = senderName;
        this.recipientId = recipientId;
        this.message = message;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getSenderId()
    {
        return senderId;
    }
    
    public void setSenderId(String senderId) 
    {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    
    public String getRecipientId()
    {
        return recipientId;
    }
    
    public void setRecipientId(String recipientId) 
    {
        this.recipientId = recipientId;
    }

    public String getMessage() 
    {
        return message;
    }
    
    public void setMessage(String message) 
    {
        this.message = message;
    }
    
    public MESSAGE_TYPE getType()
    {
        return this.type;
    }
}
