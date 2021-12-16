public abstract class ItemScreen extends Screen{

    public ItemScreen(Bank bank) {
        super(bank);
    }

    public abstract void refresh();

}
