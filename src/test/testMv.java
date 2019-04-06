package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.Cd;
import driver.Directory;
import driver.File;
import driver.Ls;
import driver.Mv;
import driver.Pwd;
import exceptions.IncorrectPathException;
import exceptions.NameAlreadyExsistException;

public class testMv {
  Mv mv;
  Cd cd;
  Ls ls;
  Directory A;
  Directory B;
  Directory C;
  Directory D;

  @Before
  public void setup() {
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    File file1 = new File("file1");
    File file2 = new File("file2");
    C.addFile(file1);
    C.addFile(file2);

    try {
      mv.runCommand("A/C/", "A/B");
    } catch (Exception e) {
      System.out.println("error");
    }
    cd.runCommand("/A/B");
    System.out.println(ls.runCommand());
    cd.runCommand("/A/C");
    System.out.println(ls.runCommand());
  }

  @Test
  public void testMv1()
      throws IncorrectPathException, NameAlreadyExsistException {
    mv.runCommand("A/C/", "A/B");
    cd.runCommand("/A/B");
    String correctOutput = "C";
    String realOutput = ls.runCommand();
    assertEquals(correctOutput, realOutput);
  }

  @Test
  public void testMv2()
      throws NameAlreadyExsistException, IncorrectPathException {
    mv.runCommand("A/C/", "A/B");
    cd.runCommand("/A/C");
    String correctOutput = "C";
    String realOutput = ls.runCommand();
    assertEquals(correctOutput, realOutput);

  }
}
