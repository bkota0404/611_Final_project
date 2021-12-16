import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockPurchasedItem extends UIItem{

    private JPanel mainPanel;
    private JLabel userID;
    private JLabel accountID;
    private JLabel stockID;
    private JLabel numOfShares;
    private JButton sellStockButton;

    public StockPurchasedItem(Bank bank, StocksPurchased stock, ItemScreen parentScreen) {
        super(parentScreen);

        Customer customer = (Customer) bank.getLoggedUser();
        userID.setText(String.valueOf(stock.getUserID()));
        accountID.setText(String.valueOf(stock.getAccountID()));
        stockID.setText(String.valueOf(stock.getStockPurchased().getStockID()));
        numOfShares.setText(String.valueOf(stock.getNumOfShares()));
        sellStockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new SellStockDialog(stock, customer, parentScreen);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}


}
