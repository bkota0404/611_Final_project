import java.util.List;

public class SavingsAccountCreation extends AccountCreation {


    public boolean createSavingsAccount(User user, double openBalance, String currency, DBManager db) {
        if (openBalance < BankConstants.getMinOpenAccountBalance()) {
            return false;
        }
        else{
            SavingsAccount account = (SavingsAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.SAVINGS.getAccountType());
            Customer c = (Customer)user;
            c.addAccount(account);
            Transaction t = db.addTransaction(TransactionType.OPENACCOUNT,user.getUserId(),account.getAccountId(),openBalance,currency,-1,-1,null);
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
