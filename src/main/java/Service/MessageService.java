package Service;

import Model.Message;

import java.util.ArrayList;


import DAO.MessageDAO;

public class MessageService extends Message  {
    private MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message) {

        if (message.message_text != "" && message.message_text.length() < 255) {
            return messageDAO.insertNewMessage(message);
        }

        return null;

    }

    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        messages = messageDAO.getAllMessages();
        return messages;

    }

    public Message getMessageById(int message_id) {

        return messageDAO.getMessageId(message_id);

    }

    public ArrayList<Message> getAccountId(int account_id) {
        

        return messageDAO.getAccountId(account_id);
        

    }

    public Message deleteMessageById(int message_id) {
        
        if (messageDAO.getMessageId(message_id) != null) {
            Message message = messageDAO.getMessageId(message_id);
             messageDAO.deleteMessage(message_id);
            
             return message;
        }
        return null;
    }

    public Message updateMessageId(int message_id, String message ){
        if(messageDAO.getMessageId(message_id) != null && message != "" && message.length()< 255){
       Message messages = messageDAO.getMessageId(message_id);
       
       messageDAO.updateMessage(message_id, message);
    
       return messageDAO.getMessageId(message_id);     
       }
       return null;
    }
}
