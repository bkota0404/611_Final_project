import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerScreen extends Screen {

    private JPanel mainPanel;
    private JLabel manager;
    private JButton logOutButton;
    private JButton viewDailyReportButton;
    private JButton checkCustomersButton;
    private JButton stockMarketButton;

    public ManagerScreen(Bank bank) {
        super(bank);

        manager.setText(bank.getLoggedUser().getUserName());
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(400, 200);
        setVisible(true);

        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                bank.logout();
            }
        });
    }
}
