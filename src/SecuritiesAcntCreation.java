public class SecuritiesAcntCreation extends AccountCreation {

    public boolean createSecuritiesAccount(User user, double openBalance, String currency, DBManager db) {
        SecuritiesAccount account = (SecuritiesAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.SECURITIES.getAccountType());
        if(account!= null)
            return true;
        else
            return false;
    }
}
