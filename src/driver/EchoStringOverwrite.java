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

import exceptions.*;
import java.util.ArrayList;

/**
 * echo STRING [> OUTFILE] If OUTFILE is not provided, print STRING on the shell
 * Otherwise, put STRING into file OUTFILE. STRING is a string of characters
 * surrounded by double quotation marks. This creates new file if OUTFILE does
 * not exists and erases the old contents if OUTFILE already exists. In either
 * case, the only thing in OUTFILE should be STRING.
 */
public class EchoStringOverwrite extends Commands {

  /**
   * constructor for EchoStringOverwrite
   */
  public EchoStringOverwrite() {}

  /**
   * This method executes the echo command, assuming the given text and path is
   * valid. Returns a (empty) String to stay consistent with the structure of
   * parent Command class.
   * 
   * @param inputText the text to be added to the file
   * @param inputPath the path to the file
   */
  public String runCommand(String inputText, String inputPath)
      throws IncorrectPathException {
    // if given is a path, then save the current directory to come back to
    // later, and then change directories to the path.
    Directory initDir = Directory.getCurrDir();
    // Assume inputPath is just a file name.
    String fileName = inputPath;
    // if it is not just a file name, assign the actual file name to fileName
    if (inputPath.contains("/")) {
      Cd cd = new Cd();
      // split the inputPath into the path leading to the filename, and the
      // filename itself.
      int indexOfEndPath = inputPath.lastIndexOf("/");
      String path = inputPath.substring(0, indexOfEndPath + 1);
      fileName = inputPath.substring(indexOfEndPath + 1);
      // change to the path leading up to the file.
      cd.runCommand(path);
    }
    else {
    	Directory.updateCurrDir(Directory.getRoot());
    }
    // now, we need to create the file if it doesn't exist, or overwrite the
    // file if it exists.
    boolean found = false;
    for (File f : Directory.getCurrDir().getFiles()) {
      // if OUTFILE exist,
      // erases the old contents and write given string into file
      if (f.getFileName().equals(fileName)) {
        found = true;
        f.eraseContent();
        f.addContent(inputText);
      }
    }
    if (!found) {
      File newFile = new File(fileName);
      newFile.addContent(inputText);
      newFile.setDirectory(Directory.getCurrDir());
      Directory.getCurrDir().addFile(newFile);
    }
    // change back to initial directory
    Directory.updateCurrDir(initDir);
    return "";
  }

  public static void main(String[] args) {
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    System.out.println("------------follow should printout file content");
    File file1 = new File("file1");
    file1.addContent("I'm file1 content\n");
    file1.addContent("I'm file1 content and nice to meet you");
    System.out.println(file1.getContent());
    File file2 = new File("file2");
    file2.addContent("I'm file2 content");
    System.out.println(file2.getContent());

    File newFile = new File("newFILE");
    newFile.addContent("initial content");
    B.addFile(newFile);


    Cd cd = new Cd();
    cd.runCommand("/A/C");
    EchoStringOverwrite echoStringOverwrite = new EchoStringOverwrite();
    try {
      echoStringOverwrite.runCommand("hello", "/A/B/newFILE");
    } catch (Exception e) {
      System.out.println("error");
    }
    cd.runCommand("/A/B");
    Ls ls = new Ls();
    System.out.println(ls.runCommand());
    System.out.println(Directory.getCurrDir().getDirName());
    ArrayList<File> fileList = Directory.getCurrDir().getFiles();
    for (File f : fileList) {
      if (f.getFileName().equals("newFILE")) {
        System.out.println(f.getContent());
      }
    }

  }
}
