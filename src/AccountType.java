public enum AccountType {

    SAVINGS("Savings"),
    CHECKING("Checking"),
    SECURITIES("Securities");

    private String accountType;

    AccountType(String accountType) {
        this.setAccountType(accountType);
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


}
