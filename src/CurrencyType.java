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

    public static CurrencyType getEnum(String currency){
        if(currency.equalsIgnoreCase(CurrencyType.CAD.getCurrencyName()))
            return CurrencyType.CAD;
        else if(currency.equalsIgnoreCase(CurrencyType.USD.getCurrencyName()))
            return CurrencyType.USD;
        else if(currency.equalsIgnoreCase(CurrencyType.GBP.getCurrencyName()))
            return CurrencyType.GBP;
        else
            return null;
    }
}
