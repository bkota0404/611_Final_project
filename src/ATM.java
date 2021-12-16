public class ATM {
    private Bank bank;
    private String atmName;
    private int atmId;

    public ATM(String atmName) {
        this.atmName = atmName;
        Bank bank = new Bank(this.getAtmName());
        bank.startOperations();
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

}
