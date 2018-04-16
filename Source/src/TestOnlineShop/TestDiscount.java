package TestOnlineShop;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import OnlineShop.Discount;
import OnlineShop.Manager;
import OnlineShop.Products;
import OnlineShop.Transaction;
import OnlineShop.User;

public class TestDiscount {
	
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
	public void testValid_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 2000, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		Discount discount = new Discount("Others", smStub, date);
		boolean result = discount.valid(user);
		assertEquals(false, result);
	}
	
	@Test
	public void testValid_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1950, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		Discount discount = new Discount("Others", smStub, date);
		boolean result = discount.valid(user);
		assertEquals(true, result);
	}
	
	@Test
	public void testGroceryDiscount_01( ) {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.GroceryDiscount("Grocery", date);
		assertEquals(0.9, result, 0.001);
	}
	
	@Test
	public void testGroceryDiscount_02( ) {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.GroceryDiscount("Others", date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testGroceryDiscount_03( ) {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.GroceryDiscount("Grocery", date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testBirthdayDiscount_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.BirthdayDiscount(user , date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testBirthdayDiscount_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 9);
		date.set(Calendar.MONTH, 9);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.BirthdayDiscount(user , date);
		assertEquals(0.85, result, 0.001);
	}
	
	@Test
	public void testBirthdayDiscount_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, 9);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.BirthdayDiscount(user , date);
		assertEquals(0.9, result, 0.001);
	}
	
	@Test
	public void testBirthdayDiscount_04() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 9);
		date.set(Calendar.MONTH, 3);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.BirthdayDiscount(user , date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 11);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(0.6, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 14);
		date.set(Calendar.MONTH, 11);
		System.out.print(date.get(Calendar.DAY_OF_MONTH));
		System.out.print(date.get(Calendar.MONTH));
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_04() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 31);
		date.set(Calendar.MONTH, 11);
		System.out.print(date.get(Calendar.DAY_OF_MONTH));
		System.out.print(date.get(Calendar.MONTH));
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_05() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		date.set(Calendar.MONTH, 1);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(0.7, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_06() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 3);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(0.8, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_07() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 20);
		date.set(Calendar.MONTH, 10);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(0.85, result, 0.001);
	}
	
	@Test
	public void testSeasonalDiscount_08() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 9, 9, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 26);
		date.set(Calendar.MONTH, 10);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.SeasonalDiscount(date);
		assertEquals(1, result, 0.001);
	}
	
	@Test
	public void testMaxDiscount_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1999, "Asia", "Student");
		smStub.setSessionUser(user);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.MaxDiscount();
		assertEquals(0.9, result, 0.001);
	}
	
	@Test
	public void testMaxDiscount_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "Asia", "Service");
		smStub.setSessionUser(user);
		smStub.readTransactionsDb();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 2, 2, "test");
		for (int i=0; i<10; i++) {
			smStub.addTransaction(tr1);
		}
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.MaxDiscount();
		assertEquals(0.8 * 0.85, result, 0.001);
	}
	
	@Test
	public void testGetDiscount_01() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1995, "Asia", "Service");
		smStub.setSessionUser(user);
		smStub.readTransactionsDb();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.getDiscount();
		assertEquals(0.8 * 0.95, result, 0.001);
	}
	
	@Test
	public void testGetDiscount_02() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1993, "Asia", "Senior Student");
		smStub.setSessionUser(user);
		smStub.readTransactionsDb();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 2, 2, "test");
		smStub.addTransaction(tr1);
		smStub.addTransaction(tr1);
		smStub.addTransaction(tr1);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.getDiscount();
		assertEquals(0.95 * 0.9, result, 0.001);
	}
	
	@Test
	public void testGetDiscount_03() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1993, "Asia", "VIP");
		smStub.setSessionUser(user);
		smStub.readTransactionsDb();
		Transaction tr1 = new Transaction("Test3", "TestSeller", 2, 2, "test");
		for (int i=0; i<10; i++) {
			smStub.addTransaction(tr1);
		}
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.getDiscount();
		assertEquals(0.85, result, 0.001);
	}
	
	@Test
	public void testGetDiscount_04() {
		SessionManagerStub smStub = new SessionManagerStub();
		User user = new User("test", "test", "test", 1, 1, 1993, "Asia", "VIP");
		smStub.setSessionUser(user);
		Transaction tr1 = new Transaction("Test3", "TestSeller", 2, 2, "test");
		smStub.addTransaction(tr1);
		Transaction tr2 = new Transaction("Test4", "test", 2, 2, "TestBuyer");
		smStub.addTransaction(tr2);
		Transaction tr3 = new Transaction("Test5", "TestSeller", 2, 2, "TestBuyer");
		smStub.addTransaction(tr3);
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 3);
		date.set(Calendar.MONTH, 5);
		Discount discount = new Discount("Others", smStub, date);
		double result = discount.getDiscount();
		assertEquals(1, result, 0.001);
	}

}
