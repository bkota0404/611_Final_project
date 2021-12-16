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
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
        loanPanel.setLayout(new BoxLayout(loanPanel, BoxLayout.Y_AXIS));
        stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS));
        update();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 1000);
        setLocation(400, 100);
        setVisible(true);


    }

    @Override
    public void refresh() {

    }

    private void update() {
        accountPanel.removeAll();
        if(customer.getAccounts() != null) {
            for(Account account: customer.getAccounts()) {
                AccountItem accountItem = new AccountItem(bank, account, this);
                accountItem.getCloseAccountButton().setEnabled(false);
                accountItem.getWithdrawlDepositButton().setEnabled(false);
                accountPanel.add(accountItem.getMainPanel());
            }
        }
        loanPanel.removeAll();
        if(customer.getLoans() != null) {
            for(Loan loan: customer.getLoans()) {
                LoanItem loanItem = new LoanItem(bank, loan, this);
                loanItem.getPayBackLoanButton().setEnabled(false);
                loanPanel.add(loanItem.getMainPanel());
            }
        }
        stockPanel.removeAll();
        if(customer.getCustomerSecurityAcct() != null)
            if(customer.getCustomerSecurityAcct().getStocksPurchased() != null)
                for(StocksPurchased stock: customer.getCustomerSecurityAcct().getStocksPurchased()) {
                    StockPurchasedItem stockPurchasedItem = new StockPurchasedItem(bank, stock, this);
                    stockPurchasedItem.getSellStockButton().setEnabled(false);
                    stockPanel.add(stockPurchasedItem.getMainPanel());
                }
    }
}
