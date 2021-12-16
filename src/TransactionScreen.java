import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel transactionPanel;
    private JLabel customer;
    private List<TransactionItem> transactionItems;

    public TransactionScreen(Bank bank) {
        super(bank);

        transactionItems = new ArrayList<TransactionItem>();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        Customer customer1 = (Customer) bank.getLoggedUser();
        updateTransactionItems(customer1.getTransactions());
        customer.setText(bank.getLoggedUser().getName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

    }

    @Override
    public void refresh() {

    }


    private void updateTransactionItems(List<Transaction> transactions) {
        transactionItems.clear();
        transactionPanel.removeAll();
        if(transactions.size() == 0) {
//            transactionItems = new ArrayList<TransactionItem>();
//            System.out.println("no account");
            return;
        }
        for(Transaction transaction: transactions) {
            TransactionItem transactionItem = new TransactionItem(transaction, this);
            transactionItems.add(transactionItem);
            transactionPanel.add(transactionItem.getMainPanel());
        }
    }

}
