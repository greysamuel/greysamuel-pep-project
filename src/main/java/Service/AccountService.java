package Service;



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
        
        if( account.username != "" && account.password.length() > 4){
            return accountDAO.insertNewUser(account);
        }
    
        return null;
        
    }
    public Account checkLogIn(Account account){
        
            if( account.username != "" && account.password.length() > 4 ){    
        return accountDAO.getLogIn(account);
            }
        return null;    
    }

   
}
