import java.util.List;

public class Manager extends User{
    public Manager(int id, String name, String userName, String password, UserRoles userRole) {
        super(id, name, userName, password, userRole);
    }

    //get list of all customers
    public List<Account> getAllAccounts(DBManager db){
        List<Account> getAllAccounts = db.getAllAccounts();
        return getAllAccounts;
    }

    //get list of last 24hrs transactions
    public List<Transaction> getAllLatestTransactions(DBManager db){
        List<Transaction> lst24hrsTransactions = db.get24hrTransactionList();
        return lst24hrsTransactions;
    }

    //Bank manager can refresh or get the latest stocks
    public void refreshStocksOffered(StocksOffered stocksOffered){
        stocksOffered.getStocksAPI();
    }

    //get list of loans pending
    public List<Loan> getAllOpenLoans(DBManager db){
        List<Loan> allOpenLoans = db.getAllOpenLoans();
        return allOpenLoans;
    }
}
