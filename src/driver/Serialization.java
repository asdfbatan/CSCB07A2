package driver;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.File;

/**
 * This class implement the functions for save and load The Serialzation class
 * which is highly related to save and load comment which provide the method for
 * saving the data steam of exsisting object and load the stream and get the
 * objects from it, and change it back to root, current directroy, history and
 * stack
 */
public class Serialization {
  /**
   * This method saved the environmental objects like stack, directories
   * files and history arraylist into an arraylist and save the arraylist by
   * stream into a file with given path
   * 
   * @param path
   */
  public void serialization(String path) {
    try {
      // input is like /Users/User1/Desktop/save.txt
      // (Relative Path)
      // i.e.String pathname = "/Users/User1/Desktop/save.txt";
      File filename = new File(path);
      // create the stream objects
      FileOutputStream fileOut = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      // initialized the arraylist object and set the index
      // by the environmental objects we want
      ArrayList<Object> data = new ArrayList<Object>();
      data.add(0, Directory.getRoot());
      data.add(1, Directory.getCurrDir());
      data.add(2, History.cmd_history);
      data.add(3, Pushd.getDirStack());
      // write the data and close the file
      out.writeObject(data);
      out.flush();
      out.close();
      // fileOut.close();
      // catch the exception and shows the exception comments
    } catch (IOException i) {
      // i.printStackTrace();
      System.out.println("wrong path, try again");
    }
  }

  /**
   * This method This method load the file for getting the environmental objects
   * like stack, directories files and history arraylist back by distracting the
   * stream of a arraylist containing the environmental objects
   * 
   * @param path
   */
  public void deserialization(String path) {
    try {
      // create the stream object from the file and path
      FileInputStream fileIn = new FileInputStream(path);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      // initialize the arraylist with environmental objects
      ArrayList<Object> data = new ArrayList<Object>();
      // read the filestream to get the arraylist
      data = (ArrayList<Object>) in.readObject();
      // getting all the environmental objects back since
      // we already know the index for each env objects
      Directory.setRoot((Directory) data.get(0));
      Directory.updateCurrDir((Directory) data.get(1));
      History.replaceHistory((ArrayList<String>) data.get(2));
      Pushd.replaceStcak((ArrayList<Directory>) data.get(3));
      // close the file
      in.close();
      fileIn.close();
      // since the reading stream doesnt how many objects
      // the stream contains and there must be an exception
      // called eofexception and we can just ignoreit
    } catch (EOFException eof) {
      // end of file reached, do nothing
    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("directory class not found");
      c.printStackTrace();
      return;
    }
  }

  public static void main(String[] args) {
    // the set up for testing the save and load
    // Directory A = new Directory("A");
    // Directory B = new Directory("B");
    // Directory C = new Directory("C");
    // Directory D = new Directory("D");
    // Directory.getRoot().addDirectory(A);
    // A.addDirectory(B);
    // A.addDirectory(C);
    // C.addDirectory(D);
    // File d = new File("d");
    // File e = new File("e");
    // History.addHistory("One arbitary history");
    // ArrayList<Directory> a = new ArrayList<Directory>();
    // a.add(A);
    // a.add(D);
    // Pushd.replaceStcak(a);
    // Directory.updateCurrDir(B);
    // Directory.getRoot().addFile(e);
    // C.addFile(d);
    //// test here
    Serialization sl = new Serialization();
    // sl.serialization("Assignment_Save_File");
    sl.deserialization("Assignment_Save_File");
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
