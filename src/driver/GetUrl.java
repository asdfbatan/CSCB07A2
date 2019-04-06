package driver;

import java.net.*;

/**
 * A class for URLConnection, check the file from given url and create file in current directory.
 *
 */
public class GetUrl extends Commands {

  /**
   * The body part for Get Url command
   * 
   * @param url
   * @throws Exception
   */
  public void runcommand(String url) throws Exception {
    // split the input for getting the name and url
    String[] paths = url.split("/");
    String fileName = paths[paths.length - 1];
    // check if the file type is valid or not
    // only html and txt file is valid
    if (fileName.endsWith(".txt") == (fileName.endsWith(".html") == true)) {
      URLconnectionreader URL1 = new URLconnectionreader();
      URL1.runcommand(url);
    } else {
      System.out.println(
          "path with incorrect file type\n" + "valid for txt and html");
    }
  }
}
