import javax.swing.*;
import java.awt.event.*;

public class ChargingRulesDialog extends JDialog {
    private JPanel contentPane;
    private JButton gotItButton;
    private JLabel rule1Label;
    private JLabel rule2Label;
    private JLabel rule3Label;
//    private JButton buttonCancel;

    public ChargingRulesDialog() {
        setContentPane(contentPane);
        setModal(true);
        setSize(500, 300);
        setLocation(400, 200);
        getRootPane().setDefaultButton(gotItButton);

        rule1Label.setText("1. " + BankConstants.getOpenAccountFee() + " will be charged for every time an account is opened or closed. ");
        rule2Label.setText("2. " + BankConstants.getTransactionFeeRate() + " of amount will be charged for every time checking account transaction.");
        rule3Label.setText("3. " + BankConstants.getWithDrawFeePercentage() + " of amount will be charged for every time withdrawal is made.");

        gotItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        pack();
        setVisible(true);
//        buttonCancel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
