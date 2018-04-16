package TestOnlineShop;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import OnlineShop.Manager;
import OnlineShop.Transaction;
import OnlineShop.User;
import OnlineShop.cmdBuyProducts;
import OnlineShop.Products;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

public class TestCmdBuyProducts {
	
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
    		User sessionUser = new User("test", "test", "test", 1, 1, 1999, "Asia", "VIP");
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
		public void removeProduct(Products product) {
		}
    	
    	@Override
		public void addTransaction(Transaction transaction) {	
    		transactionList.add(transaction);
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
		public void readTransactionsDb() {
			Transaction tr1 = new Transaction("Test1", "TestSeller", 2, 2, "test");
			Transaction tr2 = new Transaction("Test2", "TestSeller", 2, 2, "test");
			Transaction tr3 = new Transaction("Test2", "TestSeller", 2, 2, "test");
			transactionList.add(tr1);
			transactionList.add(tr2);
			transactionList.add(tr3);
		}

		@Override
		public void writeTransactionDb() {
		}

		@Override
		public ArrayList<Transaction> getTransactions() {
			return transactionList;
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
	public void testAcceptOffer_01() {
		cmdBuyProducts cmd = new cmdBuyProducts();
		Products testProduct = new Products("Test", "TestSeller", 2, 1, "Others");
		boolean result = cmd.AcceptOffer(3, testProduct);
		assertEquals(true, result);
	}
	
	@Test
	public void testAcceptOffer_02() {
		cmdBuyProducts cmd = new cmdBuyProducts();
		Products testProduct = new Products("Test", "TestSeller", 2, 1, "Others");
		boolean result = cmd.AcceptOffer(0, testProduct);
		assertEquals(false, result);
	}

	@Test
	public void testExecute_01() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdBuyProducts cmd = new cmdBuyProducts();
		String input = "Others";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("later!");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_02() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.addProduct(new Products("Test", "TestSeller", 2, 1, "Others"));
		cmdBuyProducts cmd = new cmdBuyProducts();
		String input = "Others\r\nTest\r\nTestSeller\r\nOne\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Your Purchase was successful!");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_03() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.addProduct(new Products("Test", "TestSeller", 2, 2, "Others"));
		cmdBuyProducts cmd = new cmdBuyProducts();
		String input = "Others\r\nTest\r\nTestSeller\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("improve");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_04() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.addProduct(new Products("Test", "TestSeller", 3, 2, "Others"));
		cmdBuyProducts cmd = new cmdBuyProducts();
		String input = "Sports\r\niPhone\r\nBob\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Please select one of the above types only, otherwise state the Others category.");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_05() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.addProduct(new Products("Test", "TestSeller", 3, 2, "Others"));
		cmdBuyProducts cmd = new cmdBuyProducts();
		String input = "Others\r\nTest\r\nBob\r\nTest\r\nTestSeller\r\n3";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Product Name and Seller Name does not match. Please try again!");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_06() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.addProduct(new Products("Test", "TestSeller", 3, 2, "Others"));
		cmdBuyProducts cmd = new cmdBuyProducts();
		String input = "Others\r\niphone\r\nTestSeller\r\nTest\r\nTestSeller\r\n3";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Product Name and Seller Name does not match. Please try again!");
		assertEquals(true, result);
	}
	
	@Test
	public void testTotalPrice_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.readTransactionsDb();
		cmdBuyProducts cmd = new cmdBuyProducts();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		double result = cmd.getTotalPrice("Others", 100, smStub, date);
		assertEquals(100*0.95*1.1, result, 0.0001);
	}
	
	@Test
	public void testTotalPrice_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.readTransactionsDb();
		cmdBuyProducts cmd = new cmdBuyProducts();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		double result = cmd.getTotalPrice("Others", 100000, smStub, date);
		assertEquals(100000*0.95, result, 0.0001);
	}
	
}
