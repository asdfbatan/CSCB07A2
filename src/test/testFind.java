package test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import driver.Find;
import driver.Directory;
import driver.File;
import exceptions.IncorrectPathException;
/**
 * test case for find
 */
public class testFind {
  static Find find = new Find();
  static Directory root;
  static String file1Content = "file1content";
  static String file2Content = "file2content";
  static String file3Content = "file3content";

  @BeforeClass
  /**
   * setup before test
   */
  public static void setup() {
    root = Directory.getRoot();
    Directory.updateCurrDir(root);

    File file1 = new File("file1");
    file1.addContent(file1Content);

    File file2 = new File("file2");
    file2.addContent(file2Content);
    
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    B.addDirectory(C);
    B.addFile(file1);
    B.addFile(file2);

  }

  @Test
  /**
   * test with file and full path
   * @throws IncorrectPathException
   */
  public void testWithFileAndFullPath() throws IncorrectPathException {
    String rightAnswer = "Find file file1 in given path/A/B" + "\n";
    String param = "/A/B -type f -name \"file1\"";

    String outputFromFind = find.runCommand(param);

    assertEquals(rightAnswer, outputFromFind);
  }

  @Test
  /**
   * test with directory and full path
   * @throws IncorrectPathException
   */
  public void testWithDirectoryAndFullPath() throws IncorrectPathException {
    String rightAnswer = "Find directory B in given path/A" + "\n";
    String param = "/A -type d -name \"B\"";

    String outputFromFind = find.runCommand(param);

    assertEquals(rightAnswer, outputFromFind);
  }

  @Test
  /**
   * test with file but not full path
   * @throws IncorrectPathException
   */
  public void testWithFileNotFullPath() throws IncorrectPathException {
    String rightAnswer = "Find file file1 in given path/A" + "\n";
    String param = "/A -type f -name \"file1\"";

    String outputFromFind = find.runCommand(param);

    assertEquals(rightAnswer, outputFromFind);
  }

  @Test
  /**
   * test with directory but not full path
   * @throws IncorrectPathException
   */
  public void testWithDirectoryNotFullPath() throws IncorrectPathException {

    String rightAnswer = "Find directory C in given path/A" + "\n";
    String param = "/A -type d -name \"C\"";

    String outputFromFind = find.runCommand(param);

    assertEquals(rightAnswer, outputFromFind);
  }

}
