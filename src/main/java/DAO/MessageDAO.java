package DAO;

import java.sql.*;
import java.util.ArrayList;


import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    public Message insertNewMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(),
                        message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        ArrayList<Message> messages = new ArrayList<>();
        try {

            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
                
            }
            return messages;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
       
    }

    public ArrayList<Message> getAccountId(int account_id) {
        Connection connection = ConnectionUtil.getConnection();
        ArrayList<Message> messages = new ArrayList<>();
        try {

            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                messages.add(message);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
       
    }

    public Message getMessageId(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
               Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                return message;
            }
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    public Message deleteMessage(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

 

    public Message updateMessage(int message_id, String message) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
