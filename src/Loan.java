import java.math.BigDecimal;

public class Loan {
    private int loanID;
    private int userID;
    private int accountID;
    private String currency;
    private double amount;
    private LoanStatus loanStatus;

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }


    public Loan(int loanID, int userID, String currency, double loanAmount, String collateral,LoanStatus loanStatus) {
        this.setLoanID(loanID);
        this.setUserID(userID);
        this.setCurrency(currency);
        this.setAmount(loanAmount);
        this.setCollateral(collateral);
        this.setLoanStatus(loanStatus);
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCollateral() {
        return collateral;
    }

    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }

    private String collateral;


}
