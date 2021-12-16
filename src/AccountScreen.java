import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AccountScreen extends ItemScreen{


    private JPanel mainPanel;
    private JButton viewChargingRulesButton;
    private JLabel customer;
    private JPanel accountPanel;
    private JButton createAccountButton;
    private JButton transferButton;
    private List<AccountItem> accountItems;

    public AccountScreen(Bank bank) {
        super(bank);

        accountItems = new ArrayList<AccountItem>();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
        Customer customer1 = (Customer) bank.getLoggedUser();
        updateAccountItems(customer1.getAccounts());
        customer.setText(bank.getLoggedUser().getName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);


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
                createAccountDialog();
            }
        });
        transferButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TransferDialog(bank);
            }
        });
    }

    @Override
    public void refresh() {
        close();
        new AccountScreen(bank);
    }

    private void createAccountDialog() {
        new CreateAccountDialog(bank, this);
    }


    private void addAccountItem(Account account) {
        AccountItem accountItem = new AccountItem(bank, account, this);
        accountItems.add(accountItem);
        accountPanel.add(accountItem.getMainPanel());
    }


    private void updateAccountItems(List<Account> accounts) {
        accountItems.clear();
        accountPanel.removeAll();
        if(accounts.size() == 0) {
//            accountItems = new ArrayList<AccountItem>();
//            System.out.println("no account");
            return;
        }
        for(Account account: accounts) {
            AccountItem accountItem = new AccountItem(bank, account, this);
            accountItems.add(accountItem);
            accountPanel.add(accountItem.getMainPanel());
        }
    }



}
