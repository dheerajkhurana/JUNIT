package OnlineShop;

import java.util.*;


public class Discount {

	private Manager sessionManager;
	private User sessionUser;
	private Boolean validDiscount;
	private double basicDiscount;
	private double groceryDiscount;
	private double premiumDiscount;
	private double birthdayDiscount;
	private double seasonalDiscount;
	private ArrayList<Transaction> userTransactionsAll = new ArrayList<Transaction>();
	private ArrayList<Transaction> userTransactionsBuy;
	private ArrayList<Transaction> userTransactionsSell;
	private Calendar todayDate = Calendar.getInstance(); //Bug report (date computation not working)
	private double TotalDiscount;
	
	public Discount(String category, Manager manager, Calendar date) 
	{
		sessionManager = manager;
		sessionUser = sessionManager.getSessionUser();
		userTransactionsAll = sessionManager.getTransactions();
		userTransactionsBuy = new ArrayList<Transaction>(); //bug report (initialize arraylist)
		userTransactionsSell = new ArrayList<Transaction>(); //bug report (initialize arraylist)
		basicDiscount = 0; //bug report (initialize variable)
		groceryDiscount = 0;
		premiumDiscount = 0;
		birthdayDiscount = 0;
		seasonalDiscount = 0;
		todayDate = date;
		
		validDiscount = valid(sessionUser);
		
		if (validDiscount)
		{
		basicDiscount = BasicDiscount();
		groceryDiscount = GroceryDiscount(category, todayDate);
		premiumDiscount = premium(sessionUser);
		birthdayDiscount = BirthdayDiscount(sessionUser, todayDate);
		seasonalDiscount = SeasonalDiscount(todayDate);
		
		}
		
		TotalDiscount = MaxDiscount();
	}

	public Boolean valid(User user) {
		int year = todayDate.get(Calendar.YEAR)-user.getBirthdayYear();
		
		if ((year >=18 && year <=25) || year >=65)
			return true;
		else 
			return false;
	}

	private double premium(User user) 
	{
		if (user.getPremium().equals("Student")) 
		{
			return 0.9;
		}
		else if (user.getPremium().equals("Senior Student")) 
		{
			return 0.95;
		}
		else if( user.getPremium().equals("Service"))
		{
			return 0.8;
		}
		else 
			return 1;
	
	}

	public double MaxDiscount() 
	{
		 double total = 0;
		
		  double[] discounts = {basicDiscount, groceryDiscount, premiumDiscount, birthdayDiscount, seasonalDiscount};
		 
		Arrays.sort(discounts);
		  
		  total = (discounts[0]) * (discounts[1]); // bug report (wrong discount computation)
		  
		  if (total <= 0.3) // bug report (wrong discount computation)
		  {
			  total = 0.3;
		  }
		  
		  return total;
		
	}

	public double GroceryDiscount(String category, Calendar date) {
		if (category.equals("Grocery") && date.get(Calendar.DAY_OF_MONTH) == (1)) // bug report(date computation not working)
		{
			return 0.9;
		}
		else
			return 1;
	}

	public double BirthdayDiscount(User user, Calendar date) 
	{
		if (date.get(Calendar.DAY_OF_MONTH) != user.getBirthdayDay() && date.get(Calendar.MONTH) == user.getBirthdayMonth())// bug report(date computation not working)
		{
			return 0.9;
		}
		else if (date.get(Calendar.DAY_OF_MONTH) == user.getBirthdayDay() && date.get(Calendar.MONTH) == user.getBirthdayMonth())// bug report(date computation not working)
		{
			return 0.85;
			
		}
		else return 1;
		
	}

	private double BasicDiscount() {
		int count = itemsBought();
		if (count >= 3 && count < 5)
		{
			return 0.95;
		}
		else if (count >= 5 && count < 10)
		{
			return 0.9;
		}
		else if (count >= 10)
		{
			return 0.85;
		}
		else 
			return 1;
		
	}
	
	
	private int itemsBought()
	{
		int count = 0;
		
		for(Transaction t: userTransactionsAll)
		{
			if (t.getBuyerName().equals(sessionUser.getName().toString()))
			{
				this.userTransactionsBuy.add(t);
			}
			else if (t.getProductSeller().equals(sessionUser.getName().toString()))
			{
				this.userTransactionsSell.add(t);
			}
		}
		count = this.userTransactionsBuy.size();
		
		return count;
	}
	
	public double SeasonalDiscount(Calendar date) {
		//Christmas sale (15-Dec to 30-Dec)
		if (date.get(Calendar.DAY_OF_MONTH) >= 15 && date.get(Calendar.DAY_OF_MONTH) <= 30 && date.get(Calendar.MONTH) == 11)
		{
			return 0.6;
		}
		//Lunar New Year sale (12-Feb to 27-Feb)
		else if (date.get(Calendar.DAY_OF_MONTH) >= 12 && date.get(Calendar.DAY_OF_MONTH) <= 27 && date.get(Calendar.MONTH) == 1)
		{
			return 0.7;
			
		}
		//Easter Sale (2-Apr to 16-Apr)
		else if (date.get(Calendar.DAY_OF_MONTH) >= 2 && date.get(Calendar.DAY_OF_MONTH) <= 16 && date.get(Calendar.MONTH) == 3)
		{
			return 0.8;
			
		}
		//Thanksgiving Sale (10-Nov to 25-Nov)
		else if (date.get(Calendar.DAY_OF_MONTH) >= 10 && date.get(Calendar.DAY_OF_MONTH) <= 25 && date.get(Calendar.MONTH) == 10)
		{
			return 0.85;
			
		}
		else return 1;
	}
	
	
	public double getDiscount()
	{
		return TotalDiscount;
	}
	
}
