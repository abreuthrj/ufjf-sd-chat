package br.ufjf.chat;

import br.ufjf.chat.api.client.ChatApiClient;
import br.ufjf.chat.exception.ChatException;
import br.ufjf.chat.model.ChatApiResponse;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ltoscano
 */
public class Chat {

    private static int showMenu() 
    {
        System.out.println("1 - Teste de API");
        System.out.println("0 - Sair");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void main(String[] args) 
    {
        ChatApiClient chatApi = new ChatApiClient("127.0.0.1", 8080);
        boolean again = true;
        int op;

        do 
        {
            try 
            {
                op = showMenu();

                switch (op) 
                {
                    case 1: 
                    {
                        try 
                        {
                            ChatApiResponse apiResponse = chatApi.sayHello("Luis");
                            System.out.println(apiResponse.getResponse());
                        } 
                        catch (ChatException ex) 
                        {
                            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    default: 
                        {
                        again = false;
                    }
                }
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (again);
    }
}