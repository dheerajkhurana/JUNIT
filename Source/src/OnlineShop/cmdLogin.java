package OnlineShop;

import java.util.*;

public class cmdLogin implements Command{

	private Manager sessionManager;
	private Scanner in;

	
	@Override
	public void execute(Manager manager) {
		
		sessionManager = manager;
		in = new Scanner(System.in);
		
		System.out.println("Enter username: (e.g. Paul 001)");
		String username = in.nextLine();
		
		System.out.println("Enter password: (e.g. PaulPW) ");
		String password = in.nextLine();
		
		boolean result = sessionManager.checkLogin(username, password);
		
		if(result){
			System.out.println("Logged in!");
			// save session and start mainscreen display
			
			Session session = new Session(sessionManager.getSessionUser(), sessionManager);
			session.startSession(sessionManager.getStatus());
			
		} else {
			
			System.out.println("Invalid Username or Password. Please Try Again!");
		}		
	}	
}
