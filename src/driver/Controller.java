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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import exceptions.*;

/**
 * a control center, to call corresponding class.
 */
public class Controller {

  /**
   * file operation handler
   * 
   * @param start_dir
   * @param path_list
   * @return
   * @throws IncorrectPathException
   */
  public boolean right_path_helper(Directory start_dir, String[] path_list)
      throws IncorrectPathException {
    // initialize the loop variable to monitor the loop
    boolean found = true;
    boolean loop = true;
    // loop through the directory list to find the directories
    for (String item : path_list) {
      found = false;
      if (loop == true) {
        // loop through the child directories to check the next directory
        for (Directory element : start_dir.getChildDir()) {
          found = false;
          // if the name is found, run another loop
          if (element.getDirName().contentEquals(item)) {
            found = true;
            start_dir = element;
            break;
          }
        }
        loop = found;
        if (loop == false) {// if path can not found, throw exception
          throw new IncorrectPathException(item);
        }
      }
    }
    return found;
  }

  /**
   * This method check if path given is correct
   * 
   * @param path
   * @return
   * @throws IncorrectPathException
   */
  public boolean rightpath(String path) throws IncorrectPathException {
    // initialize the loop variable
    boolean isvalid = true;
    String[] directory_list = (path.split("/"));
    // if the path starts without "/", check it from the current directory
    if (path.charAt(0) != '/') {
      Directory temp_dir = Directory.getCurrDir();
      isvalid = right_path_helper(temp_dir, directory_list);
    } else {
      // else check from the root if the the path starts with "/"
      Directory temp_dir = Directory.getRoot();
      String[] listwithouthead =
          Arrays.copyOfRange(directory_list, 1, directory_list.length);
      isvalid = right_path_helper(temp_dir, listwithouthead);
    }
    if (isvalid == false) {
      // throw the exception when the path could not be found
      throw new IncorrectPathException("the incorrect path");
    }
    return isvalid;
  }

  /**
   * This method executes the commands that is inputed by the user
   * 
   * @param orderCount is the count of the order
   * @return OutputFromCommand is the output from the command
   */
  public String execute(int orderCount) throws Exception {
    // this is where we store the output from the command
    String outputFromCommand = "";
    switch (orderCount) {
      case 1:// case for Exit
        Exit case1 = new Exit();
        case1.runCommand();
        break;
      case 4:// case for Ls
        Ls case4 = new Ls();
        outputFromCommand = case4.runCommand();
        break;
      case 5:// case for Pwd
        Pwd case5 = new Pwd();
        outputFromCommand = case5.runCommand();
        break;
      case 7:// case for Popd
        Popd case7 = new Popd();
        case7.runCommand();
        break;
      case 8:// case for History
        History case8 = new History();
        outputFromCommand = case8.runCommand();
        break;
      case 14:// case for tree
        Tree case14 = new Tree();
        outputFromCommand = case14.runCommand();
        break;
      default:
        System.err.println("haven't been complete");
        break;
    }
    return outputFromCommand;
  }

  /**
   * This method executes the commands that is inputed by the user with a
   * parameter params
   * 
   * @param orderCount is the count of the order
   * @param params is the parameters the user inputed
   */
  public String execute(int orderCount, String params) throws Exception {
    String outputFromCommand = ""; // this is where we store the output from the
                                   // command
    switch (orderCount) {
      case 2: // case for mkdir
        caseForMkdir(params);
        break;
      case 3: // case for cd
        caseForCd(params);
        break;
      case 4: // case for ls
        caseForLs(params, outputFromCommand);
        break;
      case 6: // case for pushd
        caseForPushd(params);
        break;
      case 8: // case for history
        caseForHistory(params, outputFromCommand);
        break;
      case 9: // case for cat
        Cat cat = new Cat();
        return cat.runCommand(params) + "\n";
      case 10: // case for echo
        caseForEcho(params);
        break;
      case 11: // case for man
        Man case11 = new Man();
        outputFromCommand = case11.runCommand(params);
        System.out.println(outputFromCommand);
        break;
      case 12: // case for find
        Find case12 = new Find();
        return case12.runCommand(params) + "\n";
      case 13: // case for GetUrl
        GetUrl case13 = new GetUrl();
        case13.runcommand(params);
        break;
      case 15: // case for mv
        caseForMv(params);
        break;
      case 16: // case for cp
        caseForCp(params);
        break;
      case 17: // case for save
        Save case17 = new Save();
        params = params.trim().replaceAll("  ( )+", "");
        case17.runCommand(params);
        break;
      case 18: // case for load
        Load case18 = new Load();
        params = params.trim().replaceAll("  ( )+", "");
        case18.runCommand(params);
        break;
    }
    return outputFromCommand;
  }

