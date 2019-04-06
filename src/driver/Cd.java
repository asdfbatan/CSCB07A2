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
import java.util.List;

/**
 * This is the Cd class that handles the case for when the user wants to change
 * the current working directory to a new directory.
 *
 */
public class Cd extends Commands {
  /**
   * The constructor for the cd object.
   */
  public Cd() {

  }

  /**
   * This method changes the current working directory to a new directory. '..'
   * denotes the previous (parent) directory from the current directory. '.'
   * denotes the current working directory A directory starting with '/' is a
   * full path from the root. Everything else is a relative directory.
   * 
   * @param input a String that represents the path to a new working directory
   */
  public String runCommand(String input) {
    // check the input and separate it into 5 cases.
    // Case 1 if user wants previous directory
    if (input.equals("..")) {
      Directory.updateCurrDir(Directory.getCurrDir().getPrevDir());
      // Case 2, if user wants current directory. Can be omitted but is
      // included for clarity.
    } else if (input.equals(".")) {
    }
    // Case 3 is if user wants to cd relative to current directory.
    else if (input.charAt(0) != '/') {
      Cd.runHelper(input, Directory.getCurrDir());
    }
    // Case 4, if user wants to cd to root.
    else if (input.charAt(0) == '/' && input.length() == 1) {
      Directory.updateCurrDir(Directory.getRoot());
    }
    // Case 5, if user wants to cd to an absolute path.
    else if (input.charAt(0) == '/') {
      // similar to case 3, except we will start at the root.
      // First we will update current directory to the root. Then, the
      // input path will be a path relative to current directory.
      Directory.updateCurrDir(Directory.getRoot());
      // now, change the string to reflect a path relative to current
      // directory
      String newInput = input.substring(1);
      // same case 3 from here.
      Cd.runHelper(newInput, Directory.getCurrDir());
    }
    return "";
  }

  /**
   * A helper method for runCommand. This method assumes that the given path is
   * a relative path, and changes the current working directory to the given
   * path.
   * 
   * @param input a relative path to the new working directory
   * @param start a Directory object denoting the current working directory
   */
  public static void runHelper(String input, Directory start) {
    // We assume from here that it is relative path. The difference between
    // relative path and absolute path can be solved by removing the first
    // "/" in the string, and then starting from root rather than current
    // directory. Thus, the following removes duplicate code.

    // have a pointer to the directory we are working in.
    Directory dirPointer = start;
    // get the children of the pointer. These will be Directory Objects.
    ArrayList<Directory> dirPointerChildren = new ArrayList<Directory>();
    dirPointerChildren = dirPointer.getChildDir();
    // split the input into an array of Strings.
    // Each String represents the name of the next directory we want to
    // change into.
    String[] dirNames = input.split("/");
    // loop through the names, starting from the name of the first dir we
    // want to change into
    for (String dirName : dirNames) {
      // here we will loop through the child Directories until the name
      // matches the directory we want to change to.
      for (Directory child : dirPointerChildren) {
        if (child.getDirName().equals(dirName)) {
          Directory.updateCurrDir(child);
          // after updating current directory, update the directory
          // pointer and the ArrayList of children
          dirPointer = child;
          dirPointerChildren = dirPointer.getChildDir();
        }
      }
    }
  }
}
