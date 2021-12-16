public enum TransactionType {
    WITHDRAWAL("WITHDRAWAL"),
    LOANOPEN("LOANOPEN"),
    LOANCLOSE("LOANCLOSE"),
    STOCKPURCHASED("STOCKPURCHASED"),
    STOCKSOLD("STOCKSOLD"),
    CHARGEFEE("CHARGEFEE"),
    CLOSEACCOUNT("CLOSEACCOUNT"),
    OPENACCOUNT("OPENACCOUNT"),
    DEPOSIT("DEPOSIT"),
    LOANINSTALLMENT("LOANINSTALLMENT"),
    ACCOUNTTRANSFER("ACCOUNTTRANSFER"),
    SAVINGSACCTINTEREST("SAVINGSACCTINTEREST");

    private String transactionName;

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
    TransactionType(String transactionName) {
        this.setTransactionName(transactionName);
    }
}
