package br.ufjf.chat.client;

import br.ufjf.chat.model.ChatResponse;
import br.ufjf.chat.model.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ltosc
 */
public class ChatClient
{
    private final URL baseUrl;
    
    public enum HttpMethod
    {
        GET, POST, PUT, DELETE
    }
    
    public ChatClient(String ip, int port) throws MalformedURLException
    {
        this.baseUrl = new URL(String.format("http://%s:%s", ip, port));
    }
    
    private HttpRequest buildHttpRequest(
            String url, HttpMethod method, BodyPublisher bodyPublisher) 
            throws MalformedURLException, URISyntaxException
    {
        URL requestUrl = new URL(baseUrl, url);
        
        return HttpRequest
                .newBuilder()
                .uri(requestUrl.toURI())
                .method(method.name(), bodyPublisher)
                .header("Content-Type", "application/json; charset=utf8")
                .build();
    }
    
    private HttpResponse sendHttpRequest(
            HttpRequest httpRequest, BodyHandler bodyHandler)
            throws IOException, InterruptedException
    {
        return HttpClient.newHttpClient().send(httpRequest, bodyHandler);
    }
    
    public ChatResponse chatUsers() 
            throws MalformedURLException, URISyntaxException, IOException, InterruptedException
    {
        HttpResponse<String> response
                = sendHttpRequest(
                        buildHttpRequest(
                                "/users/list",
                                HttpMethod.GET,
                                HttpRequest.BodyPublishers.noBody()
                        ),
                        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        
        return new Gson().fromJson(response.body(), ChatResponse.class);
    }
    
    public ChatResponse joinChat(User user) 
            throws MalformedURLException, URISyntaxException, IOException, InterruptedException
    {
        Gson gson = new Gson();
        
        HttpResponse<String> response
                = sendHttpRequest(
                        buildHttpRequest(
                                "/users/join",
                                HttpMethod.POST,
                                HttpRequest.BodyPublishers.ofString(
                                        gson.toJson(user)
                                )
                        ),
                        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        
        return gson.fromJson(response.body(), ChatResponse.class);
    }
    
    public ChatResponse leaveChat(String userId) 
            throws MalformedURLException, URISyntaxException, IOException, InterruptedException
    {
        HttpResponse<String> response
                = sendHttpRequest(
                        buildHttpRequest(
                                String.format("/users/leave/%s", userId),
                                HttpMethod.PUT,
                                HttpRequest.BodyPublishers.noBody()
                        ),
                        HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        
        return new Gson().fromJson(response.body(), ChatResponse.class);
    }
}
