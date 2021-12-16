import java.util.List;

public class CheckingsAccountCreation extends AccountCreation {

    public boolean createCheckingAccount(User user, double openBalance, String currency, DBManager db) {
        if (openBalance < BankConstants.getMinOpenAccountBalance()) {
            return false;
        }
        else{
            CheckingsAccount account = (CheckingsAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.CHECKING.getAccountType());
            Customer c = (Customer)user;
            c.addAccount(account);
            Transaction t =db.addTransaction(TransactionType.OPENACCOUNT,user.getUserId(),account.getAccountId(),openBalance,currency,-1,-1,null);
            c.addTransaction(t);
            if(account!= null)
                return true;
            else
                return false;
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List getAllList() {
        return null;
    }
}
