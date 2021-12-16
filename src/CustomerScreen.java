import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
        Customer customer = (Customer) bank.getLoggedUser();
        viewStockButton.setEnabled(customer.isQualifiedForSecurities());
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
                if(customer.isQualifiedForSecurities())
                    checkFirstTimeEnterStock();
            }
        });
        mainPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                return;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                viewStockButton.setEnabled(customer.isQualifiedForSecurities());
            }
        });
    }


    private void refresh() {
        close();
        new CustomerScreen(bank);
    }


    private void checkFirstTimeEnterStock() {
        Customer customer = (Customer) bank.getLoggedUser();
        if(customer.getCustomerSecurityAcct() == null) {
            new CreateSecuritiesAccountDialog(bank);
        }
        else {
            new StockScreen(bank);
        }
    }

}
