
public class BankConstants {
	private static double openAccountFee = 100.00;
	private static double minOpenAccountBalance = 100.00;
	private static double minOpenSecuritiesAccountBalance = 5000.00;
	
	
	public static double getOpenAccountFee() {
		return openAccountFee;
	}
	
	public static boolean setOpenAccountFee(double fee) {
		if (fee < 0) {
			return false;
		}
		openAccountFee = fee;
		return true;
	}
	
	public static double getMinOpenAccountBalance() {
		return minOpenAccountBalance;
	}
	
	public static boolean setMinOpenAccountBalance(double fee) {
		if (fee < 0) {
			return false;
		}
		minOpenAccountBalance = fee;
		return true;
	}
	
	public static double getMinOpenSecuritiesAccountBalance() {
		return minOpenSecuritiesAccountBalance;
	}
	
	public static boolean setMinOpenSecuritiesAccountBalance(double fee) {
		if (fee < 0) {
			return false;
		}
		minOpenSecuritiesAccountBalance = fee;
		return true;
	}
}
