package TestOnlineShop;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import OnlineShop.Manager;
import OnlineShop.Products;
import OnlineShop.RewardPoints;
import OnlineShop.Transaction;
import OnlineShop.User;

public class TestRewardPoints {
	
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
				transactionList.add(transaction);
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
				Transaction tr1 = new Transaction("Test1", "TestSeller", 10, 10, "test");
	    			Transaction tr2 = new Transaction("Test2", "TestSeller", 10, 10, "test");
	    			Transaction tr3 = new Transaction("Test2", "TestSeller", 10, 10, "test");
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
	public void testGettotalPoints_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		smStub.readTransactionsDb();
		User user= new User("test", "test", "test", 1, 1, 1999, "Hong Kong", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		double result = pt.gettotalPoints();
		assertEquals(3, result, 0.0001);
	}
	
	@Test
	public void testGettotalPoints_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 1000, 1000, "test");
		for (int i=0; i<5; i++) {
			smStub.addTransaction(tr1);
		}
		User user= new User("test", "test", "test", 1, 1, 1999, "Hong Kong", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		double result = pt.gettotalPoints();
		assertEquals(510, result, 0.0001);
	}
	
	@Test
	public void testGettotalPoints_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 1000, 1000, "TestBuyer");
		for (int i=0; i<5; i++) {
			smStub.addTransaction(tr1);
		}
		User user= new User("test", "test", "test", 1, 1, 1999, "Hong Kong", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		double result = pt.gettotalPoints();
		assertEquals(0, result, 0.0001);
	}

	@Test
	public void testGetWatchStr_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 100000, 10000, "test");
		for (int i=0; i<2; i++) {
			smStub.addTransaction(tr1);
		}
		User user= new User("test", "test", "test", 1, 1, 1999, "Hong Kong", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		String result = pt.getWatchStr();
		assertEquals("Congrats! You will receive an Apple Watch for 20 000 Reward Points!", result);
	}
	
	@Test
	public void testGetWatchStr_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 10, 10, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		String result = pt.getWatchStr();
		assertEquals("", result);
	}
	
	@Test
	public void testGetWatchStr_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		String result = pt.getWatchStr();
		assertEquals("", result);
	}
	
	@Test
	public void testGetNewPoints_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.getNewPoints();
		assertEquals(10, result);
	}
	
	@Test
	public void testSetNewPoints_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 11);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10 * 4, result);
	}
	
	@Test
	public void testSetNewPoints_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10, result);
	}
	
	@Test
	public void testSetNewPoints_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 31);
		date.set(Calendar.MONTH, 11);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10, result);
	}
	
	@Test
	public void testSetNewPoints_04() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		date.set(Calendar.MONTH, 1);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10 * 3, result);
	}
	
	@Test
	public void testSetNewPoints_05() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 3);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10 * 2, result);
	}
	
	@Test
	public void testSetNewPoints_06() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 10);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10 + 10/2, result);
	}
	
	@Test
	public void testSetNewPoints_07() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 10);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10, result);
	}
	
	@Test
	public void testSetNewPoints_08() {
		SessionManagerStub smStub = new SessionManagerStub();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 300000, 300000, "test");
		smStub.addTransaction(tr1);
		User user = new User("test", "test", "test", 1, 1, 1999, "NA", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		date.set(Calendar.MONTH, 5);
		RewardPoints pt = new RewardPoints(100, smStub, date);
		int result = pt.setNewPoints(100, date);
		assertEquals(10, result);
	}

}
