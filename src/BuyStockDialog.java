import javax.swing.*;
import java.awt.event.*;

public class BuyStockDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner numberSpinner;
    private Customer customer;
    private ItemScreen parentScreen;

    public BuyStockDialog(StocksOffered stocksOffered, Customer customer, ItemScreen parentScreen) {

        this.customer = customer;
        this.parentScreen = parentScreen;
//        numberSpinner.setModel(new SpinnerNumberModel(0, 0, stocksOffered.ge));
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
        //
//        if(!customer.buyStock()) {
//            JOptionPane.showMessageDialog(contentPane, "Failed to buy the stock.");
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
