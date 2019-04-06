// **********************************************************
// Assignment2:
// Student1: Yuk Ming Lam
// UTORID user_name: lamyuk1
// UT Student #: 1002329358
// Author: Carlos Yuk Ming Lam
//
// Student2: Jeffery Wei Xuan Su
// UTORID user_name: sujeffer
// UT Student #: 1004139684
// Author: Jeffery Wei Xuan Su
//
// Student3: Tian Xia
// UTORID user_name:xiatia18
// UT Student #: 1004440410
// Author: xiatian
//
// Student4: Zhang Ti
// UTORID user_name: zhan5263
// UT Student #: 1004424517
// Author: zhangti
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;

import java.util.ArrayList;

/**
 * This class prints out the name of the current directory, including the whole
 * path.
 * 
 */
public class Pwd extends Commands {

  /**
   * constructor for pwd
   */
  public Pwd() {}

  /**
   * This method prints out the full path to the current directory.
   * 
   * @return returnString a String representation of path to current directory
   */
  public String runCommand() {
    String returnString = "";
    // get the Directory the user is currently on
    Directory directory = Directory.getCurrDir();
    ArrayList<Directory> fullDir = directory.getFullPath();

    if (fullDir.size() != 1) {
      String fullDirString = "";
      for (Directory i : fullDir) {
        if (i.getDirName() != "/") {
          fullDirString = "/" + i.getDirName() + fullDirString;
        }
      }
      returnString = fullDirString + "\n";
    } else if (fullDir.size() == 1) {
      returnString = "/\n";
    }
    returnString = returnString.trim();
    return returnString;
  }
}
