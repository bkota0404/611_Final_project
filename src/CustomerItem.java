import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerItem extends UIItem{

    private JPanel mainPanel;
    private JButton detailsButton;
    private JLabel userID;
    private JLabel name;

    public CustomerItem(Customer customer, Screen parentScreen) {
        super(parentScreen);

        userID.setText(String.valueOf(customer.getUserId()));
        name.setText(customer.getName());
        detailsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CustomerDetailsScreen(customer);
            }
        });
    }

    public JPanel getMainPanel() {return mainPanel;}

}
