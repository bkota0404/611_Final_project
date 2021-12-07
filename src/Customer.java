import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Qingyuan Zhang
 *
 */
public class Customer extends User {

	private List<Account> accounts;
	private List<Loan> loans;
	private List<Transaction> transactions;
	
	public Customer() {
		accounts = new ArrayList<>();
		loans = new ArrayList<>();
		transactions = new ArrayList<>();
	}

    public Customer(int id, String name, String userName, String password, List<Account> accounts) {
        super();
    }

    public void addAccount(Account a) {
		accounts.add(a);
	}
	
	public void removeAccount(Account a) {
		accounts.remove(a);
	}
	
	public void addLoan(Loan l) {
		loans.add(l);
	}
	
	public void removeLoan(Loan l) {
		loans.remove(l);
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public void removeTransaction(Transaction t) {
		transactions.remove(t);
	}

	public List<SavingsAccount> getSavingsAccount(String currency) {
		List<SavingsAccount> sa = new ArrayList<>();
		for (Account a: accounts) {
			if (a.getAccountType() == AccountType.SAVINGS && a.getCurrency() == currency) {
				sa.add((SavingsAccount)a);
			}
		}
		return sa;
	}
	
	public List<Account> getAllAccounts() {
		if (accounts.size() > 0) {
			return accounts;
		}
		return null;
	}
}
