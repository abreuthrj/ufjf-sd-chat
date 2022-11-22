package br.ufjf.chat.client;

import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class SocketClient {
    @Autowired
    private WebSocketClient client;
    
    private CustomSessionHandler session;
    
    public SocketClient(User user)
    {
        client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        session = new CustomSessionHandler(user);
        stompClient.connect("ws://" + user.getIP() + ":" + user.getPort() + "/ws", (StompSessionHandler) session);
    }
    
    public void send(Message message)
    {        
        if(session == null) {
            System.out.println("You are not connected");
            return;
        }
        
        session.sendMessage(message);
    }
}
