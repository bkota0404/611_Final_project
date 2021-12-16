import javax.swing.*;

public abstract class UIItem extends JFrame implements UIOwnable{

//    protected JPanel mainPanel;
    protected ItemScreen parentScreen;

    public UIItem(ItemScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void setParentScreen(ItemScreen parentScreen) {
        this.parentScreen = parentScreen;
    }
}
