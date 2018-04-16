package TestOnlineShop;

import static org.junit.Assert.*;

import org.junit.Test;

import OnlineShop.Manager;
import OnlineShop.Products;
import OnlineShop.Transaction;
import OnlineShop.User;
import OnlineShop.cmdUserHistory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestCmdUserHistory {
	
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
	
	public class SessionManagerStub implements Manager{
    	ArrayList<Transaction> transactionList;
    	
    	public SessionManagerStub() {
    		transactionList = new ArrayList<>();
    	};
    	
    	@Override
    	public User getSessionUser() {
    		User sessionUser = new User("test", "test", "test", 1, 1, 2017, "Asia", "Student");
    		return sessionUser;
    	}
    	
    	@Override
		public ArrayList<Transaction> getTransactions() {
			return transactionList;
		}
    	
    	@Override
		public void readTransactionsDb() {
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
		public void addProduct(Products product) {
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
		Transaction tr1 = new Transaction("Test1", "TestSeller", 2, 2, "test");
		Transaction tr2 = new Transaction("Test2", "test", 2, 2, "TestBuyer");
		smStub.addTransaction(tr1);
		smStub.addTransaction(tr2);
		cmdUserHistory cmd = new cmdUserHistory();
		cmd.execute(smStub);
		boolean result = getOutput().contains("History");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_02() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		cmdUserHistory cmd = new cmdUserHistory();
		cmd.execute(smStub);
		boolean result = getOutput().contains("No purchase or sales record found.");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_03() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test1", "TestSeller", 2, 2, "test");
		smStub.addTransaction(tr1);
		cmdUserHistory cmd = new cmdUserHistory();
		cmd.execute(smStub);
		boolean result = getOutput().contains("no records found");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_04() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr2 = new Transaction("Test2", "test", 2, 2, "TestBuyer");
		smStub.addTransaction(tr2);
		cmdUserHistory cmd = new cmdUserHistory();
		cmd.execute(smStub);
		boolean result = getOutput().contains("no records found");
		assertEquals(true, result);
	}
	
	@Test
	public void testExecute_05() throws Exception {
		setOutput();
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr2 = new Transaction("Test2", "admin", 2, 2, "TestBuyer");
		smStub.addTransaction(tr2);
		cmdUserHistory cmd = new cmdUserHistory();
		cmd.execute(smStub);
		boolean result = getOutput().contains("No purchase or sales record found.");
		assertEquals(true, result);
	}

}
