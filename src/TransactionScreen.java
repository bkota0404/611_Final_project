import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionScreen extends ItemScreen {
    private JPanel mainPanel;
    private JPanel transactionPanel;
    private JLabel customer;
    private List<TransactionItem> transactionItems;

    public TransactionScreen(Bank bank) {
        super(bank);

        transactionItems = new ArrayList<TransactionItem>();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        Customer customer1 = (Customer) bank.getLoggedUser();
        updateTransactionItems(customer1.getTransactions());
        customer.setText(bank.getLoggedUser().getName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

    }

    @Override
    public void refresh() {

    }


    private void updateTransactionItems(List<Transaction> transactions) {
        transactionItems.clear();
        transactionPanel.removeAll();
        if (transactions.size() == 0) {
//            transactionItems = new ArrayList<TransactionItem>();
//            System.out.println("no account");
            return;
        }
        for (Transaction transaction : transactions) {
            TransactionItem transactionItem = new TransactionItem(transaction, this);
            transactionItems.add(transactionItem);
            transactionPanel.add(transactionItem.getMainPanel());
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
        mainPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(transactionPanel);
        final JLabel label1 = new JLabel();
        label1.setText("Customer:");
        mainPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        customer = new JLabel();
        customer.setText("Label");
        mainPanel.add(customer, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
