package br.ufjf.chat;

import br.ufjf.chat.client.ChatClient;
import br.ufjf.chat.model.ChatResponse;
import br.ufjf.chat.model.User;
import br.ufjf.chat.SocketApp;
import br.ufjf.chat.client.SocketClient;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class ChatApp
{
    private static final Scanner scanner = new Scanner(System.in);
    
    private static int showMenu()
    {
        System.out.println("[1] - API Server");
        System.out.println("[2] - API Client");
        System.out.println("[3] - WebSocket Server");
        System.out.println("[4] - WebSocket Client");
        
        return scanner.nextInt();
    }
    
    private static void runApiServer(String[] args)
    {
        SpringApplication.run(ChatApp.class, args);
    }
    
    public static void runWebSocketServer(String[] args) throws IOException
    {
        SocketApp socket = new SocketApp(8080);
    }
    
    private static void showApiResponse(ChatResponse chatResponse)
    {
        System.out.println();
        System.out.println(
                String.format("%s | %s | %s",
                        chatResponse.getStatusCode(),
                        chatResponse.getStatus(),
                        chatResponse.getResponse()
                )
        );
        System.out.println();
    }
    
    private static void runApiClient()
    {
        try 
        {
            boolean again = true;
            int opMenu;
            
            System.out.println("Informe o nome do usuário do chat: ");
            String username = scanner.next();
            System.out.println("Informe o IP do chat: ");
            String ip = scanner.next();
            System.out.println("Informe a porta do chat: ");
            int port = scanner.nextInt();
            
            User localUser = new User(username, ip, port);
            ChatClient chatClient = new ChatClient(ip, port);
            
            do
            {
                System.out.println();
                System.out.println("[1] - chatUsers");
                System.out.println("[2] - joinChat");
                System.out.println("[3] - leaveChat");
                System.out.println("[3] - leaveChat");
                System.out.println("[4] - getUserChatMessages");
                System.out.println("[5] - getChatMessages");
                System.out.println("[6] - getChatMessage");
                System.out.println("[7] - sendChatMessage");
                System.out.println("[8] - deleteChatMessage");
                System.out.println("[0] - Exit");
                opMenu = scanner.nextInt();
                
                switch(opMenu)
                {
                    case 1: 
                    {
                        try
                        {
                            showApiResponse(
                                    chatClient.chatUsers()
                            ); 
                        }
                        catch (MalformedURLException ex)
                        {
                            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (URISyntaxException | InterruptedException | IOException ex)
                        {
                            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case 2:
                    {
                        try
                        {
                            showApiResponse(
                                    chatClient.joinChat(localUser)
                            );
                        }
                        catch (MalformedURLException ex)
                        {
                            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (URISyntaxException | InterruptedException | IOException ex)
                        {
                            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case 3: 
                    {
                        try
                        {
                            showApiResponse(
                                    chatClient.leaveChat(localUser.getId())
                            );
                        }
                        catch (MalformedURLException ex)
                        {
                            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (URISyntaxException | InterruptedException | IOException ex)
                        {
                            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case 0: 
                    {
                        again = false;
                        break;
                    }
                    default: {
                            System.out.println("Opção inválida");
                            }
                }
            }
            while(again);
        } 
        catch (MalformedURLException ex)
        {
            Logger.getLogger(ChatApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void runWebSocketClient()
    {
        boolean again = true;
        int opMenu;

        System.out.println("Informe o nome do usuário do chat: ");
        String username = scanner.next();
        System.out.println("Informe o IP do chat: ");
        String ip = scanner.next();
        System.out.println("Informe a porta do chat: ");
        int port = scanner.nextInt();

        User localUser = new User(username, ip, port);
        SocketClient socketClient = new SocketClient();

        do
        {
            System.out.println();
            System.out.println("[1] - chatUsers");
            System.out.println("[2] - joinChat");
            System.out.println("[3] - leaveChat");
            System.out.println("[3] - leaveChat");
            System.out.println("[4] - getUserChatMessages");
            System.out.println("[5] - getChatMessages");
            System.out.println("[6] - getChatMessage");
            System.out.println("[7] - sendChatMessage");
            System.out.println("[8] - deleteChatMessage");
            System.out.println("[0] - Exit");
            opMenu = scanner.nextInt();

            try {
            switch(opMenu)
            {
                case 1: 
                {
                    
                }
                case 2:
                {

                }
                case 3: 
                {

                }
                case 4: 
                {

                }
                case 5: 
                {

                }
                case 6: 
                {

                }
                case 7: 
                {
                    String message = scanner.next();
                    socketClient.sendMessage(message);
                }
                case 8: 
                {

                }
                case 0: 
                {
                    again = false;
                    break;
                }
                default: {
                    System.out.println("Opção inválida");
                }
            }
        }catch(IOException exception){
            System.out.println("Error");
        }
        
        } while(again);
    }
    
    public static void main(String[] args) 
    {
        int opMenu = showMenu();
        
        switch(opMenu)
        {
            case 1:
            {
                runApiServer(args);
                break;
            }
            case 2:
            {
                runApiClient();
                break;
            }
            case 3:
            {
                runWebSocketServer(args);
                break;
            }
            case 4:
            {
                runWebSocketClient();
                break;
            }
            default:
            {
                runApiServer(args);
            }
        }
    }
}