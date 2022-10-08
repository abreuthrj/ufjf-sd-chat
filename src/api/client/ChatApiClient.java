package br.ufjf.chat.api.client;

import br.ufjf.chat.exception.ChatException;
import br.ufjf.chat.model.ChatApiResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ltoscano
 */
public class ChatApiClient 
{
    private final WebTarget baseTarget;
    
    public ChatApiClient(String ip, int port)
    {
        this.baseTarget = ClientBuilder.newClient().target(String.format("http://%s:%s/api/chat", ip, port));
    }
    
    public ChatApiResponse sayHello(String name) throws ChatException
    {
        WebTarget sayHelloTarget = baseTarget.path("/sayHello");
        Invocation.Builder invocationBuilder = sayHelloTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        
        if(response.getStatus() != Response.Status.OK.getStatusCode())
        {
            throw new ChatException(
                    String.format("Resposta da API com código de status inesperado: ", 
                            response.getStatus()
                    ));
        }
        
        return response.readEntity(ChatApiResponse.class);
    }
}
