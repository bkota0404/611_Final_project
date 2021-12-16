import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockManagementItem {
    private JPanel mainPanel;
    private JButton adjustPriceButton;
    private JButton cancelStockButton;
    private JLabel name;
    private JLabel price;
    private JLabel symbol;
    private JLabel currency;
    private JButton lowerPriceButton;
    private ItemScreen parentScreen;
    private Bank bank;
    private Stocks stock;

    public StockManagementItem(Bank bank, Stocks stock, ItemScreen parentScreen) {

        this.bank = bank;
        this.stock =stock;
        this.parentScreen = parentScreen;
        name.setText(stock.getStockName());
        price.setText(String.valueOf(stock.getStockPrice()));
        symbol.setText(stock.getStockSymbol());
        currency.setText(stock.getCurrency());


        adjustPriceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new AdjustPriceDialog(stock, parentScreen);
            }
        });
        cancelStockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                if(bank.cancelStock()) {
//                    parentScreen.refresh();
//                }
//                else {
//                    JOptionPane.showMessageDialog(null, "Failed to cancel the stock.");
//                }
            }
        });
    }
}
