package br.ufjf.chat.client;

import br.ufjf.chat.model.Message;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class SocketClient {
    @Autowired
    private WebSocketClient client;
    
    private StompSession session;
    
    public SocketClient(String ip, int port)
    {
        client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new CustomSessionHandler();
        stompClient.connect("ws://" + ip + ":" + port + "/ws", sessionHandler);
    }
    
    public void send(Message message)
    {
        session.send("all", message);
    }
}
