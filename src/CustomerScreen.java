import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerScreen extends Screen{
    private JPanel mainPanel;
    private JLabel customer;
    private JButton logOutButton;
    private JButton viewAccountsButton;
    private JButton viewLoansButton;
    private JButton viewTransactionsButton;
    private JButton viewStockButton;

    public CustomerScreen(Bank bank) {
        super(bank);

        customer.setText(bank.getLoggedUser().getName());
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(400, 200);
        setVisible(true);


        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                bank.logout();
                close();
            }
        });
        viewAccountsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                createAccountScreen();
            }
        });
        viewTransactionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        viewLoansButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }



    private void createAccountScreen() {
        Customer customer = (Customer) bank.getLoggedUser();
//        List<UIItem> items = new ArrayList<UIItem>();
//        for(Account account: customer.getAllAccounts()) {
//            items.add(new AccountItem(customer, account));
//        }
        new AccountScreen(bank, customer.getAllAccounts());
    }

}
