import java.util.List;

public class CheckingsAccountRepo extends AccountRepo{

    public boolean createCheckingAccount(User user, double openBalance, String currency, DBManager db) {
        CheckingsAccount account = (CheckingsAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.CHECKING.getAccountType());
        if(account!= null)
            return true;
        else
            return false;

    }

}
