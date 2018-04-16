package TestOnlineShop;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import OnlineShop.Products;
import OnlineShop.SessionManager;
import OnlineShop.Transaction;
import OnlineShop.User;

public class TestSessionManager {

	@Test
	public void testAddUser() {
		SessionManager sm = SessionManager.getInstance();
		ArrayList<User> oldList =  sm.getUsers();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		sm.addUser(test);
		ArrayList<User> newList =  sm.getUsers();
		assertNotSame(oldList, newList);
	}
	
	@Test
	public void testSetUsers() {
		SessionManager sm = SessionManager.getInstance();
		ArrayList<User> oldList =  sm.getUsers();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		oldList.add(test);
		sm.setUsers(oldList);
		ArrayList<User> newList =  sm.getUsers();
		boolean result = oldList.equals(newList);
		assertEquals(false, result);
	}

	@Test
	public void testAddProduct() {
		SessionManager sm = SessionManager.getInstance();
		ArrayList<Products> oldList =  sm.getProducts();
		Products test = new Products("Test", "TestSeller", 2, 1, "Others");
		sm.addProduct(test);
		sm.removeProduct(test);
		ArrayList<Products> newList =  sm.getProducts();
		assertEquals(oldList.size(), newList.size());
	}
	
	@Test
	public void testSetPorducts() {
		SessionManager sm = SessionManager.getInstance();
		ArrayList<Products> oldList =  sm.getProducts();
		Products test = new Products("Test", "TestSeller", 2, 1, "Others");
		oldList.add(test);
		sm.setPorducts(oldList);
		ArrayList<Products> newList =  sm.getProducts();
		boolean result = oldList.equals(newList);
		assertEquals(false, result);
	}

	@Test
	public void testAddTransaction() {
		SessionManager sm = SessionManager.getInstance();
		ArrayList<Transaction> oldList =  sm.getTransactions();
		Transaction test = new Transaction("Test", "TestSeller", 2, 1, "TestBuyer");
		sm.addTransaction(test);
		ArrayList<Transaction> newList =  sm.getTransactions();
		assertNotSame(oldList, newList);
	}

	@Test
	public void testGetSessionUser() {
		SessionManager sm = SessionManager.getInstance();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		sm.setSessionUser(test);
		User result = sm.getSessionUser();
		assertEquals(test, result);
	}

	@Test
	public void testCheckLogin_01() {
		SessionManager sm = SessionManager.getInstance();
		sm.init();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		sm.addUser(test);
		boolean result = sm.checkLogin("test", "test");
		assertEquals(true, result);
		
	}
	
	@Test
	public void testCheckLogin_02() {
		SessionManager sm = SessionManager.getInstance();
		sm.init();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		sm.addUser(test);
		boolean result = sm.checkLogin("admin", "test");
		assertEquals(false, result);
		
	}
	
	@Test
	public void testCheckLogin_03() {
		SessionManager sm = SessionManager.getInstance();
		sm.init();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		sm.addUser(test);
		boolean result = sm.checkLogin("test", "admin");
		assertEquals(false, result);
		
	}
	
	@Test
	public void testGetStatus() {
		SessionManager sm = SessionManager.getInstance();
		boolean result = sm.getStatus();
		assertEquals(true, result);
	}

}
