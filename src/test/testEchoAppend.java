package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.EchoStringAppend;
import driver.File;
import exceptions.IncorrectPathException;

/**
 * test case for echo append
 */
public class testEchoAppend {
	EchoStringAppend echo = new EchoStringAppend();
	Directory root;
	
	@Before
	/**
	 * setup befor test
	 */
	public void setup() {
		root = Directory.getRoot();
		Directory.updateCurrDir(root);
	}

	@Test
	/**
	 * test with file1
	 * @throws IncorrectPathException
	 */
	public void testAppendWithFile1() throws IncorrectPathException {
		String valueFromEcho = "";
		String string = "hello world";
		String fileName = "testFileWithFile1";
		ArrayList<File> files = root.getFiles();
		
		echo.runCommand(string, fileName);
		
		for (File file:files)
		{
			if (file.getFileName().equals(fileName))
			{
				valueFromEcho = file.getContent();
			}
		}
		
		assertEquals(string, valueFromEcho);
	}
	
	@Test
	/**
	 * test with file2
	 * @throws IncorrectPathException
	 */
	public void testAppendWithFile2() throws IncorrectPathException {
		String valueFromEcho = "";
		String string = "hello world";
		String fileName = "testFileWithFile2";
		
		File testFile = new File(fileName);
		String contentInFile = "test test 123";
		root.addFile(testFile);
		testFile.addContent(contentInFile);
		
		echo.runCommand(string, fileName);
		
		valueFromEcho = testFile.getContent();
		
		assertEquals(contentInFile + "\n" + string, valueFromEcho);
	}
	
	@Test
	/**
	 * test with path1
	 * @throws IncorrectPathException
	 */
	public void testAppendWithPath1() throws IncorrectPathException {
		String valueFromEcho = "";
		String string = "hello world";
		String fileName = "testFileWithPath1";
		String path = "A/" + fileName;
		
		// add a directory into the root
		Directory A = new Directory("A");
		root.addDirectory(A);
		
		ArrayList<File> files = A.getFiles();
		
		echo.runCommand(string, path);
		
		for (File file:files)
		{
			if (file.getFileName().equals(fileName))
			{
				valueFromEcho = file.getContent();
			}
		}
		
		assertEquals(string, valueFromEcho);
	}
	
	@Test
	/**
	 * test with path2
	 * @throws IncorrectPathException
	 */
	public void testAppendWithPath2() throws IncorrectPathException {
		String valueFromEcho = "";
		String string = "hello world";
		String fileName = "testFileWithPath2";
		String path = "B/" + fileName;
		
		// add a directory into the root
		Directory B = new Directory("B");
		root.addDirectory(B);
		
		File testFile = new File(fileName);
		String contentInFile = "test test 123";
		B.addFile(testFile);
		testFile.addContent(contentInFile);
		
		echo.runCommand(string, path);
		
		valueFromEcho = testFile.getContent();
		
		assertEquals(contentInFile + "\n" + string, valueFromEcho);
	}

}
