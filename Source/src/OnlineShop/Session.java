package OnlineShop;

import java.util.*;

public class Session {

	private ArrayList<Products> productsList;
	private User sessionUser;
	private Manager sessionManager;
	private Scanner in;

	
	public Session(User user, Manager manager){
		this.sessionUser 	= user;
		sessionManager		= manager;
	}
	
	public void startSession(boolean status){
		productsList = sessionManager.getProducts();
		in			= new Scanner(System.in);
		
		if (!status) return;
		
		while(true){
			Main.clearScreen();
			System.out.println("MAIN MENU\n");
			System.out.println("The following commands are available: \n");
			System.out.println("\t1. Buy \t\t - Search for products available for purchase\n");
			System.out.println("\t2. Sell \t - Add a new product to the existing listings available for purchase\n");
			System.out.println("\t3. History \t - View your purchase and sales history\n");
			System.out.println("\t4. Logout\n");
			
			System.out.println("Enter command: ");
			
			String cmd = in.nextLine();
			int result = processInput(cmd);
			
			if(result == 1) return;
		}

	}
	
	public int processInput(String cmd){
		
		if(cmd.equals("Logout")){
			System.out.println("\nGoodbye, see you soon!");
			return 1;
		}
		
		else if (cmd.equals("Buy"))
		{
			(new cmdBuyProducts()).execute(sessionManager);
		}
			
		else if (cmd.equals("Sell"))
		{
			(new cmdSellProduct()).execute(sessionManager);
		}
		
		else if (cmd.equals("History"))
		{
			(new cmdUserHistory()).execute(sessionManager);
		}
		
		else
		{
			System.out.println("\nInvalid Command. Please Try Again!");
			return -1;
		}
		
		return 0;
	}

	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
	
}
