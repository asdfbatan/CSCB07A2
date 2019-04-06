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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class save the current status of jshell into a txt file with given path
 * including name
 */
public class Save extends Commands {

  /**
   * constructor for save
   */
  public Save() {}

  /**
   * This method saved the environmental objects like stack, directories files
   * and history arraylist into an arraylist and save the arraylist by stream
   * into a file with given path
   * 
   * @param path
   */
  public void runCommand(String path) {
    Serialization ser = new Serialization();
    ser.serialization(path);
  }
  public static void main(String[] args) {
	    // the set up for testing the save and load
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
	    History.addHistory("One arbitary history");
	    ArrayList<Directory> a = new ArrayList<Directory>();
	    a.add(A);
	    a.add(D);
	    Pushd.replaceStcak(a);
	    Directory.updateCurrDir(B);
	    Directory.getRoot().addFile(e);
	    C.addFile(d);
	    //// test here
	    Serialization sl = new Serialization();
	    sl.serialization("D:/Assignment2/file.txt");
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