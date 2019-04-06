package test;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import driver.Cd;
import driver.Directory;
import driver.File;
import driver.Ls;
import driver.Mkdir;

public class testLs {
  Ls ls; // declare a Ls object

  Directory A = new Directory("A");
  Directory B = new Directory("B");
  Directory C = new Directory("C");
  Directory D = new Directory("D");
  File e = new File("e");
  File f = new File("f");
  
  @Before
  public void setup() {
    // The hierarchy is as:
    // /
    // A
    // B C
    // D
    Directory.updateCurrDir(Directory.getRoot());

    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    C.addDirectory(D);
    Directory.getRoot().addFile(e);
    C.addFile(f);

    ls = new Ls();
  }

  @Test
  public void testLs() {
    String outputFromCommand = ls.runCommand();

    String rightAnswer = "A\ne";

    assertEquals(rightAnswer, outputFromCommand);

  }
  
  @Test
  public void testLsWithParam1() {
    String outputFromCommand = ls.runCommand("A/C");

    String rightAnswer = "A/C:\nD\nf";

    assertEquals(rightAnswer, outputFromCommand);

  }
  
  @Test
  public void testLsWithParam2() {
    String outputFromCommand = ls.runCommand("/A/C");

    String rightAnswer = "/A/C:\nD\nf";

    assertEquals(rightAnswer, outputFromCommand);

  }
  
  @Test
  public void testLsWithParam3() {
    String outputFromCommand = ls.runCommand("A/B");

    String rightAnswer = "A/B:";

    assertEquals(rightAnswer, outputFromCommand);

  }
  
  @Test
  public void testLsWithParam4() {
    String outputFromCommand = ls.runCommand("/A/B");

    String rightAnswer = "/A/B:";

    assertEquals(rightAnswer, outputFromCommand);

  }



}
