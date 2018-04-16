package OnlineShop;

import java.util.*;

public class RewardPoints {
	private Manager sessionManager;
	private User sessionUser;
	private ArrayList<Transaction> userTransactionsAll = new ArrayList<Transaction>();
	private int newRewardPoints = 0;
	private int totalRewardPoints = 0;
	private String appleWatch = "";
	private Calendar todayDate = Calendar.getInstance();
	
	public RewardPoints(double totalAmount, Manager manager, Calendar date)
	{
		sessionManager = manager;
		sessionUser = sessionManager.getSessionUser();
		userTransactionsAll = sessionManager.getTransactions();
		todayDate = date;
		newRewardPoints = setNewPoints(totalAmount, todayDate);
		totalRewardPoints = settotalPoints(todayDate);
		appleWatch = setWatchStr();		
	}

	public int setNewPoints(double amount, Calendar date){
		newRewardPoints = (int)amount/10;
		
		if (date.get(Calendar.DAY_OF_MONTH) >= 15 && date.get(Calendar.DAY_OF_MONTH) <= 30 && date.get(Calendar.MONTH) == 11)
		{
			newRewardPoints = newRewardPoints * 4;
		}
		//Lunar New Year sale (12-Feb to 27-Feb)
		else if (date.get(Calendar.DAY_OF_MONTH) >= 12 && date.get(Calendar.DAY_OF_MONTH) <= 27 && date.get(Calendar.MONTH) == 1)
		{
			newRewardPoints = newRewardPoints * 3;
			
		}
		//Easter Sale (2-Apr to 16-Apr)
		else if (date.get(Calendar.DAY_OF_MONTH) >= 2 && date.get(Calendar.DAY_OF_MONTH) <= 16 && date.get(Calendar.MONTH) == 3)
		{
			newRewardPoints = newRewardPoints * 2;
			
		}
		//Thanksgiving Sale (10-Nov to 25-Nov)
		else if (date.get(Calendar.DAY_OF_MONTH) >= 10 && date.get(Calendar.DAY_OF_MONTH) <= 25 && date.get(Calendar.MONTH) == 10)
		{
			newRewardPoints = (int) (newRewardPoints * 1.5);
			
		}
		
		return newRewardPoints;
		
	}
	
	public int settotalPoints(Calendar date){
		for(Transaction t: userTransactionsAll)
		{
			if (t.getBuyerName().equals(sessionUser.getName().toString()))
			{
				totalRewardPoints = totalRewardPoints + t.getProductPrice()/10;
				
				if (date.get(Calendar.DAY_OF_MONTH) >= 15 && date.get(Calendar.DAY_OF_MONTH) <= 30 && date.get(Calendar.MONTH) == 11)
				{
					newRewardPoints = newRewardPoints * 4;
				}
				//Lunar New Year sale (12-Feb to 27-Feb)
				else if (date.get(Calendar.DAY_OF_MONTH) >= 12 && date.get(Calendar.DAY_OF_MONTH) <= 27 && date.get(Calendar.MONTH) == 1)
				{
					newRewardPoints = newRewardPoints * 3;
					
				}
				//Easter Sale (2-Apr to 16-Apr)
				else if (date.get(Calendar.DAY_OF_MONTH) >= 2 && date.get(Calendar.DAY_OF_MONTH) <= 16 && date.get(Calendar.MONTH) == 3)
				{
					newRewardPoints = newRewardPoints * 2;
					
				}
				//Thanksgiving Sale (10-Nov to 25-Nov)
				else if (date.get(Calendar.DAY_OF_MONTH) >= 10 && date.get(Calendar.DAY_OF_MONTH) <= 25 && date.get(Calendar.MONTH) == 10)
				{
					newRewardPoints = newRewardPoints + newRewardPoints/2;
					
				}
			}
		}
		if (totalRewardPoints >= 500){
			totalRewardPoints = totalRewardPoints + 10;
		}
		return totalRewardPoints;
	}
	
	public String setWatchStr(){
		if (totalRewardPoints >= 20000 && sessionUser.getRegion().equals("Hong Kong")){
			appleWatch = "Congrats! You will receive an Apple Watch for 20 000 Reward Points!";
			totalRewardPoints = totalRewardPoints - 20000;
		}
		else
			appleWatch = "";
		return appleWatch;
	}
	
	public int getNewPoints(){
		return newRewardPoints;
	}
	
	public int gettotalPoints(){
		return totalRewardPoints;
	}
	
	public String getWatchStr(){
		return appleWatch;
	}
}
