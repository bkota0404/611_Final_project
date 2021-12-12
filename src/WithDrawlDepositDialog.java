import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WithDrawlDepositDialog extends JDialog {
    private JPanel mainPanel;
    private JButton withdrawButton;
    private JButton buttonCancel;
    private JButton depositButton;
    private JSpinner amountSpinner;
    private JLabel tips;
    private JLabel tip1;
    private JLabel tip2;
    private Bank bank;
    private Account account;

    public WithDrawlDepositDialog(Bank bank, Account account) {
        amountSpinner.setModel(new SpinnerNumberModel(100, 10, 1000000000, 1));
        tip1.setText("1. " + BankConstants.getWithDrawFeePercentage() + " of amount will be charged for every time withdrawal is made.");
        tip2.setText("2. 10 at least for every time deposit or withdraw.");
        setContentPane(mainPanel);
        setModal(true);
        getRootPane().setDefaultButton(withdrawButton);

        this.bank = bank;
        this.account = account;

        buttonCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        depositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                deposit();
            }
        });
        withdrawButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                withdraw();
            }
        });
        pack();
        setVisible(true);
    }

    private void deposit() {
        double amount = Double.valueOf(amountSpinner.getValue().toString());
        if(!bank.deposit(account, amount)) {
            JOptionPane.showMessageDialog(mainPanel, "Failed to deposit.");
            return;
        }
        dispose();
    }


    private void withdraw() {
        double amount = Double.valueOf(amountSpinner.getValue().toString());
        if(!bank.withdraw(account, amount)) {
            JOptionPane.showMessageDialog(mainPanel, "Failed to withdraw.");
            return;
        }
        dispose();
    }

}
