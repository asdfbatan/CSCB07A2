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

/**
 * This is the Man class that handles the case for when the user wants to see
 * information on how to use existing commands for the program.
 *
 */
public class Man extends Commands {
  /**
   * The class store the description and brief commands requirements for each
   * class and give output
   */
  private static String catCommand =
      "cat file1[file2]:show the content of the file by given names";
  /**
   * man for cd
   */
  private static String cdCommand = "cd Dir:enter the directory called Dir\n"
      + "cd path(/a/b/c): enter the directory from the root "
      + "based on the given path\n"
      + "cd path(a/b/c): enter the directory from the current directory "
      + "based on the given path\n"
      + "cd ..: enter the previous directory\ncd .: "
      + "enter the current directory";
  /**
   * man for echoStringOverwrite
   */
  private static String echoStringOverwrite =
      "echo STRING [> OUTFILE] If OUTFILE is not provided, "
          + "print STRING on the shell\n"
          + "Otherwise, put STRING into file OUTFILE. "
          + "STRING is a string of characters\n"
          + "surrounded by double quotation marks. "
          + "This creates a new file if OUTFILE does\n"
          + "not exists and erases the old contents if OUTFILE already exists. "
          + "In either\n case, the only thing in OUTFILE should be STRING.";
  /**
   * man for echoStringAppend
   */
  private static String echoStringAppend =
      "echo STRING >> OUTFILE If OUTFILE is not provided, "
          + "print STRING on the shell\n"
          + "Otherwise, put STRING into file OUTFILE. "
          + "STRING is a string of characters\n"
          + "surrounded by double quotation marks. "
          + "This creates a new file if OUTFILE does\n"
          + "not exists and appends the old contents "
          + "if OUTFILE already exists. In either\n"
          + "case, the only thing in OUTFILE should be STRING.";
  /**
   * man for exit
   */
  private static String exitCommand = "exit: Quit the program";
  /**
   * man for history
   */
  private static String historyCommand =
      "history[number]:This method will print out the most recent commands, "
          + "as specified by\n" + "the user, one command per line";
  /**
   * man for ls
   */
  private static String lsCommand = "ls [PATH ...]If no paths are given, "
      + "print the contents (file or directory) of the current directory,"
      + " with a new line following each of "
      + "the content (file or directory).";
  /**
   * man for man
   */
  private static String manCommand =
      "man[command]: show the documentation of each command";
  /**
   * mand for mkdir
   */
  private static String mkdirCommand =
      "mkdir DIR ...:Create directories, each of which may "
          + "be relative to the current directory " + "or may be a full path. ";
  /**
   * man for popd
   */
  private static String popdCommand =
      "retrieves and removes the latest stored Directory from the\n"
          + "directory stack, and changes it the current directory.";
  /**
   * man for pushd
   */
  private static String pushdCommand =
      "This method will take in a Directory Object, "
          + "push the current directory\n"
          + "onto the stack of Directories (ArrayList), "
          + "and then change directories\n" + "to the given directory.";
  /**
   * man for pwd
   */
  private static String pwdCommand =
      "pwd: Print the current working directory (including the whole path). ";

  /**
   * cp for man
   */
  private static String cpCommand = "Provide users to copy a\n" + 
  		" file/directory to another directory path. The two instances of such\n" + 
  		" file/directory are independent of each other";
  /**
   * find for man
   */
  private static String findCommand = "find directory or file with given name under given path(s)";
  /**
   * get for man
   */
  private static String getUrlCommand = "check the file from given url and create file in current directory";
  /**
   * load for man
   */
  private static String loadCommand = "read the previous status of jshell from a txt file with given path\n" + 
  		" and rebuild the previous jshell with same status";
  /**
   * save for man
   */
  private static String saveCommand = "save the current status of jshell into a txt file with given path\n" + 
  		" including name";
  /**
   * tree for Man
   */
  private static String treeCommand = "akes no input and return a string representation of full file System";
  /**
   * mv for Man
   */
  private static String mvCommand = "move a file or directory to a new file or directory.";
  /**
   * This method returns the information on how to use a specific command.
   * 
   * @param params the String representing the command to display info on.
   * @return returnString the information on how to use the command
   */
  public String runCommand(String params) {
	int orderCount = OrderTable.getOrderCount(params);
    String returnString = "";
    switch (orderCount) {
      case 1:
        returnString = exitCommand + "\n";
        break;
      case 2:
        returnString = mkdirCommand + "\n";
        break;
      case 3:
        returnString = cdCommand + "\n";
        break;
      case 4:
        returnString = lsCommand + "\n";
        break;
      case 5:
        returnString = pwdCommand + "\n";
        break;
      case 6:
        returnString = pushdCommand + "\n";
        break;
      case 7:
        returnString = popdCommand + "\n";
        break;
      case 8:
        returnString = historyCommand + "\n";
        break;
      case 9:
        returnString = catCommand + "\n";
        break;
      case 10:
        returnString = echoStringOverwrite + "\n" + echoStringAppend;
        break;
      case 11:
    	returnString = manCommand + "\n";
    	break;
      case 12:
    	returnString = findCommand + "\n";
    	break;
      case 13:
    	returnString = getUrlCommand + "\n";
    	break;
      case 14:
    	returnString = treeCommand + "\n";
    	break;
      case 15:
    	returnString = mvCommand + "\n";
    	break;
      case 16:
    	returnString = cpCommand + "\n";
    	break;
      case 17:
    	returnString = saveCommand + "\n";
    	break;
      case 18:
    	returnString = loadCommand +"\n";
    	break;
    }
    return returnString;
  }
}
