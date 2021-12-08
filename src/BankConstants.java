
public class BankConstants {
	private static double openAccountFee = 10.00;
	private static double closeAccountFee = 20.00;
	private static double minOpenAccountBalance = 100.00;
	private static double minOpenSecuritiesAccountBalance = 5000.00;
	private static double savingsInterestPercentage = 0.03;
	private static double loanInterestPercentage = 0.1;
	private static double withDrawFeePercentage = 0.01;

	
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
	
	public static double getCloseAccountFee() {
		return closeAccountFee;
	}
	
	public static double getSavingsInterestPercentage() {
		return savingsInterestPercentage;
	}
	
	public static double getLoanInterestPercentage() {
		return loanInterestPercentage;
	}
	
	public static double getWithDrawFeePercentage() {
		return withDrawFeePercentage;
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
