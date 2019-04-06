package test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import driver.Tree;
import driver.Directory;
import driver.File;
import exceptions.IncorrectPathException;
/**
 * 
 * test cases for tree
 *
 */
public class testTree {
  static Tree tree = new Tree();
  static Directory root;
  static String file1Content = "file1content";
  static String file2Content = "file2content";
  static String file3Content = "file3content";

  @BeforeClass
  public static void setup() {
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory A1 = new Directory("A1");
    Directory A2 = new Directory("A2");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory D = new Directory("D");


    Directory.getRoot().addDirectory(A);
    A.addDirectory(A1);
    A.addDirectory(A2);
    A2.addDirectory(D);
    A.addDirectory(B);
    A.addDirectory(C);

    File file1 = new File("file1");
    File file2 = new File("file2");
    A.addFile(file1);
    B.addFile(file2);

  }
  /**
   * 
   * @throws IncorrectPathException
   */
  @Test
  public void testForTree() throws IncorrectPathException {
    String rightAnswer = "/\n\tA\n\t\tfile1\n\t\tA1\n\t\tA2\n\t\t\tD\n\t\tB\n\t\t\tfile2\n\t\tC";

    String outputFromFind = tree.runCommand();

    assertEquals(rightAnswer, outputFromFind);
  }

}
