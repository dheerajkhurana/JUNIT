package TestOnlineShop;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import OnlineShop.SessionManager;
import OnlineShop.cmdSignup;

public class TestCmdSignup {
	
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

	@Test
	public void testExecute() throws Exception {
		setOutput();
		cmdSignup cmd = new cmdSignup();
		String input = "test\r\ntest\r\ntest\r\n1\r\n1\r\n2017\r\nStudent\r\nNA";
		setInput(input);
		cmd.execute(SessionManager.getInstance());
		boolean result = getOutput().contains("signed up!");
		assertEquals(true, result);
	}

}
