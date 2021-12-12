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
//                new LoginScreen(bank);
            }
        });
        viewAccountsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new AccountScreen(bank);
//                close();
            }
        });
        viewTransactionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TransactionScreen(bank);
            }
        });
        viewLoansButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new LoanScreen(bank);
            }
        });
        viewStockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new StockScreen(bank);
            }
        });
    }



//    private void createAccountScreen() {
//        Customer customer = (Customer) bank.getLoggedUser();
//
//    }



}
