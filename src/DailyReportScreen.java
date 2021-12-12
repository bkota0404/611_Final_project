import javax.swing.*;
import java.util.List;

public class DailyReportScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel reportPanel;

    public DailyReportScreen(Bank bank) {
        super(bank);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

        // TODO method bank.getDailyTransactions
//        List<Transaction> transactions = bank.getDailyTransactions();
//        for (Transaction transaction : transactions) {
//            reportPanel.add(new TransactionItem(transaction, this).getMainPanel());
//        }
    }

    @Override
    public void updateItems() {

    }
}
