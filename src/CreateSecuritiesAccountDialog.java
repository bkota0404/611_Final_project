import javax.swing.*;
import java.awt.event.*;

public class CreateSecuritiesAccountDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner amountSpinner;
    private Bank bank;

    public CreateSecuritiesAccountDialog(Bank bank) {

        this.bank = bank;
        int min = (int) BankConstants.getMinOpenSecuritiesAccountBalance();
        amountSpinner.setModel(new SpinnerNumberModel(2000, min, 1000000, 1));
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocation(400, 200);

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
        Customer customer = (Customer) bank.getLoggedUser();
        double amount = Double.valueOf(amountSpinner.getValue().toString());
        bank.createAccount(CurrencyType.USD, amount, AccountType.SECURITIES);
        new StockScreen(bank);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
