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
1. Main.java - Main class used start the run and call the game selection class

2. ATM.java - Its like an interface for the user to Bank created for the purpose giving ATM effect and also can used incase of other functions exclusive for the ATMs to be scalable

3. Bank.java - Class that will be called from within ATM for the user to access the database, create account, signup, login or display info etc.,



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
