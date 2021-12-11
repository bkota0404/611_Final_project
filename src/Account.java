import java.math.BigDecimal;
public class Account {

    private AccountType accountType;
    private String currency;
    private String accountName;
    private double balance;
    private int userId;
    private int accountId;

    public Account(int account_id, int user_id, String currency, double balance, AccountType type) {
        this.accountId = account_id;
        this.userId = user_id;
        this.currency = currency;
        this.accountType = type;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public void addBalance(double amount) {
    	this.balance += amount;
    }
    
    public void deductBalance(double amount) {
    	this.balance -= amount;
    }

}
