package OnlineShop;

import java.util.Scanner;

public class cmdSellProduct implements Command {

	private String productName;
	private String Seller;
	private int listedPrice;
	private int minimumPrice;
	private String category;
	private boolean accepted;
	
	private Manager sessionManager;
	private User sessionUser; 
	
	@Override
	public void execute(Manager manager) {
		// TODO Auto-generated method stub
		
		sessionManager		= manager;
		this.sessionUser 	= manager.getSessionUser();

		Scanner in = new Scanner(System.in);

		showSellerInfo();
		System.out.println("\nPlease enter the Product Name: (e.g. Apple)");
		productName = in.nextLine();

		Category.showCategory();
		do{
			System.out.println("Please enter the category/type of the product");
			category = in.nextLine();
			accepted = Category.checkCategory(category);
			if (!accepted) {
				System.out.println("Invalid Input");
				System.out.println(category);
			}
		}while(!accepted);
		
		System.out.println("\nPlease enter the price you wish to sell it at:");
		boolean isInt = true;
		do{
			isInt = true;
			try{
				listedPrice = Integer.parseInt(in.nextLine());
			}catch (NumberFormatException ex) {
			    System.out.println("Please input an integer");
			    isInt = false;
			}
		}while(isInt == false);
		
		System.out.println ("Please enter the minimum price of the product you are willing to sell it at:");
		do{
			isInt = true;
			try{
				minimumPrice = Integer.parseInt(in.nextLine());
			}catch (NumberFormatException ex){
				System.out.println("Please input an integer");
			    isInt = false;
			}
		}while(isInt == false);
		
		Products sellProduct = new Products(productName, Seller, listedPrice, minimumPrice, category);
		sessionManager.addProduct(sellProduct);
		System.out.println("Product has been succesfully listed - listening for offers now");
	}
	
	private void showSellerInfo() {
		System.out.println("\nThe product is going to be listed under the following name and userID");
		Seller =  sessionUser.getName();
		System.out.println("\nName: " + Seller + "\t UserID: " + sessionUser.getUserID());		
		System.out.println();
	}
}
