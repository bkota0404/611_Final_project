import java.util.List;

public class Bank {

    private final DBManager dbManger;
    private final String bankName;
    private final SavingsAccountRepo savingAccountRepo;

    public Bank(String bankName) {
        DBManager db = new DBManager();
        SavingsAccountRepo savingAccountRepo = new SavingsAccountRepo();
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
