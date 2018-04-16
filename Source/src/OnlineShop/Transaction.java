package OnlineShop;

import java.io.Serializable;
import java.util.*;

public class Transaction implements Serializable{
	private String ProductName;
	private String SellerName;
	private String BuyerName;
	private int PaidPrice;
	private int MinimumPrice;
	
	public Transaction (String productName, String sellerName, int payment, int acceptablePrice, String buyerName)
	{
		ProductName = productName;
		SellerName = sellerName;
		PaidPrice = payment;
		MinimumPrice = acceptablePrice;
		BuyerName = buyerName;
		
	}
	
	public String getProudctName(){return ProductName;};
	public String getProductSeller(){return SellerName;};
	public int getProductPrice(){return PaidPrice;};
	public String getBuyerName() {return BuyerName;}

}
