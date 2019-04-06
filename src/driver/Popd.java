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
 * 
 * This class retrieves and removes the latest stored Directory from the
 * directory stack, and changes it the current directory.
 * 
 */
public class Popd extends Commands {

  /**
   * constructor for popd
   */
  public Popd() {}

  /**
   * This method removes the directory from the top of the pushd stack, and
   * changes the current working directory to the directory.
   */
  public String runCommand() {
    // pop from non-empty stack
    try {
      // remove the latest directory from the stack
      Directory changeToDir = Pushd.getDirStack().remove(0);
      String dirPath = changeToDir.getDirName();
      // build a String path by looping until we get to root.
      String parentDirName = changeToDir.getPrevDir().getDirName();
      while (!(parentDirName.equals("/"))) {
        dirPath = parentDirName + "/" + dirPath;
        changeToDir = changeToDir.getPrevDir();
        parentDirName = changeToDir.getPrevDir().getDirName();
      }
      dirPath = "/" + dirPath;
      // change to new directory by calling CdDIR
      Cd cd = new Cd();
      cd.runCommand(dirPath);
    }
    // pop from empty stack
    catch (IndexOutOfBoundsException e) {
      return ("The directory stack is empty");
    }
    return "";
  }

}
