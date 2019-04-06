package driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
/*
 * need to check the file's type
 */

/**
 * A class read URLconnection
 *
 */
public class URLconnectionreader {

  /**
   * This method return the string from the given URL
   * 
   * @return output
   */
  public String getStringFromURL(String web, String output) throws Exception {
    // construct an url object to open the connection
    URL website = new URL(web);
    // get the content from the website
    URLConnection yc = website.openConnection();
    BufferedReader in =
        new BufferedReader(new InputStreamReader(yc.getInputStream()));
    String inputLine;
    // add content to the String content line by line
    while ((inputLine = in.readLine()) != null)
      output += inputLine ;
      output += "\n";
    in.close();
    return output;
  }
  /**
   * This method edit the file by add content from the edited string
   * 
   * @return null
   */
  public void editfilefromURL(String editedStr, File file) throws Exception {
    // change file content form given edited Url Content String
	// erase the duplicate /n in the file content 
	file.replaceContent(editedStr);
	file.reducechar(2);
  }
  /**
   * This method returns the content of the mutated file.
   * 
   * @return filecontent
   */
  public void runcommand(String url) throws Exception {
    // split the input for getting the name and url
    String[] paths = url.split("/");
    String Filename = paths[paths.length - 1];
    // construct the file and use the helper functin to complete the task
    File file1 = new File(Filename);
    Directory.getCurrDir().addFile(file1);
    file1.setDirectory(Directory.getCurrDir());
    String editedString = "";
    this.getStringFromURL(url, editedString);
    // edit the file
    this.editfilefromURL(editedString, file1);
    // get the content if we need, able to erase the following if we should
    // return null
  }

  public static void main(String[] args) throws Exception {
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
    Directory.updateCurrDir(D);
    URLconnectionreader URL1 = new URLconnectionreader();
    String a = "https://jsonplaceholder.typicode.com/photos";

  }

}
