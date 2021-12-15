import java.math.BigDecimal;
import java.util.*;

public class Bank {


    private DBManager dbManger = null;
    private final String bankName;
    private SavingsAccountCreation savingAccountRepo;
    private User loggedUser;
    private CheckingsAccountCreation checkingsAccountRepo;
    private SecuritiesAcntCreation securitiesAccntRepo;
    private UserCreation userCreation;

    public UserCreation getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(UserCreation userCreation) {
        this.userCreation = userCreation;
    }


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

    public void startOperations() {
        //method to begin displaying login screen etc
        initializeBank();
        Music player = new Music();
        try{
            player.playMusic(BankConstants.BANK_FILE_PATH+"WelcomeMessage.wav");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new LoginScreen(this);
    }

    public boolean login(String username, String password) {
        this.setLoggedUser(dbManger.isValidUser(username,password));
        if(this.getLoggedUser() != null)
            return true;
        else
            return false;
    }

    public boolean login(User user) {
        this.setLoggedUser(user);
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
        CheckingsAccountCreation checkingAccountRepo = new CheckingsAccountCreation();
        this.setCheckingsAccountRepo(checkingAccountRepo);
        SecuritiesAcntCreation securitiesAccountRepo = new SecuritiesAcntCreation();
        this.setSecuritiesAccntCreation(securitiesAccountRepo);
        UserCreation userCreation = new UserCreation();
        this.setUserCreation(userCreation);
        addBankManager();
    }

    //signup function for new user
    public User signup(String name, String userName, String password){
        User user = userCreation.createUser(name,userName,password,UserRoles.CUSTOMER,dbManger);
        return user;
//        if(isUserCreated)
//            return true;
//        else
//            return false;
    }

    public DBManager getDbManger() {
        return dbManger;
    }

    //add default Bank Manager account
    public void addBankManager(){
        User bankManager = dbManger.getUser("admin");
        if(bankManager == null){
            User isUserCreated = userCreation.createUser("Bank Manager","admin","admin",UserRoles.MANAGER,dbManger);
        }
    }

    public CheckingsAccountCreation getCheckingsAccountRepo() {
        return checkingsAccountRepo;
    }

    public void setCheckingsAccountRepo(CheckingsAccountCreation checkingsAccountRepo) {
        this.checkingsAccountRepo = checkingsAccountRepo;
    }

    public SecuritiesAcntCreation getSecuritiesAccntCreation() {
        return securitiesAccntRepo;
    }

    public void setSecuritiesAccntCreation(SecuritiesAcntCreation securitiesAccntCreation) {
        this.securitiesAccntRepo = securitiesAccntCreation;
    }

    //create Account based on user selection
    public boolean createAccount(CurrencyType currencyType, double openingBalance, AccountType accountType){
        boolean isAccntCreated =false;
        if(accountType == AccountType.SAVINGS){
            isAccntCreated = savingAccountRepo.createSavingsAccount(this.getLoggedUser(), openingBalance,currencyType.getCurrencyName(),dbManger);
        }
        else if(accountType == AccountType.CHECKING){
            isAccntCreated = checkingsAccountRepo.createCheckingAccount(this.getLoggedUser(), openingBalance, currencyType.getCurrencyName(), dbManger);
        }
        else
        {
            Account savingsAccount = dbManger.getUserAccountByType((Customer)this.getLoggedUser(),AccountType.SAVINGS);
            if(savingsAccount.getBalance() > BankConstants.getMinOpenSavingAccountBalanceForSecurities()){
                isAccntCreated = securitiesAccntRepo.createSecuritiesAccount(this.getLoggedUser(), BankConstants.getMinOpenSecuritiesAccountBalance(), currencyType.getCurrencyName(), dbManger);
                savingsAccount.setBalance(savingsAccount.getBalance()-BankConstants.getMinOpenSecuritiesAccountBalance());
            }
        }
        return isAccntCreated;
    }

    //close customer account
    public boolean closeAccount(Account bankAccount) {
        if (dbManger.deleteAccount(bankAccount.getAccountId())) {
            chargeFee(bankAccount.getUserId(), bankAccount, BankConstants.getCloseAccountFee(),TransactionType.CHARGEFEE);
            Transaction t = dbManger.addTransaction(TransactionType.CLOSEACCOUNT, bankAccount.getUserId(), bankAccount.getAccountId(), bankAccount.getBalance(), bankAccount.getCurrency(), -1, -1, null);
            Customer c = (Customer) this.getLoggedUser();
            c.addTransaction(t);
            c.deleteAccount(bankAccount);
            return true;
        }
        else
            return false;
    }

    //deposit amount to a bank account
    protected boolean deposit(Account account, double amount) {
        if(dbManger.updateBalance(account.getAccountId(),account.getBalance()+amount)){
            account.setBalance(account.getBalance()+(amount));
            Transaction t = dbManger.addTransaction(TransactionType.DEPOSIT, account.getUserId(), account.getAccountId(), amount, account.getCurrency(), -1, -1, null);
            Customer c = (Customer) this.getLoggedUser();
            c.addTransaction(t);
            return true;
        }
        else
            return false;
    }

    //withdraw an amount from bank account
    protected boolean withdraw(Account account, double amount) {
        if(account.getBalance()>= amount+(BankConstants.getWithDrawFeePercentage()*amount)){
            double withdrawMoney = account.getBalance()-(amount);
            if(dbManger.updateBalance(account.getAccountId(),withdrawMoney)){
                account.setBalance(account.getBalance()-(amount));
                Transaction t = dbManger.addTransaction(TransactionType.WITHDRAWAL, account.getUserId(), account.getAccountId(), amount, account.getCurrency(), -1, -1, null);
                Customer c = (Customer) this.getLoggedUser();
                c.addTransaction(t);
                chargeFee(this.getLoggedUser().getUserId(),account,account.getBalance()-(BankConstants.getWithDrawFeePercentage()*amount),TransactionType.CHARGEFEE);
                account.setBalance(account.getBalance()-(BankConstants.getWithDrawFeePercentage()*amount));
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }


    //Function to charge an amount to the bank account
    public void chargeFee(int customerID, Account account, double amount,TransactionType transactionType) {
        if (dbManger.updateBalance(account.getAccountId(), amount)){
            account.setBalance(amount);
        }
        dbManger.addTransaction(transactionType, customerID, account.getAccountId(), amount, account.getCurrency(), -1, -1, null);
    }

    public boolean transfer(Account fromAccount, Account toAccount, double amount) {
        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            return false;
        }
        if (fromAccount.getBalance() < amount) {
            return false;
        }
        fromAccount.deductBalance(amount);
        toAccount.addBalance(amount * (1 - BankConstants.getTransactionFeeRate()));
        if(!dbManger.transferMoney(fromAccount.getAccountId(), toAccount.getAccountId(), fromAccount.getBalance(), toAccount.getBalance())) {
            return false;
        }
        return true;
    }

    public boolean payBackLoan(Loan loan, Account account, double amount) {
        if (account.getBalance() < amount) {
            return false;
        }
        double loanAmount = loan.getAmount();
        double realAmount = Math.min(loanAmount, amount);
        account.deductBalance(realAmount);
        loan.setAmount(loanAmount - realAmount);
        dbManger.updateLoanAmount(loan.getLoanID(), loanAmount - realAmount);
        if (loanAmount == realAmount) {
            loan.setLoanStatus(LoanStatus.CLOSE);
            dbManger.updateLoanClosure(loan.getLoanID());
        }
        return false;
    }

    //add a loan to user
    public boolean requestLoan(CurrencyType currency, double loanAmount, String collateral) {
        Customer c = (Customer)this.getLoggedUser();
        Loan loan = dbManger.addLoan(c, loanAmount, currency.getCurrencyName(), collateral);
        if (loan != null) {
            c.addLoan(loan);
            return true;
            }
        else
            return false;
    }

    public List<Customer> getAllCustomers() {
        return dbManger.getAllCustomers();
    }

    public List<Transaction> getDailyTransactions() {
        return dbManger.get24hrTransactionList();
    }

    public boolean isQualifiedForSecurities() {
        Account savingsAccount = dbManger.getUserAccountByType((Customer)this.getLoggedUser(),AccountType.SAVINGS);
        if(savingsAccount.getBalance() > BankConstants.getMinOpenSavingAccountBalanceForSecurities()){
            return true;
        }
        return false;
    }

}