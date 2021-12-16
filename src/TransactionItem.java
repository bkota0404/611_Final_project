import javax.swing.*;

public class TransactionItem extends UIItem{

    private JPanel mainPanel;
    private JLabel transactionType;
    private JLabel userID;
    private JLabel amount;
    private JLabel date;

    public TransactionItem(Transaction transaction, ItemScreen parentScreen) {
        super(parentScreen);

        transactionType.setText(transaction.getTransactionType().getTransactionName());
        userID.setText(String.valueOf(transaction.getUserID()));
        amount.setText(String.valueOf(transaction.getAmount()));
        date.setText(transaction.getDate().toString());
    }

    public JPanel getMainPanel() {return mainPanel;}


}
