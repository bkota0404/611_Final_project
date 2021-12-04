import java.util.List;

/**
 * 
 * @author Qingyuan Zhang
 *
 */
public class AccountCreation {
	DBManager dbManager;
	Customer loginUser;
	
	public AccountCreation(DBManager dbManager, Customer user) {
		this.dbManager = dbManager;
		this.loginUser = user;
	}
	
	public boolean createCheckingAccount(String currency, double initialBalance) {
        if (initialBalance < BankConstants.getMinOpenAccountBalance()) {
            return false;
        }
        dbManager.addAccount(loginUser.getUserId(), initialBalance, currency, AccountType.CHECKING.getAccountType());
        return true;
    }

    public boolean createSavingsAccount(String currency, double initialBalance) {
    	if (initialBalance < BankConstants.getMinOpenAccountBalance()) {
            return false;
        }
    	dbManager.addAccount(loginUser.getUserId(), initialBalance, currency, AccountType.SAVINGS.getAccountType());
        return true;
    }

    public boolean createSecuritiesAccount(String currency, double initialBalance) {
    	List<SavingsAccount> savingsAccounts = loginUser.getSavingsAccount("USD");
        for (SavingsAccount sa: savingsAccounts) {
        	if (sa.getBalance() >= initialBalance) {
        		return true;
        	}
        }
        return false;
    }
}
