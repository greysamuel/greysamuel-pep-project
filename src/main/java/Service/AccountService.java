package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService extends Account  {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    
    
    public Account addAccount(Account account) {
        // int account_id = account.getAccount_id();
        if( account.username != "" && account.password.length() > 4){
            return accountDAO.insertNewUser(account);
        }
    
        return null;
        
    }
    public Account checkLogIn(Account account){
        // int account_id = account.getAccount_id();
            if( account.username != "" && account.password.length() > 4 ){    
        return accountDAO.getLogIn(account);
            }
        return null;    
    }

    // public List <Account> checkLogIn(){
    //     if( account_id != 0){
    //     return accountDAO.getLogIn();
    //     }
    //     return null;
    // }
}
//account_id != 0 &&