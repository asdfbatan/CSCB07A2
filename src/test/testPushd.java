package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import driver.Cd;
import driver.Directory;
import driver.Pushd;

public class testPushd {
  Directory A;
  Directory B;
  Directory C;
  Directory D;
  ArrayList<Directory> rightAnswer;

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
  }

  @Test
  public void testPushd1() {
    Pushd pushd = new Pushd();
    Cd cd = new Cd();

    rightAnswer = new ArrayList<Directory>();

    cd.runCommand("/A/C");
    rightAnswer.add(C);

    pushd.runCommand("/A/B");


    assertEquals(pushd.getDirStack(), rightAnswer);
  }

  @Test
  public void testPushd2() {
    Pushd pushd = new Pushd();
    Cd cd = new Cd();

    for (Directory i : pushd.getDirStack()) {
      System.out.print(i.getDirName() + " ");
    }
    System.out.println("");

    rightAnswer = new ArrayList<Directory>();

    cd.runCommand("/A/C");
    rightAnswer.add(C);

    pushd.runCommand("/A/B");
    rightAnswer.add(0, B);

    pushd.runCommand("/A/C/D");

    for (Directory i : pushd.getDirStack()) {
      System.out.print(i.getDirName() + " ");
    }
    System.out.println("");

    assertEquals(pushd.getDirStack(), rightAnswer);
  }

}
