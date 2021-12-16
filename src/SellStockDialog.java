import javax.swing.*;
import java.awt.event.*;

public class SellStockDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner amountSpinner;
    private ItemScreen parentScreen;
    private Customer customer;
    private StocksPurchased stocksPurchased;
    private Bank bank;

    public SellStockDialog(StocksPurchased stocksPurchased, Bank bank, ItemScreen parentScreen) {

        this.bank = bank;
        this.customer = (Customer) bank.getLoggedUser();
        this.parentScreen = parentScreen;
        this.stocksPurchased = stocksPurchased;
        amountSpinner.setModel(new SpinnerNumberModel(0, 0, stocksPurchased.getNumOfShares(), 1));
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        int number = Integer.parseInt(amountSpinner.getValue().toString());
        Stocks stock = stocksPurchased.getStockPurchased();
        if(!customer.getCustomerSecurityAcct().sellStocks(customer, stock.getStockID(), number, bank.getDbManger())) {
            JOptionPane.showMessageDialog(this, "Failed to sell the stock.");
            return;
        }
        parentScreen.refresh();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
