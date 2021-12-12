import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LoanScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel loanPane;
    private JButton requestALoanButton;
    private JLabel customer;
    private List<LoanItem> loanItems;

    public LoanScreen(Bank bank) {
        super(bank);

        loanItems = new ArrayList<LoanItem>();
        loanPane.setLayout(new BoxLayout(loanPane, BoxLayout.Y_AXIS));
        Customer customer1 = (Customer) bank.getLoggedUser();
        updateAccountItems(customer1.getLoans());
        customer.setText(bank.getLoggedUser().getName());
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

        requestALoanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new RequestLoanDialog(bank);
            }
        });
    }

    @Override
    public void updateItems() {

    }


    private void updateAccountItems(List<Loan> loans) {
        loanItems.clear();
        loanPane.removeAll();
        if(loans == null) {
//            loanItems = new ArrayList<LoanItem>();
            return;
        }
        for(Loan loan: loans) {
            LoanItem loanItem = new LoanItem(bank, loan, this);
            loanItems.add(loanItem);
            loanPane.add(loanItem.getMainPanel());
        }
    }

}
