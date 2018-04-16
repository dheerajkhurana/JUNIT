package TestOnlineShop;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import OnlineShop.Category;
import OnlineShop.Manager;
import OnlineShop.Transaction;
import OnlineShop.User;
import OnlineShop.Products;
import OnlineShop.cmdSellProduct;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestCmdSellProduct {
	
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
		return bos.toString();
	}
	
	private void setInput(String input){
    	bis = new ByteArrayInputStream(input.getBytes());
    	System.setIn(bis);
    	
    	System.setIn(System.in);
    }
	
	public class SessionManagerStub implements Manager{
    	ArrayList<OnlineShop.Products> productList;
    	
    	public SessionManagerStub() {
    		productList = new ArrayList<>();
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
			return true;
		}
    }

	@Test
	public void testExecute_01() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nOthers\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_02() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nWeapon\r\nOthers\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Invalid Input");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_03() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nOthers\r\nTwo\r\n1\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Please input an integer");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_04() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nOthers\r\n2\r\nOne\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("Please input an integer");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_05() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nElectronic Appliances\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_06() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nFurniture\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_07() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nGrocery\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_08() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nApparel\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_09() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nBooks\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_10() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdSellProduct cmd = new cmdSellProduct();
		String input = "Test\r\nSports\r\n2\r\n1";
		setInput(input);
		cmd.execute(smStub);
		boolean result = getOutput().contains("succesfully");
		assertEquals(true, result);
	}

}