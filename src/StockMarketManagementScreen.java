import javax.swing.*;

public class StockMarketManagementScreen extends ItemScreen{


    private JPanel mainPanel;
    private JPanel stockPanel;
    private JLabel manager;

    public StockMarketManagementScreen(Bank bank) {
        super(bank);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    @Override
    public void refresh() {
        close();
        new StockMarketManagementScreen(bank);
    }

}
