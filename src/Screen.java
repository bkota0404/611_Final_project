import javax.swing.*;
import java.awt.event.WindowEvent;

public abstract class Screen extends JFrame {

    protected Bank bank;

    Screen(Bank bank) {
        super("Bank ATM");
        this.bank = bank;
    }


    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }


}
