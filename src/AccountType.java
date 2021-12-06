public enum AccountType {

    SAVINGS("SAVINGS"),
    CHECKING("CHECKING"),
    SECURITIES("SECURITIES"),
    MANAGERACCOUNT("MANAGERACCOUNT");

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
