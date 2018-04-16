package OnlineShop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class cmdBuyProducts implements Command {

	private String ProductName;
	private String SellerName;
	private int Payment;
	private Manager sessionManager;
	private User sessionUser;
	private ArrayList<Products> products;
	private ArrayList<Products> productsList = new ArrayList<Products>();
	private Products purchase;
	private String category;
	private Calendar date = Calendar.getInstance();

	@Override
	public void execute(Manager manager) {

		sessionManager = manager;
		sessionUser = manager.getSessionUser();

		Scanner in = new Scanner(System.in);
		
		Category.showCategory();
		System.out.println("\nPlease enter the type of product you wish to search for: (Input one of the valid categories e.g. Grocery)");
		category = in.nextLine();

		// print product name, listed price and one more unique identifier
		products = sessionManager.getProducts();
		if (products.size() == 0) {
			System.out.println("No products available for purchase at this moment. Please try again later!");
		} else {
			getProductByCategory();
			if (productsList.size() == 0) {
				System.out.println("Please select one of the above types only, otherwise state the Others category.");
			} else {
				System.out.println("\nChoose a Product from the following List");
				System.out.print("\n   ================================================================================================");
				System.out.printf("\n %1$30s %2$20s %3$30s", "Product Name", "Price", "Seller Name");
				System.out.print("\n   ================================================================================================");
				
				for (Products p : productsList) {
					System.out.printf("\n %1$30s %2$20s %3$30s", p.getProductName(), p.getProductPrice(), p.getProductSeller());
				}
				System.out.print("\n\n   	                                --- End of List ---                               ");
				System.out.print("\n   ================================================================================================\n");

				do{
					System.out.println("\nPlease enter the selected product's name");
					ProductName = in.nextLine();
					System.out.println("Please enter the selected product's seller's name");
					SellerName = in.nextLine();
					
					purchase = getPurchase();
					if (purchase == null){
						System.out.println("\nProduct Name and Seller Name does not match. Please try again!");
					}
					else {
						System.out.println("Please enter the amount you will pay");
						boolean isInt = true;
						do{
							isInt = true;
							try{
								Payment = Integer.parseInt(in.nextLine());
							}catch (NumberFormatException ex) {
							    System.out.println("Please input an integer");
							    isInt = false;
							}
						}while(isInt == false);
						System.out.println("\nPlease wait while we confirm if your offer is acceptable to the seller");
					}
				}while (purchase == null);
				
				// function to check if the value meets the minimum price
				boolean Accepted = AcceptOffer(Payment, purchase);
				if (Accepted) {
					System.out.println("\nYour Purchase was successful!");

					double total = getTotalPrice(category, Payment, sessionManager, date);
					System.out.println("\nTotal amount you have to pay after service charges and discounts: " + (int)total);
					
					Transaction newSale = new Transaction(ProductName, SellerName, Payment,
							purchase.getProductMinimumPrice(), sessionUser.getName());
					sessionManager.addTransaction(newSale);

					sessionManager.removeProduct(purchase);
					
					RewardPoints rewards = new RewardPoints(total, sessionManager, date);
					int newRewards = rewards.getNewPoints();
					int totalRewards = rewards.gettotalPoints(); 
					String appleWatch = rewards.getWatchStr(); 					
					
					System.out.println("\nReward points gained : " + newRewards);
					System.out.println("Total reward points : " + totalRewards);
					System.out.println(appleWatch);					

				} else {
					System.out.println(
							"\nYour Purchase offer was not acceptable to the seller, please improve your offer!");
				}

			}

		}
	}

	public boolean AcceptOffer(int payment, Products selectedProduct) {
		if (payment >= selectedProduct.getProductPrice() || payment >= selectedProduct.getProductMinimumPrice()) {
			return true;
		}
		return false;
	}
	
	public double getTotalPrice(String category, int payment, Manager manager, Calendar date) {
		Discount discount = new Discount(category, manager, date);
		double totalDiscount = discount.getDiscount();
		
		Shipping shipping = new Shipping(payment, manager, date);
		double totalShipping = shipping.getShipping();
		
		double total = (Double.parseDouble(Integer.toString(payment)));
		
		if(totalDiscount != 1)
		{
		 total = total*totalDiscount;
		}
		
		if (totalShipping != 0)
		{
			total = total*totalShipping;
		}
		return total;
	}
	
	private Products getPurchase() {
		for (Products p : products) {
			if ((p.getProductName().equals(ProductName)) && (p.getProductSeller().equals(SellerName))) {
				return p;
			}
		}
		return null;
	}
	
	private void getProductByCategory() {
		for (Products p : products) {
			if ((p.getProductCategory()).equals(category)) {
				productsList.add(p);
			}
		}
	}

}
