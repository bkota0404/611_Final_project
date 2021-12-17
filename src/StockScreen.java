import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StockScreen extends ItemScreen {


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
            if (securitiesAccount.getStocksPurchased() != null)
//                System.out.println(securitiesAccount.getStocksPurchased().size());
                if (securitiesAccount.getStocksPurchased().size() > 0) {
                    for (StocksPurchased stocksPurchased : securitiesAccount.getStocksPurchased()) {
                        stockPurchasedPanel.add(new StockPurchasedItem((Customer) bank.getLoggedUser(), bank, stocksPurchased, this).getMainPanel());
                    }
                }
        }
        if (bank.getStocksOffered() != null) {
            ArrayList<ArrayList<String>> info = bank.getStocksOffered().get2dListOfStock();
            int num_line = 0;
            for (ArrayList<String> line : info) {
                if (num_line++ >= 1000) {
                    return;
                }
                Stocks stock = new Stocks(0, "USD", Double.valueOf(line.get(2)), line.get(0), line.get(1));
                stockOfferedPanel.add(new StockOfferedItem(stock, bank, this).getMainPanel());
            }
        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 6, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stockPanel = new JPanel();
        stockPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(stockPanel);
        final JScrollPane scrollPane2 = new JScrollPane();
        stockPanel.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stockPurchasedPanel = new JPanel();
        stockPurchasedPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane2.setViewportView(stockPurchasedPanel);
        final JScrollPane scrollPane3 = new JScrollPane();
        stockPanel.add(scrollPane3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stockOfferedPanel = new JPanel();
        stockOfferedPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane3.setViewportView(stockOfferedPanel);
        final JLabel label1 = new JLabel();
        label1.setText("Stock Market");
        stockPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Customer:");
        mainPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        customer = new JLabel();
        customer.setText("Label");
        mainPanel.add(customer, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Balance:");
        mainPanel.add(label3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        amount = new JLabel();
        amount.setText("Label");
        mainPanel.add(amount, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
