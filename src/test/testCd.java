package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.Cd;
import driver.Directory;

/**
 * test case for cd
 */
public class testCd {
  Cd cd;

  @Before
  /**
   * setup the necessary classes
   */
  public void setup() {
    // The hierarchy is as:
    // /
    // A
    // B C
    // D
    Directory.updateCurrDir(Directory.getRoot());

    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory D = new Directory("D");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    C.addDirectory(D);

    cd = new Cd();

  }

  @Test
  /**
   * test cd with first path
   */
  public void testCdPath1() {
    cd.runCommand("A/C");
    assertTrue(Directory.getCurrDir().getDirName() == "C");
  }

  @Test
  /**
   * test cd with second path
   */
  public void testCdPath2() {
    cd.runCommand("A/C/D");
    assertTrue(Directory.getCurrDir().getDirName() == "D");
  }

  @Test
  /**
   * test cd with root
   */
  public void testCdPathWithRoot() {
    cd.runCommand("/A/B");
    assertTrue(Directory.getCurrDir().getDirName() == "B");
  }

  @Test
  /**
   * test cd with..
   */
  public void testCdDotDot() {
    cd.runCommand("A/C");
    cd.runCommand("..");
    assertTrue(Directory.getCurrDir().getDirName() == "A");
  }

  @Test
  /**
   * test cd with .
   */
  public void testCdDot() {
    cd.runCommand("A/C");
    cd.runCommand(".");
    assertTrue(Directory.getCurrDir().getDirName() == "C");
  }

}
