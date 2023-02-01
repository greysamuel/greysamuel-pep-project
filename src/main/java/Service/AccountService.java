package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService  {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    
    
    public Account addAccount(Account account) {
    
    
        if(account.username != "" && account.password.length() >= 4){
            return accountDAO.insertNewUser(account);
        }
        
        return null;
        
    }
    public Account checkLogIn(Account account){
        // int account_id;
        String username = account.getUsername();
        String password = account.getUsername();
        
        if(username.equals(username) && password.equals(password)){
        return accountDAO.getLogIn(account);
        }
        return null;
    }
}
