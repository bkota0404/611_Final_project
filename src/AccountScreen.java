import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AccountScreen extends Screen{


    private JPanel mainPanel;
    private JButton viewChargingRulesButton;
    private JLabel customer;
    private JPanel accountPanel;
    private JButton createAccountButton;
    private List<AccountItem> accountItems;

    public AccountScreen(Bank bank, List<Account> accounts) {
        super(bank);

        updateAccountItems(accounts);
        customer.setText(bank.getLoggedUser().getName());
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));

        viewChargingRulesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ChargingRulesDialog();
            }
        });

        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CreateAccountDialog();
            }
        });
    }



    private void addAccountItem(Account account) {
        AccountItem accountItem = new AccountItem(account, this);
        accountItems.add(accountItem);
        accountPanel.add(accountItem);
    }


    private void updateAccountItems(List<Account> accounts) {
        accountPanel.removeAll();
        if(accounts == null) {
            accountItems = null;
            return;
        }
        for(Account account: accounts) {
            AccountItem accountItem = new AccountItem(account, this);
            accountItems.add(accountItem);
            accountPanel.add(accountItem);
        }
    }



}
