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
import java.util.Collections;

/**
 * This is the Ls class that handles the case for when the user wants to display
 * the contents of a directory.
 *
 */
public class Ls {

  /**
   * Constructor
   */
  public Ls() {}

  /**
   * This method takes in a String and concatenates the items in the current
   * working directory.
   * 
   * @param returnString starting representation of items in directory
   * @return returnString items in directory after concatenating.
   */
  private String lsHelper(String returnString) {
    // get the current directory that the user is in.
    Directory currDirectory = Directory.getCurrDir();

    // get the files and directories in the current directory
    ArrayList<File> files = currDirectory.getFiles();
    ArrayList<Directory> directories = currDirectory.getChildDir();
    // make a arraylist that stories both files and directories
    ArrayList<String> filesAndDirectories = new ArrayList<String>();
    // loop through files and add them into filesAndDirectories
    for (File elements : files) {
      filesAndDirectories.add(elements.getFileName());
    }
    // loop through directiories and add them into filesAndDirectories
    for (Directory elements : directories) {
      filesAndDirectories.add(elements.getDirName());
    }
    // sort filesAndDirectories
    Collections.sort(filesAndDirectories);
    // concatenate filesAndDirectories
    for (String elements : filesAndDirectories) {
      returnString = returnString + elements + "\n";
    }
    returnString = returnString.trim();
    return returnString;
  }

  /**
   * This is the basic ls method.
   * 
   * @return output the String representing items in current working directory.
   */
  public String runCommand() {
    String output = lsHelper("");
    return output;
  }


  /**
   * This method cd into the given directory and do the triversal_helper
   * 
   * @param gotoDirectory
   */
  public void triversal(String gotoDirectory) {
    // temporarily store the curr dir, after recursion change it back
    // for the reason ls should not change currect directory
    Cd cd = new Cd();
    Directory tempDir = Directory.getCurrDir();
    cd.runCommand(gotoDirectory);
    // do the -R command
    triversal_helper(Directory.getCurrDir(), 0);
    // change currect directory back
    Directory.updateCurrDir(tempDir);
  }

  /**
   * A helper method
   * 
   * @param root
   * @param depth
   */
  public void triversal_helper(Directory root, int depth) {
    // this is the recursion to list the file and directory
    String indent = " ";
    int times = 0;
    while (times < depth) {
      indent += " ";
      times += 1;
    }
    if (root.getChildDir().isEmpty() == true) {
      System.out.println(indent + "/" + root.getDirName() + ":" + root.printFileArray());
    } else {
      System.out.println(indent + "/" + root.getDirName() + ":" + root.printFileArray());
      depth += 3;
      for (Directory element : root.getChildDir()) {
        this.triversal_helper(element, depth);
      }
    }
  }

  /**
   * This is a ls method that takes in a directory.
   * 
   * @param gotoDirectory is the directory that the user want to ls.
   * @return returnString String representing items in the specified directory
   */
  public String runCommand(String gotoDirectory) {
    String returnString = "";
    String initialInput = gotoDirectory;
    // save the current directory address
    Directory currentDirectory = Directory.getCurrDir();
    // make a Cd object
    Cd cd = new Cd();

    // 2 Cases when given gotoDirectory. It can be a full directory path,
    // or it can be a full directory path to a file.
    // We can differentiate between the 2 cases by checking the last element
    // We must cd to 2nd last element to check last element.
    // Construct a path up to but excluding the last element, which may be
    // a directory or a file.
    String pathUntilLast = "";
    if ((gotoDirectory.charAt(0)) == '/') {
      pathUntilLast = "/";
      gotoDirectory = gotoDirectory.substring(1);
    }
    String[] path = gotoDirectory.split("/");

    // if the length of path > 1, then we must cd up to the 2nd last element
    if (path.length > 1) {
      for (int counter = 0; counter < (path.length - 1); counter++) {
        pathUntilLast = pathUntilLast + path[counter] + "/";
      }
      cd.runCommand(pathUntilLast);
    }
    // try to change directory into the last element.
    // if successful, then it is now the base Ls case.
    // if there is an invalid path error, then it is either an invalid
    // path, or it is a file.
    try {
      cd.runCommand(path[path.length - 1]);
      returnString = returnString + initialInput + ":\n";
      returnString = lsHelper(returnString);
    } catch (Exception e) {
      // look through to see if there exists such file.
      boolean fileExists = false;
      ArrayList<File> files = Directory.getCurrDir().getFiles();
      for (File file : files) {
        // if the file name matches, then print the file name
        if (file.getFileName().equals(path[path.length - 1])) {
          returnString = returnString + path[path.length - 1] + "\n";
          // flip the switch once file is found
          fileExists = true;
        }
      }
      // if a matching file is not found, then it must be invalid path.
      // We will throw the same error as Cd.
      if (!fileExists) {
        cd.runCommand(path[path.length - 1]);
      }

    }

    // update the directory again so that its back to the current directory
    Directory.updateCurrDir(currentDirectory);
    // remove extra newlines at the end of the string to be returned.
    returnString = returnString.trim();
    return returnString;

  }

  // TEST
  public static void main(String[] args) {
    // TEST
    // CAPITAL indicates directory, LOWER CASE indicates file.
    // The hierarchy is as:
    // A
    // B C
    // d D
    Directory.updateCurrDir(Directory.getRoot());

    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory D = new Directory("D");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    C.addDirectory(D);
    File d = new File("d");
    File e = new File("e");
    Directory.getRoot().addFile(e);
    C.addFile(d);

    System.out.println(Directory.getCurrDir().getDirName());

    System.out.println("------------");

    Ls ls = new Ls();
    System.out.print(ls.runCommand());
    System.out.println(Directory.getCurrDir().getDirName());

    System.out.println("------------");

    System.out.print(ls.runCommand("/A/C/"));

    System.out.println(Directory.getCurrDir().getDirName());

    System.out.println("------------");

    System.out.print(ls.runCommand("/A"));
    System.out.println(Directory.getCurrDir().getDirName());

    System.out.println("------------");

    Cd cd = new Cd();

    cd.runCommand("/A");
    System.out.print(ls.runCommand());
    System.out.println(Directory.getCurrDir().getDirName());
    System.out.println("------------");
    // TEST
    // CAPITAL indicates directory, LOWER CASE indicates file.
    // The hierarchy is as:
    // A
    // A[BC]
    // B[EFG]C[D]
    // F[HI]
    Directory E = new Directory("E");
    Directory F = new Directory("F");
    Directory G = new Directory("G");
    Directory H = new Directory("H");
    Directory I = new Directory("I");
    B.addDirectory(E);
    B.addDirectory(F);
    B.addDirectory(G);
    F.addDirectory(H);
    F.addDirectory(I);
    ls.triversal_helper(A, 0);
  }
}
