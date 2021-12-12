import java.util.List;

public class SecuritiesAccount extends Account{

    private List<StocksPurchased> stocksPurchased;

    public List<StocksPurchased> getStocksPurchased() {
        return this.stocksPurchased;
    }

    public void setStocksPurchased(List<StocksPurchased> stocksPurchased) {
        this.stocksPurchased = stocksPurchased;
    }

    public SecuritiesAccount(int account_id, int user_id, String currency, double balance, AccountType type) {
        super(account_id, user_id, currency, balance, type);
    }

    public void buyStock(int userID, int acctID, String stockName, String stockSymbol, double stockPrice, int numPurchased, String currency, DBManager db){
        db.addStocks(stockName,stockSymbol,numPurchased,stockPrice,currency,userID,acctID);
    }
}
