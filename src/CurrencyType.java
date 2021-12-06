public enum CurrencyType {
    USD("USD"),
    CAD("CAD"),
    GBP("GBP");

    CurrencyType(String currencyName) {
        this.setCurrencyName(currencyName);
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    private String currencyName;
}
