import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountItem extends UIItem{

    private JPanel mainPanel;
    private Account account;
    private JLabel accountID;
    private JLabel accountType;
    private JLabel currency;
    private JLabel balance;
    private JButton closeAccountButton;
    private JButton withdrawlDepositButton;
    private Bank bank;

    public AccountItem(Bank bank, Account account, Screen parentScreen) {
        super(parentScreen);

        this.bank = bank;
        this.account = account;
        initialize();
        closeAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(bank.closeAccount(account)) {
                    JOptionPane.showMessageDialog(mainPanel, "Succeed to close the account.");
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Balance not enough to close account.");
                }
            }
        });
        withdrawlDepositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new WithDrawlDepositDialog(bank, account);
            }
        });
    }


    private void initialize() {
        accountID.setText(String.valueOf(account.getAccountId()));
        accountType.setText(account.getAccountType().getAccountType());
        currency.setText(account.getCurrency());
        balance.setText(String.valueOf(account.getBalance()));
    }

    public JPanel getMainPanel() {return mainPanel;}


}
