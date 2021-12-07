public enum LoanStatus {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    private String loanStatus;

    LoanStatus(String loanStatus) {
        this.setLoanStatus(loanStatus);
    }
}
