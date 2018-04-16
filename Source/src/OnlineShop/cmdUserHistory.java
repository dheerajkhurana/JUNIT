package OnlineShop;

import java.util.ArrayList;

public class cmdUserHistory implements Command {

		private ArrayList<Transaction> userTransactionsAll = new ArrayList<Transaction>();
		private ArrayList<Transaction> userTransactionsBuy = new ArrayList<Transaction>();
		private ArrayList<Transaction> userTransactionsSell = new ArrayList<Transaction>();
		
		private Manager sessionManager;
		private User sessionUser; 
		
		@Override
		public void execute(Manager manager) {
			
			sessionManager		= manager;
			this.sessionUser 	= manager.getSessionUser();
			
			sessionManager.readTransactionsDb();
			userTransactionsAll = sessionManager.getTransactions();
			
			for(Transaction t: userTransactionsAll)
			{
				if (t.getBuyerName().equals(sessionUser.getName().toString()))
				{
					userTransactionsBuy.add(t);
				}
				else if (t.getProductSeller().equals(sessionUser.getName().toString()))
				{
					userTransactionsSell.add(t);
				}
			}
			
			if(userTransactionsBuy.isEmpty() && userTransactionsSell.isEmpty()){
				System.out.println("\nNo purchase or sales record found.\n");	
			}
			else{
				System.out.println("Your User History is as follows: \n");
				System.out.print("\n                                      ||| Product(s) Bought |||");
				System.out.print("\n   ================================================================================================");
				System.out.printf("\n %1$30s %2$20s %3$30s", "Product Name", "Price", "Seller Name");
				System.out.print("\n   ================================================================================================");
				if (userTransactionsBuy.isEmpty()){
					System.out.print("\n   	                              --- no records found ---");
				}
				else{
					for (Transaction t : userTransactionsBuy){
						System.out.printf("\n %1$30s %2$20s %3$30s", t.getProudctName(), t.getProductPrice(), t.getProductSeller());
					}
					System.out.print("\n\n   	                                 --- End of List ---");
				}
				System.out.print("\n   ================================================================================================");
				
				System.out.print("\n\n\n\n   	                               ||| Product(s) Sold |||                               ");
				System.out.print("\n   ================================================================================================");
				System.out.printf("\n %1$30s %2$20s %3$30s", "Product Name", "Price", "Buyer Name");
				System.out.print("\n   ================================================================================================");
				if (userTransactionsSell.isEmpty()){
					System.out.print("\n   	                              --- no records found ---");
				}
				else{
					for (Transaction t : userTransactionsSell)
					{
						System.out.printf("\n %1$30s %2$20s %3$30s", t.getProudctName(), t.getProductPrice(), t.getBuyerName());
					}
					System.out.print("\n\n   	                                 --- End of List ---");
				}
				System.out.print("\n   ================================================================================================");
			}
		}

}
