public class Stocks {

    private int stockID;
    private String Currency;
    private double stockPrice;
    private String stockName;
    private String stockSymbol;


    public Stocks(int stockID, String currency, double stockPrice, String stockName, String stockSymbol) {
        this.stockID = stockID;
        Currency = currency;
        this.stockPrice = stockPrice;
        this.stockName = stockName;
        this.stockSymbol = stockSymbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }



    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

}
