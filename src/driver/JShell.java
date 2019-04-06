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
import java.util.Scanner;

/**
 * The center of jshell
 */
public class JShell {
  /**
   * The main method of JShell.
   * 
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {

    // At the start of the program, we need to initialize:
    // the on/off switch of the program
    Exit exit = new Exit();
    // the root and set it as the current directory.
    Directory.updateCurrDir(Directory.getRoot());

    // start scanner
    Scanner reader = new Scanner(System.in);
    // the program halts if exit switch is flipped. 1 denotes running.
    while (exit.isRunning() == 1) {
      System.out.print("Enter your input. -----> ");
      // read the entered input
      String input = reader.nextLine();
      // this input will be stored in a stack for History.
      History.addHistory(input);
      handleOrder(input);
    }
    reader.close();
  }

  /**
   * This method takes in an order and seperate the order into two parts: order
   * part and params part then execute the order and pass on the params if exist
   * order: string form params: List<String>
   * 
   * @throws Exception
   */
  private static void handleOrder(String order) throws Exception {
    Controller controller = new Controller();
    order = removeConstantBlanks(order).trim();
    // for input with parameters
    if (order.trim().contains(" ")) {
      String order_ = order.trim().substring(0, order.indexOf(" "));
      String param = order.substring(order.indexOf(" ")).trim();
      // get the order count
      int orderCount = OrderTable.getOrderCount(order_);
      // order does not exist
      if (orderCount == 0) {
        System.err.println("not found the " + order);
      }
      // check for redirection
      else if (param.contains(">")) {
        Redirection redirect = new Redirection();

        // if a echo command is given (orderCount = 10)
        if (orderCount == 10) {
          // check if we want to redirect the echo command
          if (param.substring(0, param.lastIndexOf(">") - 1).contains(">")) {
            // split param into echo param and redirection param
            String echoParam = param.substring(0, param.lastIndexOf(">") - 1);
            String redirectionParam =
                param.substring(param.lastIndexOf(">") - 1);

            // run the echo command with echoParam, and since echo don't
            // output anything we don't need to call redirection
            controller.execute(orderCount, removeConstantBlanks(echoParam));

          }
          // if we don't want to redirect
          else {
            // do the normal echo
            String output =
                controller.execute(orderCount, removeConstantBlanks(param));
            System.out.println(output);
          }
        } else {
          String outputFromCommand = "";
          // split the param into 2 parts
          String commandParam = param.substring(0, param.indexOf(">"));
          String redirectionParam = param.substring(param.indexOf(">"));
          // when command have no param
          if (commandParam.equals(" ")) {
            outputFromCommand = controller.execute(orderCount);
          }
          // when command have a param
          else {
            outputFromCommand = controller.execute(orderCount,
                removeConstantBlanks(commandParam));
          }
          // change the redirection param into a list
          List<String> paramList =
              new ArrayList<String>(Arrays.asList(redirectionParam.split(" ")));
          // call the redirection class
          if (outputFromCommand.equals("")) {
            // do nothing
          } else {
            redirect.runCommand(outputFromCommand, paramList);
          }
        }
      } else {
        String outputFromCommand =
            controller.execute(orderCount, removeConstantBlanks(param));
        System.out.println(outputFromCommand);
      }
    }
    // for input with no parameters
    else {
      // get the order count
      int orderCount = OrderTable.getOrderCount(order);
      // order does not exist
      if (orderCount == 0) {
        System.err.println("not found the " + order);
      } else {
        String outputFromCommand = controller.execute(orderCount);
        System.out.println(outputFromCommand);
      }

    }

  }

  /**
   * This method removes all constant blanks in a string and replace them with a
   * single blank i.e. before:" a bb c c d" after:" a bb c c d"
   * 
   * @param a
   * @return
   */
  public static String removeConstantBlanks(String a) {
    String test = a.replaceAll("\\s{1,}", " ");
    return test;
  }
}