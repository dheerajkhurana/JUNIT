package OnlineShop;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable{
	private String Name;
	private String Username;
	private String Password;
	private String UserID;
	private int BirthdayDay;
	private int BirthdayMonth;
	private int BirthdayYear;
	private String Region;
	private String Premium;

	
	public User (String name, String username, String password, int birthday, int month, int year, String region, String premium)
	{
		Name = name;
		Username = username;
		Password = password;
		BirthdayDay = birthday;
		BirthdayMonth = month;
		Region = region;
		BirthdayYear = year;
		Premium = premium;
		UserID = UUID.randomUUID().toString();
		
	}
	
	public String getName(){return Name;};
	public String getUsername(){return Username;};
	public String getPassword(){return Password;};
	public String getUserID() {return UserID;};
	public int getBirthdayDay(){ return BirthdayDay;};
	public int getBirthdayMonth(){ return BirthdayMonth;};
	public int getBirthdayYear(){ return BirthdayYear;};
	public String getRegion(){ return Region;};
	public String getPremium(){ return Premium;};

}
