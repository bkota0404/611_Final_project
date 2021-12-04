import java.sql.*;

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
    public void addAccount(int userId, double amount, String currency, String type) {
        String sql = "INSERT INTO ACCOUNT(USER_ID,AMOUNT,CURRENCY,TYPE) VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, currency);
            pstmt.setString(4, type);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
