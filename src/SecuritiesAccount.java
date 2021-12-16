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

    //buy stocks through securities account
    public boolean buyStock(User u, String stockName, String stockSymbol, double stockPrice, int numPurchased, DBManager db){
        if(this.getBalance()>= stockPrice*numPurchased){
            StocksPurchased s = db.addStocks(stockName,stockSymbol,numPurchased,stockPrice,this.getCurrency(), u.getUserId(), getAccountId());
            stocksPurchased.add(s);
            Transaction t = db.addTransaction(TransactionType.STOCKPURCHASED,u.getUserId(),this.getAccountId(),stockPrice*numPurchased,this.getCurrency(),-1,-1,null);
            ((Customer)u).addTransaction(t);
            return true;
        }
        else
            return false;
    }

    //sell stocks through securities account
    public boolean sellStocks(User u, int stockPurhasedId, int stocksNumber, DBManager db){
        StocksPurchased s = db.getStockPurchasedByID(stockPurhasedId);
        double stockPrice = s.getStockPurchased().getStockPrice(); //saved to update account balance later incase all the stocks are sold
        if(s.getNumOfShares()>=stocksNumber && db.updateStocksPurchasedNumber(stockPurhasedId,s.getNumOfShares()-stocksNumber)){
            Transaction t = db.addTransaction(TransactionType.STOCKSOLD,u.getUserId(),this.getAccountId(),s.getStockPurchased().getStockPrice()*stocksNumber,this.getCurrency(),-1,-1,null);
            ((Customer)u).addTransaction(t);
            this.setBalance(this.getBalance()+(stockPrice*stocksNumber));
            return true;
        }
        else
            return false;
    }

}
