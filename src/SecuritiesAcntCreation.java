import java.util.List;

public class SecuritiesAcntCreation extends AccountCreation {

    public boolean createSecuritiesAccount(User user, double openBalance, String currency, DBManager db) {
        SecuritiesAccount account = (SecuritiesAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.SECURITIES.getAccountType());
        Customer c = (Customer)user;
        c.addAccount(account);
        Transaction t = db.addTransaction(TransactionType.OPENACCOUNT,user.getUserId(),account.getAccountId(),openBalance,currency,-1,-1,null);
        c.addTransaction(t);
        if(account!= null)
            return true;
        else
            return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List getAllList() {
        return null;
    }
}
