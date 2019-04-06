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
 * This class saves the current working directory by pushing onto directory
 * stack and then changes the new current working directory to DIR
 *
 */
public class Pushd extends Commands {
  /**
   * The directory stack, where the 0th index is the top of the stack.
   */
  private static ArrayList<Directory> dirStack;

  /**
   * constructor for pushd
   */
  public Pushd() {}

  /**
   * method to retrieve the directory stack/
   * 
   * @return dirStack the only instance of directory stack.
   */
  public static ArrayList<Directory> getDirStack() {
    if (dirStack == null) {
      dirStack = new ArrayList<Directory>();
    }
    return dirStack;
  }

  /**
   * Replace stack
   * @param stack
   */
  public static void replaceStcak(ArrayList<Directory> stack) {
    dirStack = stack;
  }

  /**
   * This method will take in a Directory Object, push the current directory
   * onto the stack of Directories (ArrayList), and then change directories to
   * the given directory.
   * 
   */
  public String runCommand(String newDirectory) {
    // add current directory onto stack
    Directory currDir = Directory.getCurrDir();
    Pushd.getDirStack().add(0, currDir);

    // call cd here to change directories
    Cd cd = new Cd();
    cd.runCommand(newDirectory);
    return "";
  }
}
