public class StocksPurchased {

    private int userID;
    private int accountID;
    private int numOfShares;
    private Stocks stockPurchased;
    private int stockPurchasedId;

    public int getStockPurchasedId() {
        return stockPurchasedId;
    }

    public StocksPurchased(int stockPurchasedId,int userID, int accountID, int numOfShares, Stocks stockPurchased) {
        this.userID = userID;
        this.accountID = accountID;
        this.numOfShares = numOfShares;
        this.stockPurchased = stockPurchased;
        this.stockPurchasedId = stockPurchasedId;
    }

    public Stocks getStockPurchased() {
        return stockPurchased;
    }

    public void setStockPurchased(Stocks stockPurchased) {
        this.stockPurchased = stockPurchased;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public void setNumOfShares(int numOfShares) {
        this.numOfShares = numOfShares;
    }

}
