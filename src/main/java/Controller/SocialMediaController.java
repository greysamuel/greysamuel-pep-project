package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import Model.Account;
import Service.AccountService;

import Model.Message;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages/", this::getAllMessageHandler);
        app.get("messages/{message_id}", this::getMessageByIdHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException
     */
    
    private void postAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount!=null){
            context.json(mapper.writeValueAsString(addedAccount));
            context.status(200);
        }else{
            context.status(400);
        }
        
    }
    
    private void postLoginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account loggedAccount = accountService.checkLogIn(account);
        if(loggedAccount!=null){
            context.json(mapper.writeValueAsString(loggedAccount));
            context.status(200);
        }else{
            context.status(401);
        }
        
    }
    

    private void postMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            context.json(mapper.writeValueAsString(addedMessage));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void getAllMessageHandler(Context context) throws JsonProcessingException {
        // ObjectMapper mapper = new ObjectMapper();
        // TypeReference<List<Message>> message = new TypeReference<List<Message>>() {};
        // List<Message> messages = mapper.readValue(context.body(), messages);
        List<Message> messageList= messageService.getAllMessages();
        if(messageList != null){
            context.json(messageList);
            context.status(200);
        }else{
            context.json(messageList);
        }
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException {
        
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        
        
        context.json(messageService.getMessagebyId(message_id));
        
        context.status(200);
    }

}