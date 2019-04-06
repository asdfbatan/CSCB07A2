package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import driver.Directory;
import driver.File;
import driver.Redirection;

public class testRedirection {
	Redirection redirection = new Redirection();
	Directory root;
	

	@Before
	public void setup() {
		root = Directory.getRoot();
		Directory.updateCurrDir(root);
	}
	
	@Test
	public void testRedirectionAppend1() {
		String valueFromRedirection = "";
		List<String> param = new ArrayList<String>();
		param.add(">>");
		param.add("testFileAppend1");
		
		redirection.runCommand("hello world", param);
		
		ArrayList<File> files = root.getFiles();
		
		for (File file:files)
		{
			if (file.getFileName().equals("testFileAppend1"))
			{
				valueFromRedirection = file.getContent();
			}
		}
		assertEquals("hello world", valueFromRedirection);
	}
	
	@Test
	public void testRedirectionAppend2() {
		List<String> param = new ArrayList<String>();
		param.add(">>");
		param.add("testFileAppend2");
		
		File testFile = new File("testFileAppend2");
		testFile.addContent("test test 123");
		root.addFile(testFile);
		
		redirection.runCommand("hello world", param);
		
		assertEquals("test test 123hello world", testFile.getContent());
	}
	
	@Test
	public void testRedirectionOverwrite1() {
		String valueFromRedirection = "";
		List<String> param = new ArrayList<String>();
		param.add(">");
		param.add("testFileOverwrite1");
		
		redirection.runCommand("hello world", param);
		
		ArrayList<File> files = root.getFiles();
		
		for (File file:files)
		{
			if (file.getFileName().equals("testFileOverwrite1"))
			{
				valueFromRedirection = file.getContent();
			}
		}
		assertEquals("hello world", valueFromRedirection);
	}
	
	
	@Test
	public void testRedirectionOverwrite2() {
		List<String> param = new ArrayList<String>();
		param.add(">");
		param.add("testFileOverwrite2");
		
		File testFile = new File("testFileOverwrite2");
		testFile.addContent("test test 123");
		root.addFile(testFile);
		
		redirection.runCommand("hello world", param);
		
		assertEquals("hello world", testFile.getContent());
	}
	

}