  /**
   * execute case for mkdir
   * 
   * @param params
   */
  public void caseForMkdir(String params) {
    // params for mkdir will be one or more paths specifying a directory.
    // remove excess white spaces and split the paths.
    String paths = params.replaceAll(" ( )", " ").trim();
    String[] pathList = paths.split(" ");

    Mkdir case2 = new Mkdir();
    for (int i = 0; i < pathList.length; i++) {
      try {
        case2.runCommand(pathList[i]);
      } catch (NameAlreadyExsistException e) {
        System.out.println(
            "The name of the file " + e.getMessage() + " is already exsist");
      }
    }
  }

  /**
   * execute case for cd
   * 
   * @param params
   */
  public void caseForCd(String params) {
    // params for cd will be one path specifying a directory.
    // Then, there is no need to split params.
    // Remove all excess whitespaces.
    String path = params.replaceAll(" ( )", "").trim();
    Cd case3 = new Cd();
    try {
      if (path.contentEquals("..")) {
        case3.runCommand(path);
      } else if (path.contentEquals(".")) {
        case3.runCommand(path);
      } else if (path.contentEquals("/")) {
        case3.runCommand(path);
      } else if (rightpath(path)) {
        case3.runCommand(path);
      }
    } catch (IncorrectPathException e) {
      System.out.println(
          "the path is incorrect and it is caused by " + e.getMessage());
    }
  }

  /**
   * execute case for ls
   * 
   * @param params
   * @param outputFromCommand
   */
  public void caseForLs(String params, String outputFromCommand) {
    // params for Ls would be one or more paths specifying a directory
    // or a file.
    // remove excess white spaces and split the paths.
    String path = params.replaceAll(" ( )", " ").trim();
    String[] pathList = path.split(" ");
    Ls case4 = new Ls();
    if(pathList[0].contains("-R")) {
    	if(pathList.length == 1) {
    		case4.triversal_helper(Directory.getCurrDir(), 0);
    	}else if(pathList.length ==2) {
    		case4.triversal(pathList[1]);
    	}
    }else {
    	// run Ls for each path.
    	for (String p : pathList) {
    		String output = case4.runCommand(p);
    		outputFromCommand = outputFromCommand + output;
    	}
    }
  }

  /**
   * execute case for pushd
   * 
   * @param params
   */
  public void caseForPushd(String params) {
    // params for pushd is just one path specifying a directory.
    // No need to split params
    // Remove all excess white spaces.
    String path = params.replaceAll(" ( )", "").trim();;
    Pushd case6 = new Pushd();
    case6.runCommand(params);
  }

  /**
   * execute case for history
   * 
   * @param params
   * @param outputFromCommand
   */
  public void caseForHistory(String params, String outputFromCommand) {
    // params for history is just a number as a String.
    // No need to split params
    // Remove all excess white spaces.
    params = params.trim().replaceAll(" ( )+", "");
    History case8 = new History();
    outputFromCommand = case8.runCommand(params);
  }

