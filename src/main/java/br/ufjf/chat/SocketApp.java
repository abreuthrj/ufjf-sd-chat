package br.ufjf.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/")
public class SocketApp {
    private ServerSocket server;
    private boolean finishSignal;
    
    public SocketApp(int port) throws IOException
    {
        this.server = new ServerSocket(port);
        System.out.println("Server started on port: " + port);
        
        this.finishSignal = false;
        this.listenToClients();
    }
    
    @OnMessage
    public String receiveMessage(String message) {
        System.out.println("MESSAGE RECEIVED: " + message);
        
        return message;
    }
    
    private void listenToClients()
    {
        ArrayList<Socket> clients = new ArrayList<Socket>();
        
        while(true) 
        {
            if(this.finishSignal) {
                break;
            }
            
            try
            {
                Socket client = this.server.accept();
                clients.add(client);
            } catch(Exception e)
            {
                System.out.println("Failed to accept connection: " + e.getMessage());
            }
        }
    }
    
    public void stop()
    {
        this.finishSignal = true;
    }
}
