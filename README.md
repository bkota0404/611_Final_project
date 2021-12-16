# CS611-ATM(Final project)

Name
-------------------------------------------------------------------------------------------------
--Bhagyasri Kota--
--U63334155--  
--Haoyu Zhang--
--U81614811--  
--Qingyuan Zhang--
--U07172373--  


Files
-------------------------------------------------------------------------------------------------
***Added space for more readability***  

**Back-end**  
1. Main.java - Main class used start the run and call the game selection class

2. ATM.java - Its like an interface for the user to Bank created for the purpose giving ATM effect and also can used incase of other functions exclusive for the ATMs to be scalable

3. Bank.java - Class that will be called from within ATM for the user to access the database, create account, signup, login or display info etc.,

4. Account.java - Class that stores account information and corresponding methods  
5. AccountCreation.java - Used to create account in database using information passed from front-end  
6. AccountType.java - Enum class storing acconut type  
7.  BankConstants.java - Stores constants such as open account fee, etc.  
8.  BankRepository.java - Bank repository interface with generic type.    
9.  CheckingsAccount.java - Class for checking account. Extend Account
10. CheckingsAccountCreation.java - Used to create checking account in database using information passed from front-end. Extend AccountCreation  
11. CurrenciesOffered .java- Currencies offered. Extend BankRepository.  
12. Currency.java - Currency setting and exchanging rate  
13. CurrencyType.java - Enum to store currency type  
14. Customer.java - Customer class. Extend User   
15. DBManager.java - Class that linked to database providing initialization and query.  
16. HighInterestEarningAccounts.java - Interface for account with interest   
17. ListToFile.java - Parsing list to file  
18. Loan.java - Loan Class  
19. LoanStatus.java - Enum for Loan Status.    
20. Manager.java - Manager Class. Extends User  
21. ManagerAccount.java - Manager Account class. Extends Account    
22. SavingsAccount.java - Savings Account class. Extends Account  
23. SavingsAccountCreation.java - Used to create savings account in database using information passed from front-end. Extend AccountCreation    
24. SecuritiesAccount.java - Securities Account class. Extends Account  
25. SecuritiesAcntCreation.java - Used to create securities account in database using information passed from front-end. Extend AccountCreation  
26. Stocks.java - Stock Class    
27. StocksOffered.java - Stocks Offered class.  Implements BankRepository  
28. StocksPurchased.java - A class that records purchased stocks  
29. Transaction.java - Transaction class that records each transaction information   
30. TransactionType.java - Enum storing transaction type    
31. User.java - User Class  
32. UserCreation.java -  Used to create user in database using information passed from front-end. Implements BankRepository  
33. UserRoles.java - Enum to record the user roles   

**UI**  
1. AccountItem.java - Account items from UI  
   
2. AccountScreen.java - Account screen items from UI 
   
3. BuyStockDialog.java - Buy stock UI.  
4. ChargingRulesDialog.java - Charging rules UI. 
5. CreateAccountDialog.java - Create Account UI  
6. CheckCustomersScreen.java - Check customers UI, extends ItemScreen. 
7. CreateSecuritiesAccountDialog.java - Create security account UI. Extend JDialog 
8. CustomerDetailsScreen.java - Customer Details Screen UI  
9.  CustomerItem.java - Customer Item UI. Extend UIItem  
10. CustomerScreen.java - Customer Screen UI. Extend Screen  
11. DailyReportScreen.java - Daily Report Screen UI. Extend ItemScreen 
12. ItemScreen.java - Item Screen UI. Extends Screen 
13. LoanItem.java - Loan Item UI. Extends UIItem  
14. LoanScreen.java - Loan Screen UI. Extends ItemScreen  
15. LoginScreen.java - Login Screen UI. Extends Screen  
16. ManagerScreen.java - Manager Screen UI. Extends Screen 
17. Music.java - Music Class. Extends JFrame  
18. PayBackLoanDialog.java - Pay Back Loan UI. Extends JDialog  
19. RequestLoanDialog.java - Request Loan UI. Extends JDialog  
20. Screen.java - Screen UI. entends JFrame  
21. SellStockDialog.java - Sell Stock UI. Extends JDialog  
22. SignUpScreen.java - Sign Up Screen UI. Extends Screen  
23. StockManagementItem.java - Stock Management Item UI  
24. StockMarketManagementScreen.java - Stock Market Management Screen UI. Extends ItemScreen  
25. StockOfferedItem.java - Stock Offered Item UI  
26. StockPurchasedItem.java - Stock Purchased Item UI. Extends UIItem  
27. StockScreen.java - Stock Screen UI. Extends ItemScreen  
28. TransactionItem.java - Transaction Item UI. Extends UIItem  
29. TransactionScreen.java - Transaction Screen UI. Extends ItemScreen  
30. TransferDialog.java - Transfer UI. Extends Dialog  
31. UIItem.java - Base Item UI. Extends JFrame implements UIOwnable  
32. UIOwnable.java - Interface to determine the ownership of screen  
33. WithDrawlDepositDialog.java Withdrawl Deposit UI. Extends Dialog 


Notes:
-------------------------------------------------------------------------------------------------
1. Files to be parsed should be stored in ConfigFiles, for parser class to read class along with the .wav file for music to be played

2. Bonus Implementations:
	a. Parser created to read the json response from the stock api and write into file
	b. Welcome message will be played as soon as the ATM screen is displayed.
	c. Repository Pattern implemented to separate entities and the Dbstorage. We have created a BankRepository which will act as an master interface and also specific repositories for each entity like Account, User, Stocks etc.,. These interfaces named AccountCreation, UserCreation extends the main repository and is used to create successful records in db and as keep the data layer clean. 

3. The code can be run in terminal but not on eclipse or IntelliJ workspace since the path is not consistent for the both. Followed the convention mentioned on piazza but modified the path to System.getProperty("user.dir") + "/database/" since adding src to the path will cause issues on terminal.

4. Platform Used - MAC


How to run:
-------------------------------------------------------------------------------------------------
1. Navigate to the directory after downloading the project
2. Run the following instructions on command line:
	javac *.java
	java Main.java
