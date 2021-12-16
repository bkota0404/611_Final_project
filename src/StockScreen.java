import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StockScreen extends ItemScreen{


    private JPanel mainPanel;
    private JLabel customer;
    private JPanel stockPanel;
    private JPanel stockPurchasedPanel;
    private JPanel stockOfferedPanel;
    private JLabel amount;

    public StockScreen(Bank bank) {
        super(bank);

        customer.setText(bank.getLoggedUser().getUserName());
        stockOfferedPanel.setLayout(new BoxLayout(stockOfferedPanel, BoxLayout.Y_AXIS));
        stockOfferedPanel.setSize(600, 400);
        stockPurchasedPanel.setLayout(new BoxLayout(stockPurchasedPanel, BoxLayout.Y_AXIS));
        stockPurchasedPanel.setSize(600, 200);
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
        new StockScreen(bank);
    }


    private void update() {
        Customer customer = (Customer) bank.getLoggedUser();
        SecuritiesAccount securitiesAccount = customer.getCustomerSecurityAcct();
        if (securitiesAccount != null) {
            amount.setText(String.valueOf(securitiesAccount.getBalance()));
            if(securitiesAccount.getStocksPurchased() != null)
//                System.out.println(securitiesAccount.getStocksPurchased().size());
                if(securitiesAccount.getStocksPurchased().size() > 0) {
                    for(StocksPurchased stocksPurchased: securitiesAccount.getStocksPurchased()) {
                        stockPurchasedPanel.add(new StockPurchasedItem((Customer) bank.getLoggedUser(), bank, stocksPurchased, this).getMainPanel());
                    }
                }
        }
        if(bank.getStocksOffered() != null) {
            ArrayList<ArrayList<String>> info = bank.getStocksOffered().get2dListOfStock();
            int num_line = 0;
            for(ArrayList<String> line: info) {
                if(num_line++ >= 1000) {
                    return;
                }
                Stocks stock = new Stocks(0, "USD", Double.valueOf(line.get(2)), line.get(0), line.get(1));
                stockOfferedPanel.add(new StockOfferedItem(stock, bank, this).getMainPanel());
            }
        }
    }




}
