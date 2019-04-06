package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.Cd;
import driver.Directory;
import driver.Mkdir;
import exceptions.NameAlreadyExsistException;

public class testMkdir {
  Mkdir mkdir;
  Cd cd;
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

    mkdir = new Mkdir();
    cd = new Cd();
  }

  @Test
  public void testMkdir1() throws NameAlreadyExsistException {
    mkdir.runCommand("/A/Z");
    cd.runCommand("Z");

    assertEquals("Z", Directory.getCurrDir().getDirName());
  }

  @Test
  public void testMkdir2() throws NameAlreadyExsistException {
    mkdir.runCommand("/A/C/D/Z");
    cd.runCommand("C/D/Z");

    assertEquals("Z", Directory.getCurrDir().getDirName());
  }

  @Test
  public void testMkdir3() throws NameAlreadyExsistException {
    mkdir.runCommand("/A/C/D/E/F/G/Z");
    cd.runCommand("C/D/E/F/G/Z");

    assertEquals("Z", Directory.getCurrDir().getDirName());
  }

}
