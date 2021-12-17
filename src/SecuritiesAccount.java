import java.util.ArrayList;
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
        stocksPurchased = new ArrayList<>();
    }

    //buy stocks through securities account
    public boolean buyStock(User u, String stockName, String stockSymbol, double stockPrice, int numPurchased, DBManager db){
        if(this.getBalance()>= stockPrice*numPurchased && numPurchased>0){
            StocksPurchased s = db.addStocks(stockName,stockSymbol,numPurchased,stockPrice,this.getCurrency(), u.getUserId(), getAccountId());
            stocksPurchased.add(s);
            Transaction t = db.addTransaction(TransactionType.STOCKPURCHASED,u.getUserId(),this.getAccountId(),stockPrice*numPurchased,this.getCurrency(),-1,-1,null);
            ((Customer)u).addTransaction(t);
            this.setBalance(this.getBalance()-(stockPrice*numPurchased));
            db.updateBalance(this.getAccountId(),this.getBalance());
            return true;
        }
        else
            return false;
    }

    //sell stocks through securities account
    public boolean sellStocks(User u, int stockPurhasedId, int stocksNumber, DBManager db){
        StocksPurchased s = db.getStockPurchasedByID(stockPurhasedId);
        String name = s.getStockPurchased().getStockName();
        int index=0;
        for(int i=0; i< stocksPurchased.size();i++){
            if(stocksPurchased.get(i).getStockPurchased().getStockName().equalsIgnoreCase(name))
                index = i;
        }
        double stockPrice = s.getStockPurchased().getStockPrice(); //saved to update account balance later incase all the stocks are sold
        if(s.getNumOfShares()>=stocksNumber && db.updateStocksPurchasedNumber(stockPurhasedId,s.getNumOfShares()-stocksNumber)){
            s.setNumOfShares(s.getNumOfShares()-stocksNumber);
            Transaction t = db.addTransaction(TransactionType.STOCKSOLD,u.getUserId(),this.getAccountId(),s.getStockPurchased().getStockPrice()*stocksNumber,this.getCurrency(),-1,-1,null);
            ((Customer)u).addTransaction(t);
            if(s.getNumOfShares()==stocksNumber)
                stocksPurchased.remove(index);
            this.setBalance(this.getBalance()+(stockPrice*stocksNumber));
            db.updateBalance(this.getAccountId(),this.getBalance());
            return true;
        }
        else
            return false;
    }

}
