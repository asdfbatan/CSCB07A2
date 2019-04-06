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

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * This class read the previous status of jshell from a txt file with given path
 * and rebuild the previous jshell with same status
 */
public class Load extends Commands {

  /**
   * constructor for load
   */
  public Load() {}

  /**
   * This method This method load the file for getting the environmental objects
   * like stack, directories files and history arraylist back by distracting the
   * stream of a arraylist containing the environmental objects
   * 
   * @param path
   */
  public void runCommand(String path) {
    Serialization ser = new Serialization();
    ser.deserialization(path);
  }

  public static void main(String[] args) {
	  Serialization sl = new Serialization();
	    // sl.serialization("Assignment_Save_File");
	    sl.deserialization("D:/Assignment2/file.txt");
	    // print here
	    System.out.println("the hole directory is:");
	    Ls ls1 = new Ls();
	    ls1.triversal_helper(Directory.getRoot(), 0);
	    System.out.println("-----------------------");
	    System.out.println("the current directory is:");
	    System.out.println(Directory.getCurrDir().getDirName());
	    System.out.println("-----------------------");
	    System.out.println("the History are:");
	    for (String element : History.cmd_history) {
	      System.out.println(element);
	    }
	    System.out.println("-----------------------");
	    System.out.println("the Stack is:");
	    for (Directory loopvar : Pushd.getDirStack()) {
	      System.out.println(loopvar.getDirName());
	    }
  }
}