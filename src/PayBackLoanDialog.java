import javax.swing.*;
import java.awt.event.*;

public class PayBackLoanDialog extends JDialog {
    private JPanel contentPane;
    private JButton payBackButton;
    private JButton buttonCancel;
    private JSpinner amountSpinner;
    private JComboBox accountCombo;
    private Bank bank;
    private Loan loan;

    public PayBackLoanDialog(Bank bank, Loan loan) {

        this.bank = bank;
        this.loan = loan;
        double loanAmount = loan.getAmount();
        Customer customer = (Customer) bank.getLoggedUser();
        for(Account account: customer.getAccounts()) {
            accountCombo.addItem(account);
        }
        amountSpinner.setModel(new SpinnerNumberModel(loanAmount, 0, loanAmount, 1));
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(payBackButton);


        payBackButton.addActionListener(new ActionListener() {
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
        Account account = (Account) accountCombo.getItemAt(accountCombo.getSelectedIndex());
        if(bank.payBackLoan(loan, account, amount)) {
            JOptionPane.showMessageDialog(contentPane, "Paying back loan succeed.");
        }
        else {
            JOptionPane.showMessageDialog(contentPane, "Failed to pay back loan.");
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
