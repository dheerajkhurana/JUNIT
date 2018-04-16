package TestOnlineShop;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import OnlineShop.Manager;
import OnlineShop.Products;
import OnlineShop.Session;
import OnlineShop.SessionManager;
import OnlineShop.Transaction;
import OnlineShop.User;

public class TestSession {
	
	PrintStream oldPrintStream;
	ByteArrayOutputStream bos;
	ByteArrayInputStream bis;

	private void setOutput() throws Exception {
		oldPrintStream = System.out;
		bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bos));
	}

	private String getOutput() { // throws Exception
		System.setOut(oldPrintStream);
		return bos.toString().trim();
	}
    
    private void setInput(String input){
    	bis = new ByteArrayInputStream(input.getBytes());
    	System.setIn(bis);
    	System.setIn(System.in);
    }
    
    public class SessionManagerStub implements Manager{
    	ArrayList<Products> productList;
    	ArrayList<Transaction> transactionList;
    	
    	public SessionManagerStub() {
    		productList = new ArrayList<>();
    		transactionList = new ArrayList<>();
    	};
    	
    	@Override
		public void addProduct(Products product) {
			productList.add(product);
		}
    	
    	@Override
    	public User getSessionUser() {
    		User sessionUser = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
    		return sessionUser;
    	}
    	
    	@Override
		public ArrayList<Products> getProducts() {
			return productList;
		}
    	
    	@Override
		public void setPorducts(ArrayList<Products> products) {
			productList = null;
		}
    	
    	@Override
		public ArrayList<Transaction> getTransactions() {
			return transactionList;
		}
    	
    	@Override
		public void readTransactionsDb() {
    		Transaction tr1 = new Transaction("Test1", "TestSeller", 2, 2, "test");
    		Transaction tr2 = new Transaction("Test2", "test", 2, 2, "TestBuyer");
    		transactionList.add(tr1);
    		transactionList.add(tr2);
		}
    	
		@Override
		public void removeProduct(Products product) {
		}
    	
    	@Override
		public void addTransaction(Transaction transaction) {	
		}

		@Override
		public void init() {
		}

		@Override
		public void addUser(User user) {
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
		public void writeTransactionDb() {
		}

		@Override
		public ArrayList<User> getUsers() {
			return null;
		}

		@Override
		public void setUsers(ArrayList<User> users) {
		}

		@Override
		public void setSessionUser(User sessionUser) {
		}

		@Override
		public boolean checkLogin(String username, String password) {
			return true;
		}
		
		@Override
		public boolean getStatus() {
			return true;
		}
    }
	
	@Test
	public void testGetSessionUser() {
		SessionManager.setInstance(null);
		SessionManager sm = SessionManager.getInstance();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, sm);
		User result = s.getSessionUser();
		assertEquals(test, result);
	}

	@Test
	public void testSetSessionUser() {
		SessionManager sm = SessionManager.getInstance();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, sm);
		User test2 = new User("admin", "admin", "admin", 1, 1, 2017, "Asia", "Student");
		s.setSessionUser(test2);
		User result = s.getSessionUser();
		assertEquals(test2, result);
	}
	
	@Test
	public void testStartSession() throws Exception {
		setOutput();
		SessionManager sm = SessionManager.getInstance();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, sm);
		String input = "Logout";
		setInput(input);
		s.startSession(true);
		boolean result = getOutput().contains("Goodbye");
		assertEquals(true, result);
	}
	
	@Test
	public void testProcessInput_01() throws Exception {
		SessionManagerStub smStub = new SessionManagerStub();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, smStub);
		String input = "Others";
		setInput(input);
		int result = s.processInput("Buy");
		assertEquals(0, result);
	}
	
	@Test
	public void testProcessInput_02() throws Exception {
		SessionManagerStub smStub = new SessionManagerStub();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, smStub);
		String input = "Test\r\nOthers\r\n2\r\n1";
		setInput(input);
		int result = s.processInput("Sell");
		assertEquals(0, result);
	}
	
	@Test
	public void testProcessInput_03() throws Exception {
		SessionManagerStub smStub = new SessionManagerStub();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, smStub);
		int result = s.processInput("History");
		assertEquals(0, result);
	}
	
	@Test
	public void testProcessInput_04() throws Exception {
		SessionManagerStub smStub = new SessionManagerStub();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		Session s = new Session(test, smStub);
		int result = s.processInput("Leave");
		assertEquals(-1, result);
	}

}
