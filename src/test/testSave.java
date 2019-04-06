package test;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;
import driver.Save;
import driver.Serialization;
import driver.Directory;
import exceptions.IncorrectPathException;

public class testSave {
  static Save save = new Save();
  static Directory root;
  static String file1Content = "file1content";
  static String file2Content = "file2content";
  static String file3Content = "file3content";

  @BeforeClass
  public static void setup() {}

  @Test
  public void testForSave() throws IncorrectPathException {
    boolean rightAnswer = true;
    save.runCommand("D:/here.txt");
    File file = new File("D:/here.txt");

    assertEquals(rightAnswer, file.exists());
  }

}
