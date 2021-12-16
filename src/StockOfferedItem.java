import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockOfferedItem {
    private JPanel mainPanel;
    private JButton buyStockButton;
    private JLabel name;
    private JLabel price;
    private JLabel symbol;
    private JLabel currency;

    public StockOfferedItem(Stocks stocks, ItemScreen parentScreen) {

        name.setText(stocks.getStockName());
        price.setText(String.valueOf(stocks.getStockPrice()));
        symbol.setText(stocks.getStockSymbol());
        currency.setText(stocks.getCurrency());
        buyStockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
