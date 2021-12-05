public class SavingsAccountCreation extends AccountCreation {


    public boolean createSavingsAccount(User user, double openBalance, String currency, DBManager db) {
        if (openBalance < BankConstants.getMinOpenAccountBalance()) {
            return false;
        }
        else{
            SavingsAccount account = (SavingsAccount) db.addAccount(user.getUserId(),openBalance,currency,AccountType.SAVINGS.getAccountType());
            if(account!= null)
                return true;
            else
                return false;
        }
        }
}
