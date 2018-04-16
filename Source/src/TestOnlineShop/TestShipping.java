package TestOnlineShop;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import OnlineShop.Manager;
import OnlineShop.Products;
import OnlineShop.Shipping;
import OnlineShop.Transaction;
import OnlineShop.User;

public class TestShipping {

	public class SessionManagerStub implements Manager{
		ArrayList<Transaction> transactionList;
		User sessionUser;
		
		public SessionManagerStub() {
			transactionList = new ArrayList<>();
    		};
    	
    		@Override
    		public User getSessionUser() {
    			return sessionUser;
    		}

			@Override
			public void init() {
			}

			@Override
			public void addUser(User user) {
			}

			@Override
			public void addProduct(Products product) {
			}

			@Override
			public void removeProduct(Products product) {
			}

			@Override
			public void addTransaction(Transaction transaction) {
			}

			@Override
			public void writeUsersDb() {
			}

			@Override
			public void readUsersDb() {
			}

			@Override
			public void writeProductsDb() {
			}

			@Override
			public void readProductsDb() {
			}

			@Override
			public void readTransactionsDb() {
			}

			@Override
			public void writeTransactionDb() {
			}

			@Override
			public ArrayList<Transaction> getTransactions() {
				return null;
			}

			@Override
			public ArrayList<Products> getProducts() {
				return null;
			}

			@Override
			public void setPorducts(ArrayList<Products> products) {
			}

			@Override
			public ArrayList<User> getUsers() {
				return null;
			}

			@Override
			public void setUsers(ArrayList<User> users) {
			}

			@Override
			public void setSessionUser(User user) {
				sessionUser = user;
			}

			@Override
			public boolean checkLogin(String username, String password) {
				return false;
			}

			@Override
			public boolean getStatus() {
				return false;
			}
	}

	@Test
	public void testGetShipping_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "Asia", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(10000, smStub, date);
		double result = ship.getShipping();
		assertEquals(0, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "Asia", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.1, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "Hong Kong", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.1, result, 0.0001);
	}
	
	
	@Test
	public void testGetShipping_04() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "NA", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.2, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_05() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "SA", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.25, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_06() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_07() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "Heaven", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(0, result, 0.0001);
	}

	@Test
	public void testGetShipping_08() { //Christmas
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 11);
		Shipping ship = new Shipping(250, smStub, date);
		double result = ship.getShipping();
		assertEquals(0, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_09() { //Christmas
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 11);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_10() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 31);
		date.set(Calendar.MONTH, 11);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_11() {//Lunar New Year
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		date.set(Calendar.MONTH, 1);
		Shipping ship = new Shipping(300, smStub, date);
		double result = ship.getShipping();
		assertEquals(0, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_12() {//Lunar New Year
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		date.set(Calendar.MONTH, 1);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_13() { //Easter
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 3);
		Shipping ship = new Shipping(350, smStub, date);
		double result = ship.getShipping();
		assertEquals(0, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_14() { //Easter
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 3);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_15() { //Thanksgiving
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 10);
		Shipping ship = new Shipping(150, smStub, date);
		double result = ship.getShipping();
		assertEquals(0, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_16() { //Thanksgiving
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 10);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_17() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_18() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 10);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
	@Test
	public void testGetShipping_19() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "EU", "Service");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		date.set(Calendar.MONTH, 5);
		Shipping ship = new Shipping(100, smStub, date);
		double result = ship.getShipping();
		assertEquals(1.3, result, 0.0001);
	}
	
}
