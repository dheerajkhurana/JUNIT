package OnlineShop;

import java.io.Serializable;
import java.util.*;

public class Products implements Serializable{

	private String ProductName;
	private String SellerName;
	private int ListedPrice;
	private int MinimumPrice;	
	private Category category;
	
	public Products (String productName, String sellerName, int price, int acceptablePrice, String aCategory)
	{
		ProductName = productName;
		SellerName = sellerName;
		ListedPrice = price;
		MinimumPrice = acceptablePrice;
		category = new Category(aCategory);
		
	}
	
	public String getProductName(){return ProductName;};
	public String getProductSeller(){return SellerName;};
	public int getProductPrice(){return ListedPrice;};
	public int getProductMinimumPrice(){return MinimumPrice;};
	public String getProductCategory(){return category.getCategory();};
	
}
