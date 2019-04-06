package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import driver.History;

/**
 * test case for history
 */
public class testHistory {
  History history = new History();
  
  @Before
  /**
   * set up before test
   */
  public void testHistory() {
	History.addHistory("a");
	History.addHistory("b");
	History.addHistory("c");
	History.addHistory("d");
	}
  
  @After
  /**
   * clean the history array
   */
  public void cleanHistory() {
	ArrayList<String> newList = new ArrayList<String>();
	History.replaceHistory(newList);
  }


  @Test
  /**
   * test the history with no paramter
   */
  public void testHistoryWithNoParameter() {
	
	String outputFromCommand = history.runCommand();
	System.out.println(outputFromCommand);
    
    String rightAnswer = "1. a\n2. b\n3. c\n4. d";
    assertEquals(rightAnswer, outputFromCommand);
  }
  
  @Test
  /**
   * test the history with parameter 1
   */
  public void testHistoryWithParameter1() {
	
	String outputFromCommand = history.runCommand("2");
	
	String rightAnswer = "3. c\n4. d\n";
	assertEquals(rightAnswer, outputFromCommand);
  }
  
  @Test
  /**
   * test the history with parameter 2
   */
  public void testHistoryWithParameter2() {
	
	String outputFromCommand = history.runCommand("3");
	
	String rightAnswer = "2. b\n3. c\n4. d\n";
	assertEquals(rightAnswer, outputFromCommand);
  }
  
  @Test
  /**
   * test the history with parameter 3
   */
  public void testHistoryWithParameter3() {
	History.addHistory("e");
	
	String outputFromCommand = history.runCommand("2");
	
	String rightAnswer = "4. d\n5. e\n";
	assertEquals(rightAnswer, outputFromCommand);
  }

}
