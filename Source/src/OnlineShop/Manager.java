package OnlineShop;

import java.util.ArrayList;

public interface Manager {
	public void init();
	
	public void addUser(User user);
	
	public void addProduct (Products product);
	
	public void removeProduct (Products product);
	
	public void addTransaction (Transaction transaction);
	
	public void writeUsersDb();
	
	public void readUsersDb();
	
	public void writeProductsDb();
	
	public void readProductsDb();
	
	public void readTransactionsDb();
	
	public void writeTransactionDb();
	
	public ArrayList<Transaction> getTransactions();
	public ArrayList<Products> getProducts();

	public void setPorducts (ArrayList<Products> products);
	
	public ArrayList<User> getUsers();

	public void setUsers (ArrayList<User> users);

	public User getSessionUser();

	public void setSessionUser(User sessionUser);

	public boolean checkLogin(String username, String password);
	
	public boolean getStatus();
}
