import javax.swing.*;
import java.util.ArrayList;

public class StockMarketManagementScreen extends ItemScreen{


    private JPanel mainPanel;
    private JPanel stockPanel;
    private JLabel manager;

    public StockMarketManagementScreen(Bank bank) {
        super(bank);

        manager.setText(bank.getLoggedUser().getName());
        stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS));
        update();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

    }

    @Override
    public void refresh() {
        close();
        new StockMarketManagementScreen(bank);
    }

    private void update() {
        stockPanel.removeAll();

        if(bank.getStocksOffered() != null) {
            ArrayList<ArrayList<String>> info = bank.getStocksOffered().get2dListOfStock();
            int num_line = 0;
            for(ArrayList<String> line: info) {
                if(num_line++ >= 1000) {
                    return;
                }
                Stocks stock = new Stocks(0, "USD", Double.valueOf(line.get(2)), line.get(0), line.get(1));
                stockPanel.add(new StockManagementItem(bank, stock, this).getMainPanel());
            }
        }
    }

}
