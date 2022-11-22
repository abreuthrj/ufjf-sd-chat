package br.ufjf.chat.api;

import br.ufjf.chat.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class SocketApi {
        
	@MessageMapping("/join")
	@SendTo("/all")
	public Message register(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderId());
            return chatMessage;
	}

	@MessageMapping("/send")
	@SendTo("/all")
	public Message sendMessage(@Payload Message chatMessage) {
            return chatMessage;
	}
        
        @MessageMapping("/leave")
	@SendTo("/all")
	public Message leave(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSenderId());		
            return chatMessage;
	}
}
