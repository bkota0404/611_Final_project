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

        setSize(600, 1000);
        setLocation(400, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        userID.setText(String.valueOf(customer.getUserId()));
        name.setText(customer.getName());

        for(Account account: customer.getAccounts()) {
            accountPanel.add(new AccountItem(null, account, this).getMainPanel());
        }
        for(Loan loan: customer.getLoans()) {
            loanPanel.add(new LoanItem(null, loan, this).getMainPanel());
        }
        for(StocksPurchased stock: customer.getCustomerSecurityAcct().getStocksPurchased()) {
            stockPanel.add(new StockPurchasedItem(stock, this).getMainPanel());
        }

    }

    @Override
    public void updateItems() {

    }
}
