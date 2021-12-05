public class Bank {

    private final DBManager dbManger;
    private final String bankName;
    private final SavingsAccountCreation savingAccountRepo;

    public Bank(String bankName) {
        DBManager db = new DBManager();
        SavingsAccountCreation savingAccountRepo = new SavingsAccountCreation();
        this.dbManger = db;
        this.bankName = bankName;
        this.savingAccountRepo = savingAccountRepo;
    }

    public String getBankName() {
        return bankName;
    }

    public void startOperations(){
        //method to begin displaying login screen etc
    }


}
