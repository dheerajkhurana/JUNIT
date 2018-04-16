package OnlineShop;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		SessionManager.getInstance().init();
		
		try {
			Scanner in = new Scanner(System.in);
						
			boolean first = true;
			while(true){
				clearScreen();
				
				if(first){first = false;}
				else { System.out.println(); }
				
				System.out.println("Hi, there!");
				System.out.println("Get shopping right away.");
				System.out.println();
				
				System.out.println("Available commands: ");
				System.out.println("\t1. Signup");
				System.out.println("\t2. Login");
				System.out.println("\t3. Exit");
				
				System.out.println("\nEnter command: ");
				
				String cmd = in.nextLine();		
				processInput(cmd);
			}			
		} catch (Exception e){
			
		}
	}
	
	private static void processInput(String cmd) { 
		SessionManager sm 				= SessionManager.getInstance();
		
		if(cmd.equals("Exit")){
			System.out.println("\nGoodbye, see you soon!");
			System.exit(0);
		}
		
		/****************** debug *******************************/
		else if(cmd.equals("dbg")){
			System.out.println(System.getProperty("user.dir"));
			ArrayList<User> users 		= sm.getUsers();

			
			System.out.println("Users: ");
			for(User s:users){
				System.out.println(s.toString());
			}
			
			// wait for input
			try{System.in.read();} catch(Exception e){}
		}
		
		/*********************************************************/
		
		if(cmd.equals("Signup")){
			(new cmdSignup()).execute(sm);
		}
		
		else if(cmd.equals("Login")){
			(new cmdLogin()).execute(sm);
		}
		
		else {
			System.out.println("\nInvalid Command. Please Try Again!");
		}
	}
	
	public static void clearScreen() {  
	    for(int i=0; i<6; i++){
	    	System.out.println();
	    }
	}

}
