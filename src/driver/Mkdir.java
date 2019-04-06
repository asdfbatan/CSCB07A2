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

import java.util.Arrays;
import exceptions.*;
import java.util.List;
import java.util.ArrayList;

/**
 * This class is responsible for creating Directories, each of which may be
 * relative to the current directory, or may be a full path.
 *
 */
public class Mkdir extends Commands {
  /**
   * constructor for mkdir
   */
  public Mkdir() {}

  /**
   * This method takes in a path of directories and creates all directories
   * along the path that do not exist.
   * 
   * @param input the path of the
   * @throws NameAlreadyExsistException
   */
  public String runCommand(String input) throws NameAlreadyExsistException {
    Mkdir mkdir = new Mkdir();
    // store current directory
    Directory initDirectory = Directory.getCurrDir();
    // If the directory is an absolute path, we can make it relative by
    // changing directory to the root.
    if (input.charAt(0) == '/') {
      Directory.updateCurrDir(Directory.getRoot());
      // Then, mkdir is now a relative path; get rid of the first '/'
      input = input.substring(1);
    }
    ArrayList<String> dirList =
        new ArrayList<String>(Arrays.asList(input.split("/")));
    mkdir.mkDirCurrent(dirList);

    // change the current directory back to the initial current directory
    Directory.updateCurrDir(initDirectory);
    return "";
  }

  /**
   * Creates the full path of Directories relative to current path, and changes
   * the current directory to the last directory created.
   * 
   * @param input is an array of all Directories to be created.
   * @throws NameAlreadyExsistException
   */
  public void mkDirCurrent(ArrayList<String> input)
      throws NameAlreadyExsistException {
    // store current directory
    Directory initDirectory = Directory.getCurrDir();

    Cd cd = new Cd();
    Mkdir mkdir = new Mkdir();
    // get the last directory we want to change to, and remove from array.
    String lastElement = input.remove(input.size() - 1);

    // create directory path, one directory at a time, excluding the last.
    for (String dir : input) {
      mkdir.mkDirHelper(dir);
      cd.runCommand(dir);
    }

    // test to see if the final directory already exists.
    // if it does, throw error.
    boolean alreadyExists = false;
    ArrayList<Directory> childDirs = Directory.getCurrDir().getChildDir();
    for (Directory childDir : childDirs) {
      if (childDir.getDirName().equals(lastElement)) {
        alreadyExists = true;
      }
    }
    if (!alreadyExists) {
      mkdir.mkDirHelper(lastElement);
      cd.runCommand(lastElement);
    } else {
      Directory.updateCurrDir(initDirectory);
      throw new NameAlreadyExsistException(lastElement);
    }
  }

  /**
   * Creates a single Directory relative to current path.
   * 
   * @param input is the name of the Directory to be created.
   * @throws NameAlreadyExsistException
   */
  private void mkDirHelper(String input) {
    // Loop through the child directories of the current directory
    // to see if the child directory already exists.
    ArrayList<Directory> childDirectories = new ArrayList<Directory>();
    childDirectories = Directory.getCurrDir().getChildDir();
    boolean isChild = false;
    for (Directory child : childDirectories) {
      // If the directory to be created already exists, then do nothing.
      if (child.getDirName().equals(input)) {
        isChild = true;
      }
    }
    // if the directory doesn't yet exist, create it.
    if (!isChild) {
      Directory newDir = new Directory(input);
      // set the relationship between the directories
      Directory.getCurrDir().addDirectory(newDir);
      newDir.setParent(Directory.getCurrDir());
    }
  }
}
