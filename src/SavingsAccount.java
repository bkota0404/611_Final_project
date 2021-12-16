public class SavingsAccount extends Account implements HighInterestEarningAccounts{
    public SavingsAccount(int account_id, int user_id, String currency, double balance, AccountType type) {
        super(account_id, user_id, currency, balance, type);
    }

    @Override
    public void addInterest() {
        DBManager db =new DBManager();
        if(this.getBalance()>BankConstants.getMinOpenSavingAccountBalanceForSecurities()){
            this.setBalance(this.getBalance()+(this.getBalance()*BankConstants.getSavingsInterestPercentage()));
            Transaction t = db.addTransaction(TransactionType.SAVINGSACCTINTEREST,this.getUserId(),this.getAccountId(),this.getBalance()*BankConstants.getSavingsInterestPercentage(),this.getCurrency(),-1,-1,null);
            Customer c = (Customer)db.getUserById(this.getUserId());
            c.addTransaction(t);
        }
    }
}
