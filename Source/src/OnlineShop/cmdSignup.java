package OnlineShop;

import java.util.*;
public class cmdSignup implements Command {

	private Manager sessionManager;

	
	private Scanner in;
	
	@Override
	public void execute(Manager manager) {
		in = new Scanner(System.in);
		
		sessionManager = manager;
		sessionManager.addUser(signupUser());
			
		System.out.println("You have succesfully signed up!");
	}
	
	private User signupUser(){
		String name;

		String username;
		String password;
		int day;
		int month;
		int year;	
		String region;
		String Premium;
		
		System.out.println("Enter name: (e.g. Paul)");
		name = in.nextLine();
		
		System.out.println("Enter username: (e.g. Paul001)");
		username = in.nextLine();
		
		System.out.println("Enter password: (e.g. PaulPW)");
		password = in.nextLine();
		
		System.out.println("Enter birthday day: Integer between 1 to 31)");
		day = Integer.parseInt(in.nextLine());
		
		System.out.println("Enter birthday month: (Integer between 1 to 12)");
		month = Integer.parseInt(in.nextLine());
		
		System.out.println("Enter birthday year: (e.g. 1996)");
		year = Integer.parseInt(in.nextLine());
		
		System.out.println("Enter Premium : (No, Student, Senior Student, Service)");
		Premium = (in.nextLine());
		
		System.out.println("Enter region: (Hong Kong, Asia, NA, SA, EU)");
		region = (in.nextLine());

		return new User(name, username, password,day,month,year,region,Premium);
	}
	
	
}
