package br.ufjf.chat.api;

import br.ufjf.chat.model.ChatResponse;
import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    @GetMapping(value = "/users/messages/{userId}")
    public ChatResponse getUserChatMessages(@PathVariable("userId") String userId);
    
    @GetMapping(value = "/messages/list")
    public ChatResponse getChatMessages();
    
    @GetMapping(value = "/messages/get/{msgId}")
    public ChatResponse getChatMessage(@PathVariable("msgId") String msgId);
    
    @PostMapping(value = "/messages/send", consumes = "application/json", produces = "application/json")
    public ChatResponse sendChatMessage(@RequestBody Message msg);
    
    @DeleteMapping(value = "/messages/delete/{msgId}")
    public ChatResponse deleteChatMessage(@PathVariable("msgId") String msgId);
}
