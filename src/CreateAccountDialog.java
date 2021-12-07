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

    public CreateAccountDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        typeCombo.addItem("Savings Account");
        typeCombo.addItem("Checking Account");
        List<String> currencyTypeList = Stream.of(CurrencyType.values()).map(CurrencyType::name).collect(Collectors.toList());
        for(String type: currencyTypeList)
            currencyCombo.addItem(type);
        amountSpinner.setModel(new SpinnerNumberModel(100, 50, 1000000, 1));

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
