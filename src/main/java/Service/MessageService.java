package Service;
import Model.Message;
import DAO.MessageDAO;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message) {
        int m = message.getMessage_id();
        // int numDigits = String.valueOf(m).length();
         boolean id  = message.equals(m);
         
         id = true;
        if(id = true && message.message_text != "" && message.message_text.length() <= 255){
            return messageDAO.insertNewMessage(message);
        }
    
        return null;
        
    }

}
