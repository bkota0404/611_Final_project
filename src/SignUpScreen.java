import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpScreen extends Screen{
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField reEnterPasswordField;
    private JButton createYourAccountButton;

    SignUpScreen(Bank bank) {
        super(bank);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(400, 200);
        setVisible(true);

        createYourAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String password = String.valueOf(passwordField.getPassword());
                String reEnterPassword = String.valueOf(reEnterPasswordField.getPassword());
                if(!password.equals(reEnterPassword)) {
                    JOptionPane.showMessageDialog(null, "The entered passwords are inconsistent, please input again.");
                    passwordField.setText("");
                    reEnterPasswordField.setText("");
                    return;
                }
                if(bank.signup(nameField.getText(), usernameField.getText(), password)) {
                    //bank.getUserCreation().createUser(nameField.getText(),usernameField.getText(),password, UserRoles.CUSTOMER,bank.getDbManger());
                    int choice = JOptionPane.showConfirmDialog(mainPanel, "Succeed. Log in with this account?", "Option Dialog", JOptionPane.YES_NO_OPTION);
                    switch (choice) {
                        case 0:
                            bank.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                            new CustomerScreen(bank);
                            break;
                        case 1:
                            new LoginScreen(bank);
                            break;
                    }
                    close();
                }
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Failed to sign up! Please change username.");
                }
            }
        });

    }

}
