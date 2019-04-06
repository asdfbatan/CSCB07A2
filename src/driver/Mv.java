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
import exceptions.*;

/**
 * This is the Mv class that handles the case for when the user wants to move a
 * file or directory to a new file or directory.
 *
 */
public class Mv extends Commands {
  /**
   * The constructor for the Mv object.
   */
  public Mv() {}

  /**
   * This method takes in a String that represents the path to the file or
   * directory to be moved, and a String that represents the path that we want
   * to move the file/directory to.
   * 
   * @param oldPath the path of the file/directory we want to move
   * @param newPath the path we want to move the file/directory to
   * 
   * @throws NameAlreadyExsistException if there already exists a file/
   *         directory in the new path with identical name.
   * @throws IncorrectPathException if either path given does not exist.
   */
  public String runCommand(String oldPath, String newPath)
      throws NameAlreadyExsistException, IncorrectPathException {
    Directory initialDir = Directory.getCurrDir();
    Cd cd = new Cd();
    // Clean up the input; get rid of excess backslashes at the end.
    oldPath = oldPath.replaceAll("/$", "");
    newPath = newPath.replaceAll("/$", "");

    // Check if oldPath refers to file or directory.
    String fileOrDir = Directory.fileOrDirectory(oldPath);

    // separate the path into 2 parts; the path to the last element and the
    // last directory.
    String lastItemName = oldPath;
    if(oldPath.contains("/")) {
    	int lastSeparator = oldPath.lastIndexOf("/");
    	String pathToLastItem = oldPath.substring(0, lastSeparator);
    	lastItemName = oldPath.substring(lastSeparator + 1);
    	// change directory to the 2nd last directory specified in oldPath.
    	cd.runCommand(pathToLastItem);
    }
    // if oldPath refers to a directory, then rearrange the hierarchy of
    // the directories.
    if (fileOrDir.equals("d")) {
      // access the directory specified by oldPath
      ArrayList<Directory> dirList = Directory.getCurrDir().getChildDir();
      Directory lastDir = null;
      for (Directory d : dirList) {
        if (d.getDirName().equals(lastItemName)) {
          lastDir = d;
        }
      }
      // if no directory is found, it must be an invalid path; throw
      // an exception
      if (lastDir == null) {
        throw new IncorrectPathException(lastItemName);
      }
      // store this directory. Do not remove yet; should be removed after
      // confirming that the new directory does not contain a
      // directory with identical name.
      cd.runCommand(lastItemName);
      Directory toBeRemoved = Directory.getCurrDir();

      // change back to initial directory (newPath may be relative)
      Directory.updateCurrDir(initialDir);
      // change to newPath and add the directory specified by oldPath.
      cd.runCommand(newPath);
      // if there is already a directory with identical name, throw an
      // exception. Else, add the directory specified by oldPath.
      if (Directory.getCurrDir().contains(lastDir)) {
        throw new NameAlreadyExsistException(lastItemName);
      }
      // if we get to here, then we can remove oldPath directory.
      toBeRemoved.removeDirectory();
      Directory.getCurrDir().addDirectory(lastDir);

    }
    // if oldPath refers to a file, then move the file only.
    else if (fileOrDir.equals("f")) {
      ArrayList<File> fileList = Directory.getCurrDir().getFiles();
      File fileToMove = null;
      for (File f : fileList) {
        if (f.getFileName().equals(lastItemName)) {
          fileToMove = f;
        }
      }
      // if file does not exist, throw exception
      if (fileToMove == null) {
        throw new IncorrectPathException(lastItemName);
      }
      // store the directory so that we can remove the file after
      // confirming it is not a duplicate (or duplicate name).
      Directory oldDir = Directory.getCurrDir();

      // change back to initial directory (newPath may be relative).
      Directory.updateCurrDir(initialDir);
      // change to newPath
      cd.runCommand(newPath);
      // If the newPath contains a file with identical name, throw an
      // exception.
      if (Directory.getCurrDir().getFiles().contains(fileToMove)) {
        throw new NameAlreadyExsistException(lastItemName);
      }
      // if we get here, then we can remove oldPath file.
      oldDir.removeFile(fileToMove);
      Directory.getCurrDir().addFile(fileToMove);

    }

    // change back to initial directory before returning.
    Directory.updateCurrDir(initialDir);
    return "";
  }

  public static void main(String[] args) {
    // TEST
    // The hierarchy is initialized as:
    // /
    // A
    // B C
    // file1 file2
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    File file1 = new File("file1");
    File file2 = new File("file2");
    C.addFile(file1);
    C.addFile(file2);


    Mv mv = new Mv();
    Ls ls = new Ls();
    Cd cd = new Cd();
    Pwd pwd = new Pwd();

    try {
      mv.runCommand("A/C/", "A/B");
    } catch (Exception e) {
      System.out.println("error");
    }
    cd.runCommand("/A/B");
    System.out.println(ls.runCommand());
    cd.runCommand("/A/C");
    System.out.println(ls.runCommand());
  }
}
