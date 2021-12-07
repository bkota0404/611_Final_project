public class Bank {

    private final DBManager dbManger;
    private final String bankName;
    private SavingsAccountCreation savingAccountRepo;
    private User loggedUser;
    private CheckingsAccountCreation checkingsAccountRepo;
    private SecuritiesAcntCreation securitiesAccntCreation;
    private UserCreation userCreation;


    public SavingsAccountCreation getSavingAccountRepo() {
        return savingAccountRepo;
    }

    public void setSavingAccountRepo(SavingsAccountCreation savingAccountRepo) {
        this.savingAccountRepo = savingAccountRepo;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    public Bank(String bankName) {
        DBManager db = new DBManager();
        this.dbManger = db;
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void startOperations(){
        //method to begin displaying login screen etc
        initializeBank();
        new LoginScreen(this);
    }

    public boolean login(String username, String password) {
        this.setLoggedUser(dbManger.isValidUser(username,password));
        if(this.getLoggedUser() != null)
            return true;
        else
         return false;
    }

    public void logout() {
        new LoginScreen(this);
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void initializeBank(){
        SavingsAccountCreation savingAccountRepo = new SavingsAccountCreation();
        this.setSavingAccountRepo(savingAccountRepo);
    }

    public boolean signup(String name, String userName, String password){
        boolean isUserCreated = dbManger.addUser(name,userName,password,UserRoles.CUSTOMER);
        if(isUserCreated)
            return true;
        else
            return false;
    }
}
