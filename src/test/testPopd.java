package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.Cd;
import driver.Directory;
import driver.Mkdir;
import driver.Popd;
import driver.Pushd;
import java.util.ArrayList;

public class testPopd {
  Popd popd;
  Directory A;
  Directory B;
  Directory C;
  Directory D;

  @Before
  public void setup() {
    // The hierarchy is as:
    //   A
    // B   C
    //       D
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

    popd = new Popd();
  }


  @Test
  public void testPopEmptyStack() {
	  //create representation of directory stack
	  Pushd pushd = new Pushd();
	  popd.runCommand();
	  System.out.println(Directory.getCurrDir().getDirName());
	  assertTrue(Directory.getCurrDir().getDirName() == "A");
  }
  
  @Test
  public void testNonEmptyStack() {
	  Pushd pushd = new Pushd();
	  pushd.getDirStack().add(0, B);
	  //add C to the stack, then check to see if working directory has changed
	  // to C
	  pushd.getDirStack().add(0, C);
	  popd.runCommand();
	  assertTrue(Directory.getCurrDir().getDirName() == "C");
  }
}
