import javax.swing.*;

public abstract class Screen extends JFrame {

    protected Bank bank;

    Screen(Bank bank) {
        super("Bank ATM");
        this.bank = bank;
//        locateScreen();
    }

    protected void locateScreen() {setLocationRelativeTo(null);}

    protected void close() {
        this.dispose();
    }


}
