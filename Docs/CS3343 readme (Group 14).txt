
	CS3343 - Sofware Engineering Practice (Project - Group 14)
===============================================================================

				Online Shop

===============================================================================
			     || DESCRIPTION ||
===============================================================================

 "Online Shop" is an software application, which allows 'Selling' and 'Buying
 of goods from various categories. 

 The typical use of this program is to allow users to simply create an account,
 followed by logging into it. Then the user can choose to list products or buy.

 The program will then compute the final price based on numerous calculations 
 which occur at the backend.


===============================================================================
			     || INSTALLATION ||
===============================================================================

 The Online Shop program works on Windows and Linux computers.

 Prerequisites:
 --------------
	1. Ensure your computer has the latest Java SE/Java SE Development Kit
	   (JDK) installed.

	2. Ensure the latest JRE (Java Runtime Environment) is present on your 
	   computer.

	3. Check the files (Products.txt, Trasnactions.txt, Users.txt) are 
	   present and are in the right directory (they ought to be in the same 
	   directory as the OnlineShop.jar file) The Products.txt and Users.txt 
	   file is essential for the program to run as intended.

	4. In addition, make sure that the run OnlineShop.bat file is present


 If any of the above system requirements are missing, follow the steps below:


		       *** Install a JRE/ Java SE (SDK) ***
			   ----------------------------

 Please visit the following website and follow all the necessary steps listed 
 to install a JRE/Java SE (SDK) or to update to the latest version.

 http://www.oracle.com/technetwork/java/javase/downloads/index.html



			  *** Misplaced Text files ***
			      --------------------

 Copy the text files to the same directory as where the OnlineShop.jar file is 
 located.


	     *** Missing /Misplaced OnlineShop.bat (Windows Only) ***
		 ------------------------------------------------

 Please open the text editor (Notepad in Windows)
 Copy the following lines to the text editor:

 java -jar OnlineShop.jar
 pause

 Now save the file as a (filename).bat file and make sure the .bat file is 
 saved to the same directory as the OnlineShop.jar file. Double click the 
 .bat file and Enjoy Shopping!


===============================================================================
			        || USER GUIDE ||
===============================================================================

 ||| LAUNCH PROGRAM
     --------------

 Windows - You may simply double click the .bat file or create a shortcut of 
           the .bat file to desktop and double click the shortcut from there.

 Linux   - Navigate to the directory where the .jar file is. 
    	   Open terminal and type the following command:
	   java -jar OnlineShop.jar

 MacOS   - Navigate to the directory where the .jar file is. 
    	   Open terminal and type the following command:
	   java -jar OnlineShop.jar



 ||| MAIN MENU
     ---------

 It will be displayed immediately after launch and showing the user 3 options.
 If new user, then it is required to signup by using the "Signup" command.
 Otherwise for existing users, simply login by using the "Login" command.
 Last command is for exiting the program.


 ||| SIGNUP
     ------

 The program will ask for user's credentials one by one. They are:
 'Name', 'Username', 'Password', 'Birthday', 'Premium', and 'Region'.

 The purpose of these credentials is to provide users with best discounts and 
 shipping costs depending on the input.


 ||| LOGIN
     -----

 To login into the system, the program will ask for "Username" and "Password".
 Upon entering the valid credentials the system will let you in to "Main Menu".


 ||| MAIN MENU
     ---------

 There will be a total of 4 commands. Last one is to logout of our system and 
 redirected to the First Command page.

 Here we have the 2 main options which are namely "Buy" and "Sell.
 So user can input one of these commands depending on their purpose and need.

 The remaining command would be "History" which allows user to check their
 purchases and sales which records down every transaction of the user.


 ||| BUY
     ---

 The program will ask for the type of product the user wishes to purchase.
 There will be a total of 7 categories to choose from.

 After inputting the category, there will be a list of products for the user
 to select from.

 The program will ask for the "Product Name" and "Seller Name".
 If such product exists in the list, then user will be prompted to input Price.

 The result of the transaction depends on the Seller's acceptable price.
 If it meets it, then the transaction is succeeded immediately.


 ||| SELL
     ----

 The program will ask the user to input the product they would like to sell.
 Followed by the category which the product belongs to.
 After that, the program will ask for a price range to set the selling price.
 Finally, it will be listed for other users to buy the product.


 ||| HISTORY
     -------

 As mentioned earlier, the users can check their purchase and sales history.
 Two lists will be generated, one of purchase and the other for sales.
 It includes the details like Product Name, Buyer/Seller Name, and Price.


===============================================================================
			        || TEAM MEMBERS ||
===============================================================================
	Position		       Name		    SID

PROJECT MANAGER 		- Dheeraj Khurana	- 54227224
PROGRAMMER			- Navwinder Singh	- 54395023
ASSISTANT PROJECT MANAGER 	- Ravinder Singh	- 54390586
TESTING ENGINEER		- Pang Tsz Ho		- 54387730
CONFIGURATION MANAGER		- Matthew Bilo		- 54387858


===============================================================================
			       || END OF README ||
===============================================================================






