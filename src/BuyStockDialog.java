import javax.swing.*;
import java.awt.event.*;

public class BuyStockDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner numberSpinner;
    private Customer customer;
    private ItemScreen parentScreen;
    private Stocks stock;
    private Bank bank;

    public BuyStockDialog(Stocks stock, Bank bank, ItemScreen parentScreen) {

        this.bank = bank;
        this.customer = (Customer) bank.getLoggedUser();
        this.parentScreen = parentScreen;
        this.stock = stock;
//        numberSpinner.setModel(new SpinnerNumberModel(0, 0, stocksOffered.ge));
        setLocation(400, 200);
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
        int number = Integer.parseInt(numberSpinner.getValue().toString());
        if(!customer.getCustomerSecurityAcct().buyStock(customer, stock.getStockName(), stock.getStockSymbol(), stock.getStockPrice(), number, bank.getDbManger())) {
            JOptionPane.showMessageDialog(contentPane, "Failed to buy the stock.");
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
