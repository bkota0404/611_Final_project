import javax.swing.*;

public class StockScreen extends ItemScreen{


    private JPanel mainPanel;
    private JLabel customer;
    private JPanel stockPanel;

    public StockScreen(Bank bank) {
        super(bank);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    @Override
    public void refresh() {

    }


}
