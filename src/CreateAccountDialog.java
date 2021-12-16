import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateAccountDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox typeCombo;
    private JComboBox currencyCombo;
    private JSpinner amountSpinner;
    private JLabel tipsLabel;
    private Bank bank;
    private ItemScreen parentScreen;

    public CreateAccountDialog(Bank bank, ItemScreen parentScreen) {
        this.parentScreen = parentScreen;
        this.bank = bank;
        setContentPane(contentPane);
        setModal(true);
        setSize(400, 300);
        setLocation(400, 200);
        getRootPane().setDefaultButton(buttonOK);

        typeCombo.addItem("Savings Account");
        typeCombo.addItem("Checking Account");

        List<String> currencyTypeList = Stream.of(CurrencyType.values()).map(CurrencyType::name).collect(Collectors.toList());
        for(String type: currencyTypeList)
            currencyCombo.addItem(type);
        amountSpinner.setModel(new SpinnerNumberModel(100, BankConstants.getMinOpenAccountBalance(), 1000000, 1));
        tipsLabel.setText("Minimum amount is " + BankConstants.getMinOpenAccountBalance() + ".") ;


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

        buttonCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Cancel");
                onCancel();
            }
        });
        buttonOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                onOK();
            }
        });
        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        double amount = Double.valueOf(amountSpinner.getValue().toString());
        AccountType accountType = null;
        CurrencyType currencyType = null;
        switch (typeCombo.getSelectedIndex()) {
            case 0:
                accountType = AccountType.SAVINGS;
                break;
            case 1:
                accountType = AccountType.CHECKING;
                break;
        }
        switch (currencyCombo.getSelectedIndex()) {
            case 0:
                currencyType = CurrencyType.USD;
                break;
            case 1:
                currencyType = CurrencyType.CAD;
                break;
            case 2:
                currencyType = CurrencyType.GBP;
                break;
        }
        if(!bank.createAccount(currencyType, amount - BankConstants.getOpenAccountFee(), accountType)) {
            JOptionPane.showMessageDialog(contentPane, "Failed to create the account.");
            return;
        }
        parentScreen.refresh();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
