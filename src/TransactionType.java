public enum TransactionType {
    ACCOUNTDEPOSIT("ACCOUNTDEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    LOANOPEN("LOANOPEN"),
    LOANCLOSE("LOANCLOSE"),
    STOCKPURCHASED("STOCKPURCHASED"),
    STOCKSOLD("STOCKSOLD");

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
