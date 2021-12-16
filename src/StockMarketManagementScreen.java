import javax.swing.*;

public class StockMarketManagementScreen extends ItemScreen{


    private JPanel mainPanel;
    private JPanel stockPanel;
    private JLabel manager;

    public StockMarketManagementScreen(Bank bank) {
        super(bank);

        manager.setText(bank.getLoggedUser().getName());
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

}
