import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel transactionPane;
    private JLabel customer;
    private List<TransactionItem> transactionItems;

    public TransactionScreen(Bank bank) {
        super(bank);

        transactionItems = new ArrayList<TransactionItem>();
        Customer customer1 = (Customer)bank.getLoggedUser();
        updateTransactionItems(customer1.getTransactions());
        transactionPane.setLayout(new BoxLayout(transactionPane, BoxLayout.Y_AXIS));
        customer.setText(bank.getLoggedUser().getName());
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);


    }

    @Override
    public void updateItems() {

    }


    private void updateTransactionItems(List<Transaction> transactions) {
        transactionItems.clear();
        transactionPane.removeAll();
        if(transactions == null) {
//            transactionItems = new ArrayList<TransactionItem>();
//            System.out.println("no account");
            return;
        }
        for(Transaction transaction: transactions) {
            TransactionItem transactionItem = new TransactionItem(transaction, this);
            transactionItems.add(transactionItem);
            transactionPane.add(transactionItem);
        }
    }

}
