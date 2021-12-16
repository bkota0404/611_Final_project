import javax.swing.*;
import java.util.List;

public class DailyReportScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel reportPanel;
    private JLabel manager;

    public DailyReportScreen(Bank bank) {
        super(bank);

        manager.setText(bank.getLoggedUser().getName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

        List<Transaction> transactions = bank.getDailyTransactions();
        if(transactions.size() > 0) {
            for (Transaction transaction : transactions) {
                reportPanel.add(new TransactionItem(transaction, this).getMainPanel());
            }
        }

    }

    @Override
    public void refresh() {

    }
}
