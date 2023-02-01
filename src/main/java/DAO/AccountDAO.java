package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    

    public Account insertNewUser(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO Account (username, password) VALUES (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    // public List<Account> getLogIn(String username , String password){
    //     Connection connection = ConnectionUtil.getConnection();
    //     List<Account> account = new ArrayList<>();
    //     try {

    //         String sql = "SELECT * FROM Account WHERE username =? and password =?;" ;
    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
    //         preparedStatement.setString(1,username);
    //         preparedStatement.setString(2,password);
    //         preparedStatement.executeUpdate();
    //         ResultSet rs = preparedStatement.executeQuery();
    //         while(rs.next()){
    //             Account accounts = new Account(rs.getInt("account_id"), rs.getString("username"),
    //                     rs.getString("password"));
    //             account.add(accounts);
    //         }
    //     }catch(SQLException e){
    //         System.out.println(e.getMessage());
    //     }
    //     return account;
    // }
    public Account getLogIn(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO Account (account_id, username, password) VALUES (?, ?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,account.getAccount_id());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.setString(3,account.getPassword());
            
            preparedStatement.executeUpdate();
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
