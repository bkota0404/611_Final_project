import java.util.List;

public class SavingsAccountRepo extends AccountRepo{


    public boolean createSavingsAccount(User user, double openBalance, String currency, DBManager db) {
        SavingsAccount account = (SavingsAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.SAVINGS.getAccountType());
        if(account!= null)
            return true;
        else
            return false;
    }
}
