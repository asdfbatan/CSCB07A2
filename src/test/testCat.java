package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import driver.Cat;
import driver.Directory;
import driver.File;
import exceptions.IncorrectPathException;

/**
 * Test case for cat
 */
public class testCat {
  /**
   * initial variables
   */
  static Cat cat = new Cat();
  static Directory root;
  static String file1Content = "file1content";
  static String file2Content = "file2content";
  static String file3Content = "file3content";
	
  @BeforeClass
  /**
   * setup the classes
   */
  public static void setup() {
	root = Directory.getRoot();
	Directory.updateCurrDir(root);
	
	File file1 = new File("file1");
	file1.addContent(file1Content);
	root.addFile(file1);
	
	File file2 = new File("file2");
	file2.addContent(file2Content);
	root.addFile(file2);
	
	File file3 = new File("file3");
	file3.addContent(file3Content);
	root.addFile(file3);
	
  }

  @Test
  /**
   * test with one file
   * @throws IncorrectPathException
   */
  public void testWithOneFile() throws IncorrectPathException {
	String rightAnswer = file1Content;
	String param = "file1";
    
	String outputFromCat = cat.runCommand(param);
	
	assertEquals(rightAnswer, outputFromCat);
  }
  
  @Test
  /**
   * test with two files
   * @throws IncorrectPathException
   */
  public void testWithTwoFile() throws IncorrectPathException {
	String rightAnswer = file1Content + "\n\n\n\n" + file2Content;
	String param = "file1 file2";
    
	String outputFromCat = cat.runCommand(param);
	
	assertEquals(rightAnswer, outputFromCat);
  }
  
  @Test
  /**
   * test with three files
   * @throws IncorrectPathException
   */
  public void testWithThreeFile() throws IncorrectPathException {
	String rightAnswer = file1Content + "\n\n\n\n" + file2Content + "\n\n\n\n"
  + file3Content;
	String param = "file1 file2 file3";
    
	String outputFromCat = cat.runCommand(param);
	
	assertEquals(rightAnswer, outputFromCat);
  }

}
