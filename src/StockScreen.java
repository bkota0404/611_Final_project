import javax.swing.*;

public class StockScreen extends ItemScreen{


    private JPanel mainPanel;
    private JLabel customer;
    private JPanel stockPanel;
    private JPanel stockPurchasedPanel;
    private JPanel stockOfferedPanel;
    private JLabel amount;

    public StockScreen(Bank bank) {
        super(bank);

        update();
        customer.setText(bank.getLoggedUser().getUserName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

    }


    @Override
    public void refresh() {
        close();
        new StockScreen(bank);
    }


    private void update() {
        Customer customer = (Customer) bank.getLoggedUser();
        SecuritiesAccount securitiesAccount = customer.getCustomerSecurityAcct();
        if (securitiesAccount != null) {
            amount.setText(String.valueOf(securitiesAccount.getBalance()));
            if(securitiesAccount.getStocksPurchased().size() > 0) {
                for(StocksPurchased stocksPurchased: securitiesAccount.getStocksPurchased()) {
                    stockPurchasedPanel.add(new StockPurchasedItem(bank, stocksPurchased, this).getMainPanel());
                }
            }
        }
        if(bank.getStocksOffered() != null) {
            if(bank.getStocksOffered().getAllList().size() > 0) {
                for(Stocks stocks: bank.getStocksOffered().getAllList()) {
                    stockOfferedPanel.add(new StockOfferedItem(stocks, this).getMainPanel());
                }
            }
        }
    }




}
