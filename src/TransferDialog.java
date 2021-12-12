import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TransferDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox fromCombo;
    private JComboBox toCombo;
    private JSpinner amountSpinner;
    private JLabel tipLabel;
    private Bank bank;

    public TransferDialog(Bank bank) {

        initialize();
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
        double amount = Double.valueOf(amountSpinner.getValue().toString());
        String from = (String) fromCombo.getItemAt(fromCombo.getSelectedIndex());
        String to = (String) toCombo.getItemAt(toCombo.getSelectedIndex());
        Account fromAccount = null;
        Account toAccount = null;
        Customer customer = (Customer) bank.getLoggedUser();
//        switch (from) {
//            case "SAVINGS":
//                fromAccount = customer.ge
//            case "CHECKING":
//                fromAccount =
//            case "SECURITIES":
//                fromAccount =
//        }
//        bank.transfer()
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void initialize() {
        tipLabel.setText(BankConstants.getTransactionFeeRate() + " of amount will be charged for every time transaction.");
        amountSpinner.setModel(new SpinnerNumberModel(50, 10, 1000000, 1));
        Customer customer = (Customer) bank.getLoggedUser();
        for(Account account: customer.getAccounts()) {
            switch (account.getAccountType()) {
                case SAVINGS:
                    fromCombo.addItem("SAVINGS");
                    toCombo.addItem("SAVINGS");
                    break;
                case CHECKING:
                    fromCombo.addItem("CHECKING");
                    toCombo.addItem("CHECKING");
                    break;
                case SECURITIES:
                    fromCombo.addItem("SECURITIES");
                    toCombo.addItem("SECURITIES");
                    break;
            }
        }
    }

}
