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
 * The exit command that handles the case for when the user wants to terminate
 * the current session of the program.
 *
 */
public class Exit {
  /**
   * intial state
   */
  public static int state;

  /**
   * constructor of exit
   */
  public Exit() {}

  /**
   * This method checks if the program is still running.
   * 
   * @return state 1 denotes the program is running.
   */
  public int isRunning() {
    if (state == 0) {
      state = 1;
    }
    return state;
  }

  /**
   * this command terminates the program.
   */
  public void runCommand() {
    state = 2;
    System.out.println("Program terminated.");
  }
}
