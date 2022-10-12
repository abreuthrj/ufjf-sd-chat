package br.ufjf.chat.api;

import br.ufjf.chat.model.ChatResponse;
import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ltosc
 */
public interface IChatApi 
{
    @GetMapping(value = "/users/list", produces = "application/json")
    public ChatResponse chatUsers();
    
    @PostMapping(value = "/users/join", consumes = "application/json", produces = "application/json")
    public ChatResponse joinChat(@RequestBody User user);
    
    @PutMapping(value = "/users/leave/{userId}", produces = "application/json")
    public ChatResponse leaveChat(@PathVariable("userId")String userId);
    
    public ChatResponse getUserChatMessages(@PathVariable("userId") String userId);
    
    public ChatResponse getChatMessages();
    
    public ChatResponse getChatMessage(@PathVariable("msgId") String msgId);
    
    public ChatResponse sendChatMessage(@RequestBody Message msg);
    
    public ChatResponse deleteChatMessage(@PathVariable("msgId") String msgId);
}
