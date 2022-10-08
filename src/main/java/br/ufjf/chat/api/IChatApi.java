package br.ufjf.chat.api;

import br.ufjf.chat.model.Message;
import br.ufjf.chat.model.User;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ltoscano
 */
@Path("/chat")
public interface IChatApi 
{
    @GET
    @Path("/sayHello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello();
    
    @POST
    @Path("/join")
    @Produces(MediaType.APPLICATION_JSON)
    public Response join(User user);
    
    @POST
    @Path("/leave")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leave(User user);
    
    @GET
    @Path("/messages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMessages();
    
    public Response listMessage(String msgId);
    
    public Response sendMessage(Message msg);
    
    public Response deleteMessage(String msgId);
}
