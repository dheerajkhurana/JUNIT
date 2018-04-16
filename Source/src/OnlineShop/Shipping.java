package OnlineShop;

import java.util.Calendar;

public class Shipping {

	private Manager sessionManager;
	private User sessionUser;
	private double shippingCharges = 0;
	private Calendar todayDate = Calendar.getInstance();

	public Shipping(int price, Manager manager, Calendar date) 
	{
		sessionManager = manager;
		User sessionUser = sessionManager.getSessionUser();
		String region = sessionUser.getRegion();
		todayDate = date;
		shippingCharges = Charges(region, price, todayDate);

	}

	private double Charges(String region, int amount, Calendar date) 
	{
		if (seasonalFreeShipping(amount, date)) return 0;
		if (amount >= 1000) 
		{
			return 0;
		} else 
		{
			if (region.equals("Asia") || region.equals("Hong Kong"))  //bug report (handling for Hong Kong user)
			{
				return 1.1;
			} 
			else if (region.equals("NA")) 
			{
				return 1.2;
			} 
			else if (region.equals("SA")) 
			{
				return 1.25;
			} 
			else if (region.equals("EU")) 
			{
				return 1.3;
			} 
			else
			{
				return 0;
			}
		}
	}
	
	private boolean seasonalFreeShipping(int amount, Calendar date) {
		//Christmas sale (15-Dec to 30-Dec)
				if (date.get(Calendar.DAY_OF_MONTH) >= 15 && date.get(Calendar.DAY_OF_MONTH) <= 30 && date.get(Calendar.MONTH) == 11)
				{
					if (amount >= 250) return true;
					else return false;
				}
				//Lunar New Year sale (12-Feb to 27-Feb)
				else if (date.get(Calendar.DAY_OF_MONTH) >= 12 && date.get(Calendar.DAY_OF_MONTH) <= 27 && date.get(Calendar.MONTH) == 1)
				{
					if (amount >=300) return true;
					else return false;
					
				}
				//Easter Sale (2-Apr to 16-Apr)
				else if (date.get(Calendar.DAY_OF_MONTH) >= 2 && date.get(Calendar.DAY_OF_MONTH) <= 16 && date.get(Calendar.MONTH) == 3)
				{
					if (amount >=350) return true;
					else return false;
					
				}
				//Thanksgiving Sale (10-Nov to 25-Nov)
				else if (date.get(Calendar.DAY_OF_MONTH) >= 10 && date.get(Calendar.DAY_OF_MONTH) <= 25 && date.get(Calendar.MONTH) == 10)
				{
					if (amount >=150) return true;
					else return false;
					
				}
				else return false;
	}
	
	public double getShipping()
	{
		return shippingCharges;
	}
	
}
