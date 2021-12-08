import javax.swing.*;

public abstract class UIItem extends JFrame implements UIOwnable{

    protected Screen parentScreen;

    public UIItem(Screen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void setParentScreen(Screen parentScreen) {
        this.parentScreen = parentScreen;
    }
}
