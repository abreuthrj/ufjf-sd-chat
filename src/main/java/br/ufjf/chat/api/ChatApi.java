package br.ufjf.chat.api;

import br.ufjf.chat.exception.ChatException;
import br.ufjf.chat.model.ChatApiResponse;
import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

/**
 *
 * @author ltoscano
 */
public class ChatApi implements IChatApi
{
    private final Map<String, User> users;
    
    private final Map<String, Message> messages;
    
    public ChatApi()
    {
        this.users = new HashMap<>();
        this.messages = new HashMap<>();
    }
    
    private boolean containsUser(String userId)
    {
        return users.containsKey(userId);
    }
    
    public void addUser(User user)
    {
        if(!containsUser(user.getId()))
        {
            users.put(user.getId(), user);
        }
    }
    
    public User getUser(String userId) throws ChatException
    {
        if(!containsUser(userId))
        {
            throw new ChatException("Usuário não encontrado");
        }
        
        return users.get(userId);
    }
    
    public void removeUser(User user)
    {
        users.remove(user.getId());
    }
    
    private boolean containsMessage(String msgId)
    {
        return messages.containsKey(msgId);
    }
    
    public void addMessage(Message msg)
    {
        if(!containsUser(msg.getId()))
        {
            messages.put(msg.getId(), msg);
        }
    }
    
    public Message getMessage(String msgId) throws ChatException
    {
        if(!containsMessage(msgId))
        {
            throw new ChatException("Mensagem não encontrada");
        }
        
        return messages.get(msgId);
    }
    
    public void removeMessage(Message msg)
    {
        messages.remove(msg.getId());
    }
    
    @Override
    public Response sayHello() 
    {
        ChatApiResponse apiResponse = new ChatApiResponse();
        
        apiResponse.setStatusCode(0);
        apiResponse.setStatus("OK");
        apiResponse.setResponse("Hello world!");
        
        return Response
                .ok(apiResponse)
                .build();
    }

    @Override
    public Response join(User user) 
    {
        ChatApiResponse apiResponse = new ChatApiResponse();
        
        this.addUser(user);
        
        apiResponse.setStatusCode(200);
        apiResponse.setStatus("OK");
        apiResponse.setResponse("User joined");
        
        return Response
                .ok(apiResponse)
                .build();
    }

    @Override
    public Response leave(User user) 
    {
        ChatApiResponse apiResponse = new ChatApiResponse();
        
        this.removeUser(user);
        
        apiResponse.setStatusCode(200);
        apiResponse.setStatus("OK");
        apiResponse.setResponse("User joined");
        
        return Response
                .ok(apiResponse)
                .build();
    }
    
    @Override
    public Response listMessages() 
    {
        ChatApiResponse apiResponse = new ChatApiResponse();
        
        apiResponse.setStatusCode(200);
        apiResponse.setStatus("OK");
        apiResponse.setResponse(this.messages);
        
        return Response
                .ok(apiResponse)
                .build();
    }

    @Override
    public Response listMessage(String msgId) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response sendMessage(Message msg) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Response deleteMessage(String msgId) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
