package br.ufjf.chat.client;

import br.ufjf.chat.model.Message;
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
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message message = (Message) payload;
        System.out.println(message.getMessage());
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/all", this);
        session.send("/all/join", "User joined");
        
        System.out.println("CONNECTED");
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
}