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
 * find directory or file with given name under given path(s)
 */
public class Find extends Commands {

  /**
   * contstructor for find
   */
  public Find() {}

  /**
   * This method is the body part for find class mainly for proplerly handle
   * given order and pass them on to helper
   * 
   * @param input
   * @return
   */
  public String runCommand(String input) {
    // if name or type is missing, return error
    String[] listOfParams = input.trim().split(" ");
    if (listOfParams.length < 5) {
      StringBuffer returnMsg1 = new StringBuffer("Input missing argurments.\n");
      String buffer1 = new String(returnMsg1);
      return buffer1;
    } else {
      List<String> paths = new ArrayList<>();
      String type = "";
      String name = "";
      for (int i = 0; i < listOfParams.length; i++) {
        if (listOfParams[i].trim().startsWith("/")) {
          paths.add(listOfParams[i]);
        } else if (listOfParams[i].trim().equals("-type")) {
          type += listOfParams[i + 1].trim();
        } else if (listOfParams[i].trim().equals("-name")) {
          String name1 = listOfParams[i + 1].trim();
          name += name1.substring(1, name1.length() - 1);
        }
      }
      String buffer2 = new String(runCommandHelper(paths, type, name));
      return buffer2;
    }
  }

  /**
   * This method accept properly handled variables and run them
   * 
   * @param path
   * @param type
   * @param name
   * @return
   */
  public StringBuffer runCommandHelper(List<String> path, String type,
      String name) {
    // collect a result
    StringBuffer returnMsg = new StringBuffer("");
    Directory directory = Directory.getCurrDir();
    // loop through each given path
    for (String singlePath : path) {
      Controller controller = new Controller();
      try {
        controller.execute(3, singlePath);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        returnMsg.append("no such path exist");
        // e.printStackTrace();
      }
      Directory.updateCurrDir(directory);

      // for type d
      if (type.equals("d")) {
        boolean find = false;
        // loop through each directory in given directory
        Directory dir = directory.getPrevDir();
        find = directoryCheck(find, dir, returnMsg, name, singlePath);
        if (find == false) {
          returnMsg.append(
              "No directory " + name + " in given path " + singlePath + "\n");
        }
      }
      // for type f
      else if (type.equals("f")) {
        boolean find = false;
        // loop through each file in given directory
        find = fileCheck(find, directory, returnMsg, name, singlePath);
        if (find == false) {
          returnMsg.append(
              "No file " + name + " in given path " + singlePath + "\n");
        }
      } else {
        returnMsg.append("Wrong input type, try again");
      }
    }
    return returnMsg;
  }

  /**
   * A helper method to find files in each sub directories of given (including
   * given)
   * 
   * @param find
   * @param directory
   * @param returnMsg
   * @param name
   * @param singlePath
   * @return
   */
  public boolean fileCheck(boolean find, Directory directory,
      StringBuffer returnMsg, String name, String singlePath) {
    for (File i : directory.getFiles()) {
      if (name.trim().equals(i.getFileName())) {
        find = true;
        returnMsg
            .append("Find file " + name + " in given path" + singlePath + "\n");
      }
    }
    if (directory.getChildDir().isEmpty()) {
    } else {
      for (Directory j : directory.getChildDir()) {
        find = fileCheck(find, j, returnMsg, name, singlePath);
      }
    }
    return find;
  }

  /**
   * This method check if the directory is in the current directory
   * 
   * @param find
   * @param directory
   * @param returnMsg
   * @param name
   * @param singlePath
   * @return
   */
  public boolean directoryCheck(boolean find, Directory directory,
      StringBuffer returnMsg, String name, String singlePath) {
    for (Directory i : directory.getChildDir()) {
      if (name.trim().equals(i.getDirName())) {
        find = true;
        returnMsg.append(
            "Find directory " + name + " in given path" + singlePath + "\n");
      }
    }
    if (directory.getChildDir().isEmpty()) {
      return find;
    } else {
      for (Directory j : directory.getChildDir()) {
        find = directoryCheck(find, j, returnMsg, name, singlePath);
      }
    }
    return find;
  }


  public static void main(String[] args) {
    // TEST
    // The output should be like:
    // /
    // A
    // B
    // C
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    B.addDirectory(C);
    System.out.println("------------follow should printout file content");
    File file1 = new File("file1");
    file1.addContent("I'm file1 content and nice to meet you");
    System.out.println(file1.getContent());
    File file2 = new File("file2");
    file2.addContent("I'm file2 content");
    System.out.println(file2.getContent());
    B.addFile(file1);
    B.addFile(file2);
    Controller ctl = new Controller();
    try {
      ctl.execute(3, "/A/B");
      ctl.execute(4, "/A/B");
    } catch (Exception e) {
      System.out.println("not found");
    }
    System.out.println("------------follow should printout");
    B.getFiles().get(0).getFileName();
    B.getFiles().get(1).getFileName();
    Tree tree = new Tree();
    tree.runCommand();
    Find find = new Find();
    System.out.println("------------follow should return missing argurments");
    find.runCommand("B");
    System.out
        .println("------------follow should return not found(print nothing");
    find.runCommand("B -type f -name \"file5\"");
    System.out.println("------------follow should return found(print nothing)");
    find.runCommand("/A/B -type f -name \"file1\"");
    find.runCommand("/A -type d -name \"B\"");
    find.runCommand("/A -type f -name \"file1\"");
    find.runCommand("/A -type d -name \"C\"");

  }
}
