package OnlineShop;

import java.util.*;
import java.io.*;

public class SessionManager implements Manager {
	
	private static SessionManager instance = new SessionManager();
	
	private ArrayList<User> usersList;
	private ArrayList<Products> productsList;
	private ArrayList<Transaction> transactionList;

	private User sessionUser;
	
	public static SessionManager getInstance(){
		if(instance == null)
			return new SessionManager();
		else
			return instance;
	}
	
	private SessionManager(){
		
		this.sessionUser	= null;
		this.usersList = new ArrayList<User>();
		this.productsList = new ArrayList<Products>();
		this.transactionList = new ArrayList<Transaction>();

		this.readUsersDb();
		this.readProductsDb();
		this.readTransactionsDb();
	}
	
	public void init(){

	}
	
	public void addUser(User user){
		usersList.add(user);
		writeUsersDb();
	}
	
	public void addProduct (Products product){
		productsList.add(product);
		writeProductsDb();
	}
	
	public void removeProduct (Products product)
	{
		productsList.remove(product);
		writeProductsDb();
		readProductsDb();
	}
	
	public void addTransaction (Transaction transaction)
	{
		transactionList.add(transaction);
		writeTransactionDb();
	}
	
	public void writeUsersDb(){
		try {
			File seekerDb 			= new File("Users.txt");
			
			seekerDb.createNewFile(); // creates new file if doesn't exist, does nothing otherwise
			
			FileOutputStream fos 	= new FileOutputStream("Users.txt");
			ObjectOutputStream oos 	= new ObjectOutputStream(fos);
			
			oos.writeObject(usersList);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void readUsersDb(){

		try {
			File userDb 				= new File("Users.txt");
			userDb.createNewFile(); // creates new file if doesn't exist, does nothing otherwise
			
			FileInputStream fis			= new FileInputStream("Users.txt");
			ObjectInputStream ois 		= new ObjectInputStream(fis);
			
			ArrayList<User> readcase	= (ArrayList<User>) ois.readObject();
			
			this.usersList = readcase;
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void writeProductsDb(){
		try {
			File productDb 			= new File("Products.txt");
			
			productDb.createNewFile(); // creates new file if doesn't exist, does nothing otherwise
			
			FileOutputStream fos 	= new FileOutputStream("Products.txt");
			ObjectOutputStream oos 	= new ObjectOutputStream(fos);
			
			oos.writeObject(productsList);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void readProductsDb()
	{

		try {
			File productDb 				= new File("Products.txt");
			productDb.createNewFile(); // creates new file if doesn't exist, does nothing otherwise
			
			FileInputStream fis			= new FileInputStream("Products.txt");
			ObjectInputStream ois 		= new ObjectInputStream(fis);
			
			ArrayList<Products> readcase	= (ArrayList<Products>) ois.readObject();
			
			this.productsList = readcase;
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	
	public void readTransactionsDb()
	{
		try {
			File productDb 				= new File("Transactions.txt");
			productDb.createNewFile(); // creates new file if doesn't exist, does nothing otherwise
			
			FileInputStream fis			= new FileInputStream("Transactions.txt");
			ObjectInputStream ois 		= new ObjectInputStream(fis);
			
			ArrayList<Transaction> readcase	= (ArrayList<Transaction>) ois.readObject();
			
			this.transactionList = readcase;
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	
	public void writeTransactionDb(){
		try {
			File transactionDb 			= new File("Transactions.txt");
			
			transactionDb.createNewFile(); // creates new file if doesn't exist, does nothing otherwise
			
			FileOutputStream fos 	= new FileOutputStream("Transactions.txt");
			ObjectOutputStream oos 	= new ObjectOutputStream(fos);
			
			oos.writeObject(transactionList);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public ArrayList<Transaction> getTransactions() {
		readTransactionsDb();
		return transactionList;
	}
	public ArrayList<Products> getProducts() {
		readProductsDb();
		return productsList;
	}

	public void setPorducts (ArrayList<Products> products) {
		this.productsList = products;
	}
	
	public ArrayList<User> getUsers() {
		readUsersDb();
		return usersList;
	}

	public void setUsers (ArrayList<User> users) {
		this.usersList = users;
	}

	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}

	public static void setInstance(SessionManager instance) {
		SessionManager.instance = instance;
	}

	public boolean checkLogin(String username, String password){
		for(User s:usersList){
			if(s.getUsername().equals(username) && s.getPassword().equals(password)){
				this.sessionUser = s;
				return true;
			}				
		}
		
		return false;
	}

	@Override
	public boolean getStatus() {
		return true;
	}

}
