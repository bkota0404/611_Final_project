import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockPurchasedItem extends UIItem{

    private JPanel mainPanel;
    private JLabel name;
    private JLabel price;
    private JLabel symbol;
    private JLabel numOfShares;
    private JButton sellStockButton;

    public StockPurchasedItem(Customer customer, Bank bank, StocksPurchased stock, ItemScreen parentScreen) {
        super(parentScreen);

        name.setText(stock.getStockPurchased().getStockName());
        price.setText(String.valueOf(stock.getStockPurchased().getStockPrice()));
        symbol.setText(stock.getStockPurchased().getStockSymbol());
        numOfShares.setText(String.valueOf(stock.getNumOfShares()));
        sellStockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new SellStockDialog(stock, bank, parentScreen);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}


    public JButton getSellStockButton() {
        return sellStockButton;
    }
}
