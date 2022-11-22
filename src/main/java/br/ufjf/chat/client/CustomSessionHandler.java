package br.ufjf.chat.client;

import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class CustomSessionHandler implements StompSessionHandler
{
    private StompSession session;
    private User user;
    
    public CustomSessionHandler(User user)
    {
        this.user = user;
    }
    
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message message = (Message) payload;
        
        if(message.getType() == Message.MESSAGE_TYPE.CONNECTION) {
            if(message.getSenderId().equals(user.getId())) {
                System.out.println("You joined the chat!");
                return;
            }
            
            System.out.println(message.getMessage());
            return;
        }
        
        if(message.getType() == Message.MESSAGE_TYPE.MESSAGE) {
            if(message.getSenderId().equals(user.getId())) {
                System.out.println("Sent: " + message.getMessage());
                return;
            }
            
            System.out.println("Received: " + message.getMessage());
        }
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        this.session = session;
        
        session.subscribe("/all", this);
        session.send("/join", new Message(user.getId(), "all", "User " + user.getName() + " joined!", Message.MESSAGE_TYPE.CONNECTION));
        
        System.out.println("Type your message:");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }
    
    public void sendMessage(Message message)
    {
        this.session.send("/send", message);
    }
}