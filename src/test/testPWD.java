package test;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import driver.Directory;
import driver.Pwd;

public class testPWD {
  Pwd pwd;
  Directory A;
  Directory B;
  Directory C;
  Directory D;

  @Before
  public void setup() {
    // The hierarchy is as:
    // A
    // B C
    // D
    Directory.updateCurrDir(Directory.getRoot());

    A = new Directory("A");
    B = new Directory("B");
    C = new Directory("C");
    D = new Directory("D");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    C.addDirectory(D);
    Directory.updateCurrDir(A);

    pwd = new Pwd();
  }

  @Test
  public void testPwd1() {
    String outputFromCommand = pwd.runCommand();
    
    assertEquals("/A", outputFromCommand);
  }

  @Test
  public void testPwd2() {
    Directory.updateCurrDir(D);

    String outputFromCommand = pwd.runCommand();
    
    assertEquals("/A/C/D", outputFromCommand);
  }

}
