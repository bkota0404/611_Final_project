import javax.swing.*;
import java.util.List;

public class CheckCustomersScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel customerPanel;

    public CheckCustomersScreen(Bank bank) {
        super(bank);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.Y_AXIS));

        // TODO method bank.getAllCustomers()
//        List<Customer> customers = bank.getAllCustomers();
//        for (Customer customer : customers) {
//            customerPanel.add(new CustomerItem(customer, this));
//        }
    }

    @Override
    public void refresh() {

    }
}
