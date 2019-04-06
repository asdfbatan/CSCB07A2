package test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.*;
import driver.URLconnectionreader;
import driver.Directory;
import driver.File;
import driver.GetUrl;

/**
 * test case for GetUrl
 */
public class testGetUrl {
  static GetUrl url = new GetUrl();
  static URLconnectionreader urlConnection = new URLconnectionreader();
  static Directory root;
  static String file1Content = "file1content";
  static String file2Content = "file2content";


  @BeforeClass
  /**
   * set up befor 
   */
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
    file1.addContent(file1Content);
    File file2 = new File("file2");
    file2.addContent(file2Content);
    A.addFile(file1);
    B.addFile(file2);

  }
  /**
   * actually we cant test this method since we use open connection in java io
   * so we mock we got the content already by buffer reader
   * and we test how we write into the file 
   * @throws Exception
   */
  @Test
  public void testForGetUrl1()throws Exception{
    File file1 = new File("file1");
    String rightAnswer = "file1content";
    // now mock that we got the string from url
    String urlContent = rightAnswer + "\n";
    // test the url connection class method
    urlConnection.editfilefromURL(urlContent, file1);
    String outputFromGet = file1.getContent();

    assertEquals(rightAnswer, outputFromGet);
  }

}
