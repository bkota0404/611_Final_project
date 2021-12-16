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

    public StockManagementItem() {


        adjustPriceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        cancelStockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                
            }
        });
    }
}
