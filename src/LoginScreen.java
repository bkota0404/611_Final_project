import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends Screen{
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton signUpButton;

    public LoginScreen(Bank bank) {
        super(bank);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(400, 200);
        setVisible(true);

        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if(bank.login(username, password)) {
                    User user = bank.getLoggedUser();
                    switch (user.getUserRole()) {
                        case CUSTOMER:
                            new CustomerScreen(bank);
                            break;
                        case MANAGER:
                            new ManagerScreen(bank);
                            break;
                    }
                    close();
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Username or password is wrong. Please input again.");
                }
            }
        });
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // set up sign-up window
                new SignUpScreen(bank);
                // close the log in window
                close();
            }
        });
    }


}
