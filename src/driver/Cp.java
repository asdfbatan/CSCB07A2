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
 * This is the Cp class that handles the case for when the user wants to copy a
 * file/directory to another directory path. The two instances of such
 * file/directory are independent of each other.
 *
 */
public class Cp extends Commands {
  /**
   * The constructor for the cp object.
   */
  public Cp() {

  }


  /**
   * The cp command. If the old path leads to a file, copy the file into new
   * path. If old path leads to a directory, recursively copy the files in old
   * path to new path.
   * 
   * @param oldPath a path of a file/directory to copy from
   * @param newPath a path leading to a directory to copy to
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
    
    // if oldPath refers to a directory, duplicate the directory and add
    // it to newPath.
    if (fileOrDir.equals("d")) {
      // access the directory specified by oldPath
      ArrayList<Directory> dirList = Directory.getCurrDir().getChildDir();
      Directory dirToDupe = null;
      for (Directory d : dirList) {
        if (d.getDirName().equals(lastItemName)) {
          dirToDupe = d;
        }
      }
      // if no directory is found, it must be an invalid path; throw
      // an exception
      if (dirToDupe == null) {
        throw new IncorrectPathException(lastItemName);
      }
      Directory dupeDir = Directory.duplicate(dirToDupe);

      // change back to initial directory (newPath may be relative)
      Directory.updateCurrDir(initialDir);
      // change to newPath
      cd.runCommand(newPath);

      // if there is already a directory with identical name, throw an
      // exception. Else, add the directory specified by oldPath.
      if (Directory.getCurrDir().contains(dupeDir)) {
        throw new NameAlreadyExsistException(lastItemName);
      }
      Directory.getCurrDir().addDirectory(dupeDir);

    }
    // if oldPath refers to a file, then duplicate the file only.
    else if (fileOrDir.equals("f")) {
      ArrayList<File> fileList = Directory.getCurrDir().getFiles();
      File fileToDupe = null;
      for (File f : fileList) {
        if (f.getFileName().equals(lastItemName)) {
          fileToDupe = f;
        }
      }
      // if file does not exist, throw exception
      if (fileToDupe == null) {
        throw new IncorrectPathException(lastItemName);
      }
      File dupeFile = File.duplicate(fileToDupe);

      // change back to initial directory (newPath may be relative).
      Directory.updateCurrDir(initialDir);
      // change to newPath.
      cd.runCommand(newPath);
      // if there is already a file with identical name, throw an
      // exception. Else, add the file specified by oldPath.
      if (Directory.getCurrDir().contains(dupeFile)) {
        throw new NameAlreadyExsistException(lastItemName);
      }
      Directory.getCurrDir().addFile(dupeFile);

    }

    // change back to initial directory before returning.
    Directory.updateCurrDir(initialDir);
    return "";
  }


  public static void main(String[] args) {
    // TEST
    // The hierarchy is as:
    // /
    // A
    // B C
    // file1 file2 D
    // file3
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory D = new Directory("D");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    File file1 = new File("file1");
    File file2 = new File("file2");
    File file3 = new File("file3");
    C.addFile(file1);
    C.addFile(file2);
    D.addFile(file3);
    C.addDirectory(D);

    Cp cp = new Cp();
    Ls ls = new Ls();
    Cd cd = new Cd();
    Pwd pwd = new Pwd();

    // TEST copy C to B.
    try {
      cp.runCommand("/A/C", "/A/B");
    } catch (Exception NameAlreadyExsistException) {
      System.out.println("OOPS");
    }

    cd.runCommand("/A/B/C");
    System.out.println(ls.runCommand());
    // see if mutating items in /A/C affect /A/B/C
    C.removeFile(file1);
    D.removeDirectory();
    // should print out the same output as before.
    System.out.println(ls.runCommand());
    System.out.println("-----------");
    // remove file3 from /A/C/D to see if it affects /A/B/C/D
    cd.runCommand("/A/B/C/D");
    D.removeFile(file3);
    ls.runCommand();

  }

}

