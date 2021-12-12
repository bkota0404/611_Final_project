import javax.swing.*;

public abstract class UIItem extends JFrame implements UIOwnable{

//    protected JPanel mainPanel;
    protected Screen parentScreen;

    public UIItem(Screen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void setParentScreen(Screen parentScreen) {
        this.parentScreen = parentScreen;
    }
}
