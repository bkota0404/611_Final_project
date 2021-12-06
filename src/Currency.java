public class Currency {

    private CurrencyType currencyName;
    private double exchangeRate;

    public CurrencyType getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(CurrencyType currencyName) {
        this.currencyName = currencyName;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


}
