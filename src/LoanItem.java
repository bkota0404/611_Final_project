import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoanItem extends UIItem{

    private JPanel mainPanel;
    private JLabel amount;
    private JLabel currency;
    private JLabel collateral;
    private JLabel status;
    private JButton payBackLoanButton;

    public LoanItem(Bank bank, Loan loan, Screen parentScreen) {
        super(parentScreen);

        amount.setText(String.valueOf(loan.getAmount()));
        currency.setText(loan.getCurrency());
        collateral.setText(loan.getCollateral());
        status.setText(loan.getLoanStatus().toString());
        payBackLoanButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new PayBackLoanDialog(bank, loan);
            }
        });
    }


    public JPanel getMainPanel() {return mainPanel;}

}
