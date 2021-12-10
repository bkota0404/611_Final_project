public class SecuritiesAcntCreation extends AccountCreation {

    public boolean createSecuritiesAccount(User user, double openBalance, String currency, DBManager db) {
        SecuritiesAccount account = (SecuritiesAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.SECURITIES.getAccountType());
        db.addTransaction(TransactionType.OPENACCOUNT,user.getUserId(),account.getAccountId(),openBalance,currency,-1,-1,null);
        if(account!= null)
            return true;
        else
            return false;
    }
}
