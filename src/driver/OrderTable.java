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

import java.util.HashMap;
import java.util.Map;

/**
 * an order table
 */
public class OrderTable {
  /**
   * initial the order map
   */
  private static Map<String, Integer> orderMap;
  /**
   * initial each order in the map
   */
  static {
    orderMap = new HashMap<String, Integer>();
    orderMap.put("exit", 1);
    orderMap.put("mkdir", 2);
    orderMap.put("cd", 3);
    orderMap.put("ls", 4);
    orderMap.put("pwd", 5);
    orderMap.put("pushd", 6);
    orderMap.put("popd", 7);
    orderMap.put("history", 8);
    orderMap.put("cat", 9);
    orderMap.put("echo", 10);
    orderMap.put("man", 11);
    orderMap.put("find", 12);
    orderMap.put("get", 13);
    orderMap.put("tree", 14);
    orderMap.put("mv", 15);
    orderMap.put("cp", 16);
    orderMap.put("save", 17);
    orderMap.put("load", 18);
  }

  /**
   * get the order count
   * 
   * @param order
   * @return if such order exits them return its order count. if not return 0
   */
  public static int getOrderCount(String order) {
    if (orderMap.get(order) == null) {
      return 0;
    }
    return orderMap.get(order);
  }
}