  /**
   * execute case for echo
   * 
   * @param params
   * @return
   */
  public String caseForEcho(String params) {
    // params for Echo is text surrounded by quotations, followed by > or >>
    // to denote the type of echo, followed by (path to) file name.
    // First split up the parameter into 2 parts;
    // text, and arrow(s) followed by path to file.
    params = params.trim();
    String[] splitByQuote = params.split("\"");//len2: only text and no path.
    if (splitByQuote.length == 2) {
      // if the 0th index contains nothing/only spaces, then input is
      // valid; return the text to the shell.
      String beforeContent = splitByQuote[0];
      beforeContent = beforeContent.trim().replaceAll("  ( )+", "");
      if (beforeContent.equals("")) {
        return splitByQuote[1];
      }
    }
    else if (splitByQuote.length != 3) {// else len=3 if no invalid input
      return "Invalid input.";
    }
    // if no extraneous quotations, text should be in index 1.
    String content = splitByQuote[1];

    String beforeContent = splitByQuote[0];// remove spaces before the content.
    beforeContent = beforeContent.trim().replaceAll("  ( )+", "");
    if (!beforeContent.equals("")) {//there should be no input before content
      return "Invalid input.";
    }
    
    String afterContent = splitByQuote[2];// remove spaces after the content.
    afterContent = afterContent.trim().replaceAll("  ( )+", " ");

    boolean isAppend = false;
    if (afterContent.contains(">>")) {
      isAppend = true;
    }

    String path = afterContent.replaceAll(">", "");//remove >, left with path.
    path = path.trim();

    if (path.contains("/")) {//check if path is path to file or just file name.
      try {
    	int indexOfEndPath = path.lastIndexOf("/");
    	String onlyPath = path.substring(0, indexOfEndPath + 1);
        rightpath(onlyPath);
      } catch (IncorrectPathException e) {// check if the path given is valid
        System.out.println(
            "the path is incorrect and it is caused by " + e.getMessage());
      }
    }
    try {//path should be valid here and no more potential errors
      if (isAppend) {
        EchoStringAppend case10 = new EchoStringAppend();
        case10.runCommand(content, path);
      } else {
        EchoStringOverwrite case10 = new EchoStringOverwrite();
        case10.runCommand(content, path);
      }
    } catch (Exception e) {
    }
    return null;

  }

  /**
   * execute case for mv
   * 
   * @param params
   */
  public void caseForMv(String params) {
    Mv case15 = new Mv();
    // parameter for Mv is 2 paths; old path and new path.
    // remove excess whitespaces, trim, and split up parameters.
    params = params.replaceAll(" ( )", " ").trim();
    // index 0 holds old path, index 1 holds new path.
    String[] pathList = params.split(" ");
    try {
      case15.runCommand(pathList[0], pathList[1]);
    } catch (NameAlreadyExsistException e) {
      System.out.println(
          "The name of the file " + e.getMessage() + " already exists");
    } catch (IncorrectPathException e) {
      System.out.println(
          "the path is incorrect and it is caused by " + e.getMessage());
    }

  }

  /**
   * execute case for cp
   * 
   * @param params
   */
  public void caseForCp(String params) {
    Cp case16 = new Cp();
    // parameter for Cp is 2 paths; old path and new path.
    // remove excess whitespaces, trim, and split up parameters.
    params = params.replaceAll(" ( )", " ").trim();
    // index 0 holds old path, index 1 holds new path.
    String[] pathList = params.split(" ");
    try {
      case16.runCommand(pathList[0], pathList[1]);
    } catch (NameAlreadyExsistException e) {
      System.out.println(
          "The name of the file " + e.getMessage() + " already exists");
    } catch (IncorrectPathException e) {
      System.out.println(
          "the path is incorrect and it is caused by " + e.getMessage());
    }
  }

  public static void main(String[] args) throws Exception {
    // TEST
    // The hierarchy is as:
    // /
    // A
    // B C
    // D
    Directory.updateCurrDir(Directory.getRoot());

    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory D = new Directory("D");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    C.addDirectory(D);
    Controller a = new Controller();
    List<String> params = new ArrayList<String>();
    params.add("/A/C/b");
    // a.execute(3, params);
  }
}
