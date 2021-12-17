import java.text.SimpleDateFormat;

public class BankConstants {
	private static double openAccountFee = 10.00;
	private static double closeAccountFee = 20.00;
	private static double minOpenAccountBalance = 100.00;
	private static double minOpenSavingAccountBalanceForSecurities = 5000.00;
	private static double minOpenSecuritiesAccountBalance = 1000.00;
	private static double savingsInterestPercentage = 0.03;
	private static double loanInterestPercentage = 0.1;
	private static double transactionFeeRate = 0.05;
	private static double withDrawFeePercentage = 0.01;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final  String BANK_NAME = "CPK Bank";
	public static final String BANK_FILE_PATH = System.getProperty("user.dir") + "/src/";
	//public static final String BANK_FILE_PATH = System.getProperty("user.dir");



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
		minOpenSavingAccountBalanceForSecurities = fee;
		return true;
	}

	public static double getMinOpenSavingAccountBalanceForSecurities() {
		return minOpenSavingAccountBalanceForSecurities;
	}
	
	public static double getTransactionFeeRate() {
		return transactionFeeRate;
	}
}
