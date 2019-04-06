package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.Exit;
/**
 * test case for exit
 */
public class testExit {
  Exit exit; // declare exit

  @Before
  /**
   * setup before test
   */
  public void setup() {
    exit = new Exit();

  }

  @Test
  /**
   * test the state
   */
  public void testStateOfExit() {
    exit.runCommand();
    assertTrue(exit.state == 2);
  }

  @Test
  /**
   * test exit
   */
  public void testExit() {
    assertTrue(exit.state == 0);
    exit.runCommand();
    assertTrue(exit.state == 2);
  }

}
