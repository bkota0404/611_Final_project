import javax.swing.*;

public class CustomerDetailsScreen extends ItemScreen{
    private JPanel mainPanel;
    private JLabel userID;
    private JLabel name;
    private JPanel accountPanel;
    private JPanel loanPanel;
    private JPanel stockPanel;
    private Customer customer;


    public CustomerDetailsScreen(Customer customer) {
        super(null);
        this.customer = customer;

        userID.setText(String.valueOf(customer.getUserId()));
        name.setText(customer.getName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 1000);
        setLocation(400, 100);
        setVisible(true);

        for(Account account: customer.getAccounts()) {
            accountPanel.add(new AccountItem(bank, account, this).getMainPanel());
        }
        for(Loan loan: customer.getLoans()) {
            loanPanel.add(new LoanItem(bank, loan, this).getMainPanel());
        }
        for(StocksPurchased stock: customer.getCustomerSecurityAcct().getStocksPurchased()) {
            stockPanel.add(new StockPurchasedItem(bank, stock, this).getMainPanel());
        }

    }

    @Override
    public void refresh() {

    }
}
