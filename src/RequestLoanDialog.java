import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestLoanDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner amountSpinner;
    private JTextField collateralField;
    private JComboBox currencyCombo;
    private Bank bank;

    public RequestLoanDialog(Bank bank) {

        this.bank = bank;
        amountSpinner.setModel(new SpinnerNumberModel(100, 50, 1000000, 1));
        List<String> currencyTypeList = Stream.of(CurrencyType.values()).map(CurrencyType::name).collect(Collectors.toList());
        for(String type: currencyTypeList)
            currencyCombo.addItem(type);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        //CurrencyType currencyType = (CurrencyType) currencyCombo.getItemAt(currencyCombo.getSelectedIndex());
        String currency = (String) currencyCombo.getItemAt(currencyCombo.getSelectedIndex());
        CurrencyType currencyType = CurrencyType.getEnum(currency);
        double amount = Double.valueOf(amountSpinner.getValue().toString());
        if(!bank.requestLoan(currencyType, amount, collateralField.getText())) {
            JOptionPane.showMessageDialog(contentPane, "Failed to request a loan.");
            return;
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
