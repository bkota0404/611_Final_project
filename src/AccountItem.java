import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountItem extends UIItem{

    private Account account;
    private JLabel accountID;
    private JLabel accountType;
    private JLabel currency;
    private JLabel balance;
    private JButton closeAccountButton;
    private JButton withdrawlDepositButton;
    private JButton loanButton;

    public AccountItem(Account account, Screen parentScreen) {
        super(parentScreen);

        this.account = account;
        initialize();
        closeAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        withdrawlDepositButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        loanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }


    private void initialize() {
        accountID.setText(String.valueOf(account.getAccountId()));
        accountType.setText(account.getAccountType().getAccountType());
        currency.setText(account.getCurrency());
        balance.setText(String.valueOf(account.getBalance()));
    }

}
