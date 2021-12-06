import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Connection conn = null;

    public DBManager() {
        try {
            // db parameters
            //Class.forName("org.sqlite.JDBC");
            String file = System.getProperty("user.dir") + "/src/database/";
            String url = "jdbc:sqlite:" + file +"bankproject.db";
            // create a connection to the database
            //System.out.println(url);
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            createTable();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(){
        try{
            Statement stmt = conn.createStatement();
            //create account table
            String sql = "CREATE TABLE IF NOT EXISTS ACCOUNT (\n"
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	AMOUNT REAL NOT NULL,\n"
                    + "	CURRENCY TEXT NOT NULL,\n"
                    + " TYPE TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Account table created");

            //create user table
            sql = "CREATE TABLE IF NOT EXISTS USERS (\n"
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
                    + "	NAME TEXT NOT NULL,\n"
                    + "	USERNAME TEXT NOT NULL UNIQUE,\n"
                    + "	PASSWORD TEXT NOT NULL,\n"
                    + "	ROLE TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("User table created");


            //create stocks table
            sql = "CREATE TABLE IF NOT EXISTS STOCKS (\n"
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
                    + "	STOCK_NAME TEXT NOT NULL,\n"
                    + "	ACCT_ID INTEGER NOT NULL,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	DESC TEXT NOT NULL UNIQUE,\n"
                    + "	NUMBER INTEGER NOT NULL,\n"
                    + "	PRICE REAL NOT NULL,\n"
                    + "	TOTAL_VALUE REAL NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);

            //create loans table
            sql = "CREATE TABLE IF NOT EXISTS LOANS (\n"
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
                    + "	ACCT_ID INTEGER NOT NULL,\n"
                    + "	USER_ID INTEGER NOT NULL,\n"
                    + "	COLLATERAL TEXT NOT NULL UNIQUE,\n"
                    + "	AMOUNT REAL NOT NULL,\n"
                    + "	CURRENCY TEXT NOT NULL,\n"
                    + "	STATUS TEXT NOT NULL,\n"
                    + " PRIMARY KEY (ID AUTOINCREMENT)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Stocks table created");

            //transactions table
            sql = "CREATE TABLE IF NOT EXISTS TRANSACTIONS(\n"
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
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
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
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

    //insert data into account table
    public Account addAccount(int userId, double amount, String currency, String type) {
        String sql = "INSERT INTO ACCOUNT(USER_ID,AMOUNT,CURRENCY,TYPE) VALUES(?,?,?,?)";
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
    public boolean addUser(String name, String user, String pass, UserRoles role) {
        String sql = "INSERT INTO USERS(NAME,USERNAME,PASSWORD,ROLE) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, user);
            stmt.setString(3, pass);
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
                //List<Loan> loans = getAllUserLoans(id);
                //List<Transaction> transactions = getAllUserTransaction(id);
                //List<StockPosition> stockPositions = getAllStockPosition(id);
                //newUser = new Customer(id, name, userName, password, loans, accounts, transactions, stocks);
            } else if (role.equals(UserRoles.MANAGER.toString())) {
                newUser = new Manager();
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

    //add loans



}
