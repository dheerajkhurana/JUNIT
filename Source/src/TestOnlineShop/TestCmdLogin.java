package TestOnlineShop;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import OnlineShop.Manager;
import OnlineShop.Products;
import OnlineShop.SessionManager;
import OnlineShop.Transaction;
import OnlineShop.User;
import OnlineShop.cmdLogin;

public class TestCmdLogin {

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
    	private ArrayList<User> usersList;
    	
    	public SessionManagerStub() {
    		usersList = new ArrayList<>();
    	};
    	
    	@Override
		public void addProduct(Products product) {
		}
    	
    	@Override
    	public User getSessionUser() {
    		User sessionUser = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
    		return sessionUser;
    	}
    	
    	@Override
		public ArrayList<Products> getProducts() {
			return null;
		}
    	
    	@Override
		public void setPorducts(ArrayList<Products> products) {
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
			usersList.add(user);
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
			return false;
		}
    }
	
	@Test
	public void testExecute_01() throws Exception {
		setOutput();
		SessionManager sm = SessionManager.getInstance();
		cmdLogin cmd = new cmdLogin();
		String input = "fakeusername\r\nfakepassword";
		setInput(input);
		cmd.execute(sm);
		boolean result = getOutput().contains("Invalid");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_02() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		User test = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
		smStub.addUser(test);
		cmdLogin cmd = new cmdLogin();
		String input = "test\r\ntest";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Logged");
		assertEquals(true, result);
	}

}
