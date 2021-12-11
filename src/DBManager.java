import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBManager {
    private Connection conn;

    public DBManager() {
        try {
            // db parameters
            //Class.forName("org.sqlite.JDBC");
            String file = System.getProperty("user.dir") + "/src/database/";
            System.out.println(file);
            String url = "jdbc:sqlite:" + file +"bankproject.db";
            // create a connection to the database
            System.out.println(url);
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            //dropTables();
            createTable();
            //System.out.println(getAllCustomers().get(0));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(){
        try{
            Statement stmt = conn.createStatement();
            //create account table
            String sql = "CREATE TABLE IF NOT EXISTS ACCOUNTS (\n"
                    + "	ID INTEGER NOT NULL UNIQUE,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	AMOUNT REAL NOT NULL,\n"
                    + "	CURRENCY TEXT NOT NULL,\n"
                    + " TYPE TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Accounts table created");

            //create user table
            sql = "CREATE TABLE IF NOT EXISTS USERS (\n"
                    + "	ID INTEGER NOT NULL UNIQUE,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	USERNAME TEXT NOT NULL UNIQUE,\n"
                    + "	PASSWORD TEXT NOT NULL,\n"
                    + "	ROLE TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Users table created");


            //create stocks table
            sql = "CREATE TABLE IF NOT EXISTS STOCKSPURCHASED (\n"
                    + "	ID INTEGER NOT NULL UNIQUE,\n"
                    + "	STOCK_NAME TEXT NOT NULL UNIQUE,\n"
                    + "	ACCT_ID INTEGER NOT NULL,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	DESC TEXT NOT NULL,\n"
                    + "	NUMBER INTEGER NOT NULL,\n"
                    + "	PRICE REAL NOT NULL,\n"
                    + "	TOTAL_VALUE REAL NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Stocks table created");

            //create loans table
            sql = "CREATE TABLE IF NOT EXISTS LOANS (\n"
                    + "	ID INTEGER NOT NULL UNIQUE,\n"
                    + "	ACCT_ID INTEGER NOT NULL,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	COLLATERAL TEXT NOT NULL UNIQUE,\n"
                    + "	AMOUNT REAL NOT NULL,\n"
                    + "	CURRENCY TEXT NOT NULL,\n"
                    + "	STATUS TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Loans table created");

            //transactions table
            sql = "CREATE TABLE IF NOT EXISTS TRANSACTIONS(\n"
                    + "ID INTEGER NOT NULL UNIQUE,\n"
                    + "TYPE TEXT NOT NULL,\n"
                    + "USERID INTEGER NOT NULL,\n"
                    + "ACCT_ID INTEGER,\n"
                    + "AMOUNT REAL,\n"
                    + "CURRENCY TEXT,\n"
                    + "TARGETACCOUNTID INTEGER,\n"
                    + "TARGETUSERID INTEGER,\n"
                    + "COLLATERAL TEXT,\n"
                    + "DATE TEXT NOT NULL,\n"
                    + "PRIMARY KEY(ID AUTOINCREMENT))";
            stmt.execute(sql);
            System.out.println("Transactions table created");

            //Currency table
            sql = "CREATE TABLE IF NOT EXISTS CURRENCY (\n"
                    + "	ID INTEGER NOT NULL UNIQUE,\n"
                    + "	CURRSYMBOL TEXT NOT NULL UNIQUE,\n"
                    + "	EXCHANGERATE TEXT NOT NULL,\n"
                    + "	STATUS TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Currency table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //drop tables for testing
    public void dropTables() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "DROP TABLE USERS";
            stmt.execute(sql);
            sql = "DROP TABLE ACCOUNTS";
            stmt.execute(sql);
            sql = "DROP TABLE TRANSACTIONS";
            stmt.execute(sql);
            sql = "DROP TABLE LOANS";
            stmt.execute(sql);
            sql = "DROP TABLE STOCKSPURCHASED";
            stmt.execute(sql);
            sql = "DROP TABLE CURRENCY";
            stmt.execute(sql);
            System.out.println("Drop successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //check for authenticated user
    public User isValidUser(String username, String password) {
        String sql = "SELECT ID, USERNAME FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        User authUser = null;
        int id;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            id = rs.getInt(1);
            if (id != -1) {
                authUser = getUser(username);
                //System.out.println(authUser.getUserId()+" "+authUser.getUserName()+" "+authUser.getName());
            }
            else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return authUser;
    }

    //insert data into account table
    public Account addAccount(int userId, double amount, String currency, String type) {
        String sql = "INSERT INTO ACCOUNTS(USER_ID,AMOUNT,CURRENCY,TYPE) VALUES(?,?,?,?)";
        Account account = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, currency);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    account = getAccount(generatedKeys.getInt(1));
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }


    //query for an Account
    public Account getAccount(int id) {
        Account account = null;
        try {
            String sql = "SELECT ID, USER_ID, CURRENCY ,AMOUNT, TYPE FROM ACCOUNTS WHERE ID = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, id);
            ResultSet rs = stmt2.executeQuery();

            String accType = rs.getString(3);
            switch (accType) {
                case "SAVINGS":
                    account = new SavingsAccount(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            AccountType.SAVINGS);
                    break;
                case "CHECKING":
                    account = new CheckingsAccount(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            AccountType.CHECKING);
                    break;
                case "SECURITIES":
                    account = new SecuritiesAccount(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            AccountType.SECURITIES);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return account;
    }

    //get User account by accounttype
    public Account getUserAccountByType(Customer customer, AccountType accountType){
        int userID = customer.getUserId();
        Account account = null;
        try{
            String sql = "SELECT ID FROM ACCOUNTS WHERE USER_ID = ? AND TYPE = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, userID);
            stmt2.setString(2, accountType.getAccountType());
            ResultSet rs = stmt2.executeQuery();
            if(rs.next()){
                int accntID = rs.getInt(1);
                account = getAccount(accntID);
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return account;
    }

    //query for all Accounts
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT ID FROM ACCOUNTS";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                Account a = getAccount(rs2.getInt(1));
                accounts.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return accounts;
    }

    //deleting Account called on close
    public boolean deleteAccount(int accountId) {
        String sql = "DELETE FROM ACCOUNTS WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //Update balance to account
    public boolean updateBalance(int accountId, double amount) {
        String sql = "UPDATE ACCOUNTS SET AMOUNT = ?," +  "WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    //add stock into stocks table
    public void addStocks(String name, String description, int numOfStocks, double price, double totalValue) {
        String sql = "INSERT INTO STOCKS(STOCK_NAME,DESC,NUMBER,PRICE,TOTAL_VALUE) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, numOfStocks);
            pstmt.setDouble(4, price);
            pstmt.setDouble(4, totalValue);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //add User
    public boolean addUser(String name, String userName, String password, UserRoles role) {
        String sql = "INSERT INTO USERS(NAME,USERNAME,PASSWORD,ROLE) VALUES (?,?,?,?)";
        try {
            System.out.println(name+" "+userName);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, role.toString());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //get List of Customers
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT ID, USERNAME FROM USERS WHERE ROLE = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, UserRoles.CUSTOMER.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User p = getUser(rs.getString(2));
                list.add((Customer) p);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }

    //get a User
    public User getUser(String username) {
        String sql = "SELECT ID, NAME, USERNAME, PASSWORD, ROLE FROM USERS WHERE USERNAME = ?";
        User newUser = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String userName = rs.getString(3);
            String password = rs.getString(4);
            String role = rs.getString(5);

            if (role.equals("CUSTOMER")) {

                List<Account> accounts = getUserAccounts(id);
                List<Loan> loans = getAllUserLoans(id);
                //List<Transaction> transactions = getAllUserTransaction(id);
                //List<StockPosition> stockPositions = getAllStockPosition(id);
                newUser = new Customer(id, name, userName, password, UserRoles.CUSTOMER);
            } else if (role.equals(UserRoles.MANAGER.toString())) {
                newUser = new Manager(id, name, userName, password, UserRoles.MANAGER);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return newUser;
    }

    //get User Accounts
    public List<Account> getUserAccounts(int id) {
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT ID FROM ACCOUNTS WHERE USER_ID = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, id);
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                Account a = getAccount(rs2.getInt(1));
                accounts.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return accounts;
    }

    //add Bank manager as a user
    public void addBankManager() {
        String sql = "INSERT OR IGNORE INTO USERS(NAME,USERNAME,PASSWORD,ROLE) VALUES (\"BANKMANAGER\",\"admin\",\"admin\",\"MANAGER\");";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //add loans
    public Loan addLoan(Customer c, double amount, String currency, String collateral) {
        String sql = "INSERT INTO LOANS(USERID,AMOUNT,CURRENCY,COLLATERAL,STATUS) VALUES (?,?,?,?)";
        Loan loan = null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, c.getUserId());
            stmt.setDouble(2, amount);
            stmt.setString(3, currency);
            stmt.setString(4, collateral);
            stmt.setString(5, LoanStatus.OPEN.getLoanStatus());
            stmt.execute();

            //sql = "SELECT MAX(ID) FROM LOANS";
            //stmt = conn.prepareStatement(sql);
            //ResultSet rs = stmt.executeQuery();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                loan = new Loan(generatedKeys.getInt(1), c.getUserId(), currency, amount, collateral,LoanStatus.OPEN);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return loan;
    }

    //get loan by id
    public Loan getLoan(int id) {
        Loan loan = null;
        try {
            String sql = "SELECT ID, USERID, CURRENCY ,AMOUNT, COLLATERAL,STATUS FROM LOANS WHERE ID = ?";

            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, id);
            ResultSet rs2 = stmt2.executeQuery();
            if(rs2.getString(6).equalsIgnoreCase(LoanStatus.OPEN.getLoanStatus())){
                loan = new Loan(
                        rs2.getInt(1),
                        rs2.getInt(2),
                        rs2.getString(3),
                        rs2.getDouble(4),
                        rs2.getString(5),
                        LoanStatus.OPEN);
            }
            else{
                loan = new Loan(
                        rs2.getInt(1),
                        rs2.getInt(2),
                        rs2.getString(3),
                        rs2.getDouble(4),
                        rs2.getString(5),
                        LoanStatus.CLOSE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return loan;
    }

    //get all user loans which are open status
    public List<Loan> getAllUserLoans(int userId) {
        List<Loan> loans = new ArrayList<>();

        try {
            String sql = "SELECT ID FROM LOANS WHERE USERID = ? AND STATUS = LoanStatus.OPEN.getLoanStatus()";

            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, userId);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                Loan l = getLoan(rs2.getInt(1));
                loans.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return loans;
    }

    //get all loans for manager view
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();

        try {
            String sql = "SELECT ID FROM LOANS";

            PreparedStatement stmt2 = conn.prepareStatement(sql);

            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                Loan l = getLoan(rs2.getInt(1));
                loans.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return loans;
    }

    //update loan amount
    public boolean updateLoanAmount(int id, double amount) {
        String sql = "UPDATE LOANS SET AMOUNT = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //update loan to closure
    public boolean updateLoanClosure(int id) {
        String sql = "UPDATE LOANS SET STATUS = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, LoanStatus.CLOSE.getLoanStatus());
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //add transaction
    public Transaction addTransaction(TransactionType type, int userid, int accountId, double amount, String currency, int targetUserId, int targetAccountId, String collateral) {
        String sql = "INSERT INTO TRANSACTIONS(DATE,TYPE,AMOUNT,CURRENCY,USERID,ACCT_ID,TARGETUSERID,TARGETACCOUNTID,COLLATERAL) VALUES (?,?,?,?,?,?,?,?,?)";
        Transaction t = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String strDate = dateFormat.format(date);
            stmt.setString(1, strDate);
            stmt.setString(2, type.toString());
            stmt.setDouble(3, amount);
            stmt.setString(4, currency);
            stmt.setInt(5, userid);
            stmt.setInt(6, accountId);
            stmt.setInt(7, targetUserId);
            stmt.setInt(8, targetAccountId);
            stmt.setString(9, collateral);
            stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                t = new Transaction(generatedKeys.getInt(1), date, type, amount, currency, userid, accountId, targetUserId, targetAccountId, collateral);
            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return t;
    }

    //generate last 24 hrs transactions for bank manager
    public List<Transaction> get24hrTransactionList() {
        List<Transaction> list = new ArrayList<>();

        try {
            String sql = "SELECT ID FROM TRANSACTIONS WHERE DATE = ? OR DATE = ?";

            PreparedStatement stmt2 = conn.prepareStatement(sql);
            long day = 24 * 60 * 60 * 1000;
            stmt2.setString(1, BankConstants.DATE_FORMAT.format(System.currentTimeMillis()));
            stmt2.setString(2, BankConstants.DATE_FORMAT.format(System.currentTimeMillis() - day));
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                Transaction l = getTransaction(rs2.getInt(1));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }

    //get a transaction by id
    public Transaction getTransaction(int id) {
        Transaction t = null;
        try {
            String sql = "SELECT ID, DATE, TYPE ,AMOUNT, CURRENCY, USERID, ACCOUNTID, TARGETACCOUNTID, TARGETUSERID, COLLATERAL FROM TRANSACTIONS WHERE ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            String sDate = rs.getString(2);
            Date date = BankConstants.DATE_FORMAT.parse(sDate);
            t = new Transaction(rs.getInt(1),
                    date,
                    TransactionType.valueOf(rs.getString(3)),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getString(10));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return t;
    }

    //get transactions of each user both credit and debit
    public List<Transaction> getAllUserTransaction(int id) {
        List<Transaction> list = new ArrayList<>();

        try {
            String sql = "SELECT ID FROM TRANSACTIONS WHERE USERID = ? OR TARGETUSERID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            ResultSet rs2 = stmt.executeQuery();
            while (rs2.next()) {
                Transaction l = getTransaction(rs2.getInt(1));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }


    //tranfer money from one account to other
    public boolean transferMoney(int fromId, int toId, double fromAmount, double toAmount) {

        String sql = "UPDATE ACCOUNTS SET AMOUNT = ? " +
                "WHERE ID = ?";

        try {
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, fromAmount);
            stmt.setInt(2, fromId);
            stmt.executeUpdate();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, toAmount);
            stmt.setInt(2, toId);
            stmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
