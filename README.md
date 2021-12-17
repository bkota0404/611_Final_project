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

**Back-end**
1. Main.java - Main class used start the run and call the game selection class
2. ATM.java - Its like an interface for the user to Bank created for the purpose giving ATM effect and also can used incase of other functions exclusive for the ATMs to be scalable
3. Bank.java - Class that will be called from within ATM for the user to access the database, create account, signup, login or display info etc.,
4. Account.java - Class that stores account information and corresponding attributes and methods like account balance, currency on which the account is operated, user whose account it is etc
5. AccountCreation.java - Used to create account in database using information passed from front-end which acts as a account repository as well with methods to delete and get the list etc.
6. AccountType.java - Enum class storing account type
7. BankConstants.java - Stores constants such as open account fee, transaction fee, etc.
8. BankRepository.java - Bank repository interface containing delete and getAllList methods used for class that owns bank repository.
9. CheckingsAccount.java - Class for checking account. Extend Account
10. CheckingsAccountCreation.java - Used to create checking account in database using information passed from front-end. Extend AccountCreation
11. CurrenciesOffered.java - Used to get currencies offered and delete certain currency (implements BankRepository)
12. Currency.java - Used to set and store current type with exchanging rate
13. CurrencyType.java - Enum class contains the values of the types of currency
14. Customer.java - Used to store customers' information including personal information as well as account/loan/stock information. Extend User
15. DBManager.java - Used to link to database providing initialization and query.
16. HighInterestEarningAccounts.java - Interface for account classes that have interests
17. ListToFile.java - A utility class parsing list to file
18. Loan.java - Used to store loan information for a certain user and account
19. LoanStatus.java - Enum for Loan Status -- open, closed.
20. Manager.java - Used to store manager information and their basic operation methods(get all customer accounts, stocks, etc). Extends User
21. ManagerAccount.java - Used for manager account. Extends Account
22. SavingsAccount.java - Used to manage saving account which entends Account and has interest(implemnets HighInterestEarningAccounts)
23. SavingsAccountCreation.java - Used to create savings account in database using information passed from front-end. Extend AccountCreation
24. SecuritiesAccount.java - Used to store user's stock information and manage (buy/sell) stocks. Extends Account
25. SecuritiesAcntCreation.java - Used to create securities account in database using information passed from front-end. Extend AccountCreation
26. Stocks.java - Used to manage stocks including stock ID, price, name, etc.
27. StocksOffered.java - Used to store the list of all offered stocks, stock prices, stock names, etc, implementing delete and getAllList methods(from interface BankRepository)
28. StocksPurchased.java - A class that records purchased stocks for a certain user and account
29. Transaction.java - Used to records each transaction information including users, date, acocunts, collateral information
30. TransactionType.java - An Enum contains all transaction type (withdrawl, deposit, loan open, stock sold, ...)
31. User.java - Used for users containing user role, name, username, password, user id.
32. UserCreation.java -  Used to create user in database using information passed from front-end. Implements BankRepository
33. UserRoles.java - Enum contains all user roles (manager, customer)

**UI**
1. AccountItem.java - Account items from UI
2. AccountScreen.java - Account screen items from UI
3. BuyStockDialog.java - Buy stock UI.
4. ChargingRulesDialog.java - Charging rules UI.
5. CreateAccountDialog.java - Create Account UI
6. CheckCustomersScreen.java - Check customers UI, extends ItemScreen.
7. CreateSecuritiesAccountDialog.java - Create security account UI. Extend JDialog
8. CustomerDetailsScreen.java - Customer Details Screen UI
9. CustomerItem.java - Customer Item UI. Extend UIItem
10. CustomerScreen.java - Customer Screen UI. Extend Screen
11. DailyReportScreen.java - Daily Report Screen UI. Extend ItemScreen
12. ItemScreen.java - Item Screen UI. Extends Screen
13. LoanItem.java - Loan Item UI. Extends UIItem
14. LoanScreen.java - Loan Screen UI. Extends ItemScreen
15. LoginScreen.java - Login Screen UI. Extends Screen
16. ManagerScreen.java - Manager Screen UI. Extends Screen
17. Music.java - contains logic to start, loop and stop the music during program execution. Extends JFrame
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
32. UIOwnable.java - Interface used for screen class which owns other screens
33. ViewAllLoanScreen.java - Display all loans UI. Extends ItemScreen
34. WithDrawlDepositDialog.java Withdrawl Deposit UI. Extends Dialog

**Jar Files**
Forms_rt.jar
Sqlite-jdbc-3.32.3.2.jar


**Data Files**
Stocks.csv


Notes:
-------------------------------------------------------------------------------------------------
1. Files to be parsed should be stored in src folder depending on the whether they are database or data files, for parser class to read class along with the .wav file for music to be played

2. Bonus Implementations:
   a. Parser created to read the json response from the stock api and write into file
   b. Welcome message will be played as soon as the ATM screen is displayed.
   c. Repository Pattern implemented to separate entities and the Dbstorage. We have created a BankRepository which will act as an master interface and also specific repositories for each entity like Account, User, Stocks etc.,. These interfaces named AccountCreation, UserCreation extends the main repository and is used to create successful records in db and as keep the data layer clean.
   d. GUI Implemented
   e. API created for stocks

3. To display the stock options and their prices, we leveraged the already available API from internet from financialmodelingprep and generated our own key which we used to call and receive response from the API in the form of JSON.

4. The structure from the above mentioned JSON is read and written into an CSV from which it is read to display on GUI. For this purpose we have created a separate ListToFile class which acts as a parser.

5. The code is tested to run on MAC computers due to unavailability of a Windows OS, we couldn't test but should work may be with a change in execute command.  java -cp "src/database/*;src" Main

6. Platform Used - MAC


How to run:
-------------------------------------------------------------------------------------------------
1. Navigate to the directory after downloading the project
2. Run the following instructions on command line:

   a. javac -cp "src/database/*" src/*.java

   b. java -cp "src/database/*:src" Main
