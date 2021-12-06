
public class Transaction {

    private int transactionID;
    private int userID;
    private int accountID;
    private TransactionType transactionType;
    private double amount;
    private String currency;
    private int targetUserID = 0;
    private int targetAccountID = 0;
    private String collateral;


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getTargetUserID() {
        return targetUserID;
    }

    public void setTargetUserID(int targetUserID) {
        this.targetUserID = targetUserID;
    }

    public int getTargetAccountID() {
        return targetAccountID;
    }

    public void setTargetAccountID(int targetAccountID) {
        this.targetAccountID = targetAccountID;
    }

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }


}
