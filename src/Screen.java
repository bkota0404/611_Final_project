import javax.swing.*;

public abstract class Screen extends JFrame {

    protected Bank bank;

    Screen(Bank bank) {
        super("Bank ATM");
        this.bank = bank;
    }


    protected void close() {
        this.dispose();
    }


}
