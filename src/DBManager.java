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
                    + " PRIMARY KEY (ACCT_ID)\n"
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
                    + " PRIMARY KEY (ID)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("User table created");

            //create stocks table
            sql = "CREATE TABLE IF NOT EXISTS STOCKS (\n"
                    + "	ID INTEGER NOT NULL AUTO_INCREMENT,\n"
                    + "	STOCK_NAME TEXT NOT NULL,\n"
                    + "	DESC TEXT NOT NULL UNIQUE,\n"
                    + "	NUMBER INTEGER NOT NULL,\n"
                    + "	PRICE REAL NOT NULL,\n"
                    + "	TOTAL_VALUE REAL NOT NULL,\n"
                    + " PRIMARY KEY (ID)\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Stocks table created");

            //transactions table
            //Currency table

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

}
