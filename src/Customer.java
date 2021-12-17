import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Customer extends User {

	private List<Account> accounts;
	private List<Loan> loans;
	private List<Transaction> transactions;
	private List<StocksPurchased> stockPurchased;


	public List<StocksPurchased> getStockPurchased() {
		return stockPurchased;
	}

	/*public Customer(int id, String name, String userName, String password, UserRoles userRole) {
		super(id, name, userName, password, userRole);
		accounts = new ArrayList<>();
		loans = new ArrayList<>();
		transactions = new ArrayList<>();
		this.stockPurchased = new ArrayList<>();
	}*/

	public Customer(User user, List<Account> accounts,
					List<Loan> loans,List<Transaction> transactions,List<StocksPurchased> stockPurchased) {
		super(user.getUserId(), user.getName(), user.getUserName(), user.getPassword(), user.getUserRole());
		this.accounts = accounts;
		this.loans = loans;
		this.transactions = transactions;
		this.stockPurchased = stockPurchased;
	}

	public Customer(int id, String name, String userName, String password, UserRoles userRole,List<Account> accounts,
					List<Loan> loans,List<Transaction> transactions, List<StocksPurchased> stockPurchased)  {
		super(id, name, userName, password, userRole);
		this.accounts = accounts;
		this.loans = loans;
		this.transactions = transactions;
		this.stockPurchased = stockPurchased;
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


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	//check why currency is required as input parameter
	public List<SavingsAccount> getSavingsAccount() {
		List<SavingsAccount> sa = new ArrayList<>();
		for (Account a: this.accounts) {
			if (a.getAccountType() == AccountType.SAVINGS) {
				sa.add((SavingsAccount)a);
			}
		}
		return sa;
	}

	public List<Account> getAccounts() {
		return accounts;
	}


	public List<Loan> getLoans() {
		return loans;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void deleteAccount(Account account){
		this.accounts.remove(account);
	}

	public SecuritiesAccount getCustomerSecurityAcct(){
		SecuritiesAccount s = null;
		if(getAccounts() != null) {
			for(Account a: this.getAccounts()){
				if(a.getAccountType().equals(AccountType.SECURITIES)) {
					s = (SecuritiesAccount) a;
					break;
				}
			}
		}
		return s;
	}

	//check if the customer is eligible for Securities account
	public boolean isQualifiedForSecurities() {
		if(this.getSavingsAccount().size()>0){
			Account savingsAccount = this.getSavingsAccount().get(0);
			if(savingsAccount.getBalance() > BankConstants.getMinOpenSavingAccountBalanceForSecurities()){
				return true;
			}
		}
		return false;
	}


}