import javax.swing.*;
import java.util.List;

public class CheckCustomersScreen extends ItemScreen{
    private JPanel mainPanel;
    private JPanel customerPanel;
    private JLabel manager;

    public CheckCustomersScreen(Bank bank) {
        super(bank);

        manager.setText(bank.getLoggedUser().getName());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 700);
        setLocation(400, 100);
        setVisible(true);

        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.Y_AXIS));

        List<Customer> customers = bank.getAllCustomers();
        if(customers.size() > 0) {
            for (Customer customer : customers) {
                customerPanel.add(new CustomerItem(customer, this).getMainPanel());
            }
        }
    }

    @Override
    public void refresh() {
        close();
        new CheckCustomersScreen(bank);
    }

}
