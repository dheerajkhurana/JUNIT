package OnlineShop;

public class Category {
	
	private String category;
	
	public Category(String aCategory) {
		category = aCategory;
	}
	
	public static boolean checkCategory(String category) {
		if ("Electronic Appliances".equals(category)) return true;
		else if ("Furniture".equals(category)) return true;
		else if ("Grocery".equals(category)) return true;
		else if ("Apparel".equals(category)) return true;
		else if ("Books".equals(category)) return true;
		else if ("Sports".equals(category)) return true;
		else if ("Others".equals(category)) return true;
		else return false;
	}
	
	public static void showCategory() {
		System.out.println("The following is the list of accepted product types, if needed please state the product as Others category");
		System.out.println("\t1. Electronic Appliances ");
		System.out.println("\t2. Furniture");
		System.out.println("\t3. Grocery ");
		System.out.println("\t4. Apparel ");
		System.out.println("\t5. Books ");
		System.out.println("\t6. Sports");
		System.out.println("\t7. Others");
	}

	public String getCategory() {
		return category;
	}
	
}
