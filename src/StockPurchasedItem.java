import javax.swing.*;

public class StockPurchasedItem extends UIItem{

    private JPanel mainPanel;
    private JLabel userID;
    private JLabel accountID;
    private JLabel stockID;
    private JLabel numOfShares;

    public StockPurchasedItem(StocksPurchased stock, ItemScreen parentScreen) {
        super(parentScreen);

        userID.setText(String.valueOf(stock.getUserID()));
        accountID.setText(String.valueOf(stock.getAccountID()));
        stockID.setText(String.valueOf(stock.getStockPurchased().getStockID()));
        numOfShares.setText(String.valueOf(stock.getNumOfShares()));
    }

    public JPanel getMainPanel() {return mainPanel;}


}
