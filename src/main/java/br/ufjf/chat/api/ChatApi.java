package br.ufjf.chat.api;

import br.ufjf.chat.exception.ChatException;
import br.ufjf.chat.model.ChatResponse;
import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ltosc
 */
@RestController
public class ChatApi implements IChatApi
{
    private final Map<String, User> chatUsers;
    
    private final Map<String, Message> chatMessages;
    
    public ChatApi()
    {
        this.chatUsers = new HashMap<>();
        this.chatMessages = new HashMap<>();
    }
    
    private boolean containsUser(String userId)
    {
        return chatUsers.containsKey(userId);
    }
    
    private void addUser(User user)
    {
        if(!containsUser(user.getId()))
        {
            chatUsers.put(user.getId(), user);
        }
    }
    
    private Collection<User> getUsers()
    {
        return chatUsers.values();
    }
    
    private User getUser(String userId) throws ChatException
    {
        if(!containsUser(userId))
        {
            throw new ChatException("Usuário não encontrado");
        }
        
        return chatUsers.get(userId);
    }
    
    private void removeUser(String userId)
    {
        chatUsers.remove(userId);
    }
    
    private boolean containsMessage(String msgId)
    {
        return chatMessages.containsKey(msgId);
    }
    
    private void addMessage(Message msg)
    {
        if(!containsMessage(msg.getId()))
        {
            chatMessages.put(msg.getId(), msg);
        }
    }
    
    private Collection<Message> getMessages()
    {
        return chatMessages.values();
    }
    
    private Message getMessage(String msgId) throws ChatException
    {
        if(!containsMessage(msgId))
        {
            throw new ChatException("Mensagem não encontrada");
        }
        
        return chatMessages.get(msgId);
    }
    
    private void removeMessage(String msgId)
    {
        chatMessages.remove(msgId);
    }
    
    private ChatResponse getChatResponse(int statusCode, String status, Object response)
    {
        ChatResponse chatResponse = new ChatResponse();
        
        chatResponse.setStatusCode(statusCode);
        chatResponse.setStatus(status);
        chatResponse.setResponse(response);
        
        return chatResponse;
    }
    
    @Override
    public ChatResponse chatUsers()
    {
        return getChatResponse(0, "OK", getUsers());
    }

    @Override
    public ChatResponse joinChat(User user)
    {
        addUser(user);
        return getChatResponse(0, "OK", null);
    }

    @Override
    public ChatResponse leaveChat(String userId) 
    {
        removeUser(userId);
        return getChatResponse(0, "OK", null);
    }
    
    @Override
    public ChatResponse getUserChatMessages(String userId)
    {
        ArrayList<Message> messages = new ArrayList(getMessages());
        ArrayList<Message> userMessages = new ArrayList();
        
        for(int i = 0; i < messages.size(); i++)
            if(messages.get(i).getSenderId().equals(userId))
                userMessages.add(messages.get(i));
        
        return getChatResponse(0, "OK", userMessages);
    }

    @Override
    public ChatResponse getChatMessages() 
    {
        return getChatResponse(0, "OK", getMessages());
    }

    @Override
    public ChatResponse getChatMessage(String msgId) 
    {
        try {
           return getChatResponse(0, "OK", getMessage(msgId)); 
        }catch(ChatException exception) {
           return getChatResponse(404, "NOT FOUND", null);
        }
    }

    @Override
    public ChatResponse sendChatMessage(Message msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChatResponse deleteChatMessage(String msgId)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
