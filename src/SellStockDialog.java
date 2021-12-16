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

    public SellStockDialog(StocksPurchased stocksPurchased, Customer customer, ItemScreen parentScreen) {

        this.customer = customer;
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
    }

    private void onOK() {
        // add your code here
        int number = Integer.parseInt(amountSpinner.getValue().toString());
        //
//        if(!customer.sellStock(stocksPurchased, number)) {
//            JOptionPane.showMessageDialog(this, "Failed to sell the stock.");
//            return;
//        }
        parentScreen.refresh();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
