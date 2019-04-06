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
 * This class represents the history of all user inputs, and is responsible for
 * storing such data, as well as outputting the data.
 *
 */
public class History extends Commands {

  /**
   * An ArrayList storing Strings representing all the input from user, where
   * the latest input is at the front of the list (index 0).
   */
  public static ArrayList<String> cmd_history = new ArrayList<String>();

  /**
   * constructor
   */
  public History() {}

  /**
   * mutator
   * 
   * @param input
   */
  public static void addHistory(String input) {
    cmd_history.add(input);
  }

  /**
   * replace current history
   * 
   * @param history
   */
  public static void replaceHistory(ArrayList<String> history) {
    cmd_history = history;
  }

  /**
   * This method will print out the most recent commands, as specified by the
   * user, one command per line. There will be two columns; the first column is
   * numbered such that the highest number is the most recent command. The
   * second column will be the actual command, including syntactical errors
   * typed by user.
   * 
   * @param input the String denoting the number of outputs the user wants
   * @returnString the String representation of the output for history
   */
  public String runCommand(String input) {
    History history = new History();

    int commands_wanted = Integer.parseInt(input);
    int len_history = cmd_history.size();
    String returnString = "";

    // If the number of commands wanted is greater than or equal to the
    // number of total input history, then just return all input history
    if (commands_wanted >= len_history) {
      returnString = history.runCommand();
    }
    // If number of commands wanted is less than total input history, then
    // only display the number of commands wanted.
    else {
      for (int counter =
          (len_history - commands_wanted); counter < len_history; counter++) {
        returnString = returnString + Integer.toString(counter + 1) + ". "
            + cmd_history.get(counter) + "\n";
      }
    }
    return returnString;

  }

  /**
   * OVERLOAD to display all history
   * 
   * @return returnString the String representation of the output for history
   */
  public String runCommand() {
    String returnString = "";
    for (int counter = 0; counter < cmd_history.size(); counter++) {
      returnString = returnString + Integer.toString(counter + 1) + ". "
          + cmd_history.get(counter) + "\n";
    }
    returnString = returnString.trim();
    return returnString;
  }
}
