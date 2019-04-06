package test;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import driver.Man;

public class testMan {
  Man man;

  @Before
  public void setup() {
    man = new Man();
  }

  @Test
  public void testManWithCat() {
    String outputFromCommand = man.runCommand("cat");
    
    assertEquals(
        "cat file1[file2]:show the content of the file by given" + " names\n",
        outputFromCommand);
  }

  @Test
  public void testManWithExit() {
	String outputFromCommand = man.runCommand("exit");

    assertEquals("exit: Quit the program\n", outputFromCommand);
  }

  @Test
  public void testManWithPwd() {
	String outputFromCommand = man.runCommand("pwd");

    assertEquals("pwd: Print the current working directory (including"
        + " the whole path). \n", outputFromCommand);
  }

}
