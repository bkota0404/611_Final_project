public class ManagerAccount extends Account{
    public ManagerAccount(int account_id, int user_id, String currency, double balance, AccountType type) {
        super(account_id, user_id, currency, balance, AccountType.MANAGERACCOUNT);
    }
}
