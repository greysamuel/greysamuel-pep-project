package Service;
import Model.Message;

import java.util.List;

import DAO.MessageDAO;

public class MessageService extends Message {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message) {
        // int m = message.getMessage_id();
        // int numDigits = String.valueOf(m).length();
         
         
         
        if(message.message_text != "" && message.message_text.length() < 255){
            return messageDAO.insertNewMessage(message);
        }
    
        return null;
        
    }

    public List <Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    // public List <Message> getMessagebyId(int message_id){
    //     if(message_id > 0){
    //     return messageDAO.getMessageById(message_id);
    //     }
    //     return null;
    // }
        public Message getMessagebyId(int message_id){
            
            return messageDAO.getMessageById(message_id);
        }
}
