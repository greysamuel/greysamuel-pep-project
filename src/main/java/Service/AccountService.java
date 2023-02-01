package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    
    
    public Account addAccount(Account account) {
        String password = account.getPassword();
        String username = account.getUsername();
        if(password.length() >= 4 && username != null){
            return accountDAO.insertNewUser(account); 
        }
        return null;
        
    }
}
