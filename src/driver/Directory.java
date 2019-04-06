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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;


/**
 * A Directory that takes on the form of a Tree. Every Directory Object will
 * have: - a Directory parent, denoting the previous directory. - an ArrayList
 * of Directory Objects, denoting the Directories within the directory. - an
 * ArrayList of File Objects, denoting the Files inside the directory.
 *
 * The Directory class will also have a pointer to the current directory the
 * user is in (a single Directory Object). IMPORTANT: the pointer to current
 * directory will be a null pointer. Root should be signified by a Directory
 * object with the empty string as name.
 */
public class Directory implements Serializable {
  /**
   * initial serialVersionUID
   */
  private static final long serialVersionUID = -7636145456422L;
  /**
   * initial parent directory
   */
  private Directory parent;
  /**
   * initial name
   */
  private String name;
  /**
   * initial the directory array
   */
  private ArrayList<Directory> dirArray;
  /**
   * initial the file array
   */
  private ArrayList<File> fileArray;
  /**
   * initial the current directory
   */
  private static Directory currentDir;
  /**
   * initial the current root of directory
   */
  private static Directory root;

  /**
   * Constructor
   * 
   * @param name
   */
  public Directory(String name) {
    this.name = name;
    this.parent = null;
    this.dirArray = new ArrayList<Directory>();
    this.fileArray = new ArrayList<File>();
  }

  /**
   * Getter methods for pervious directory
   * 
   * @return
   */
  public Directory getPrevDir() {
    return parent;
  }

  /**
   * Getter methods for directory name
   * 
   * @return
   */
  public String getDirName() {
    return name;
  }

  /**
   * Getter methods for child directory
   * 
   * @return
   */
  public ArrayList<Directory> getChildDir() {
    return dirArray;
  }

  /**
   * Getter methods for files
   * 
   * @return
   */
  public ArrayList<File> getFiles() {
    return fileArray;
  }
  /**
   * Print method for file arraylist
   * 
   * @return
   */
  public String printFileArray() {
	// initialize the filearray string
	String fileArrayString = "files[";
	// loop all the files on file array
	for(File child:Directory.currentDir.fileArray) {
		fileArrayString += child.getFileName();
		fileArrayString += ";";
	}
	fileArrayString += "]";
    return fileArrayString;
  }
  /**
   * This method returns an ArrayList of Directories that represents the full
   * path to the directory. The first index will be the directory closest to the
   * root.
   * 
   * @return
   */
  public ArrayList<Directory> getFullPath() {
    // initialize the ArrayList containing full path to current directory
    ArrayList<Directory> fullPath = new ArrayList<Directory>();
    Directory directory = this;
    while (directory != null) {
      fullPath.add(directory);
      directory = directory.parent;
    }
    return fullPath;
  }

  /**
   * Setter methods
   * 
   * @param parent
   */
  public void setParent(Directory parent) {
    this.parent = parent;
  }

  /**
   * This method adds a Directory object into the directory list.
   */
  public void addDirectory(Directory next) {
    dirArray.add(next);
    next.parent = this;
  }

  /**
   * This method adds a File object into the file list
   */
  public void addFile(File file) {
    fileArray.add(file);
    file.setDirectory(this);
  }

  /**
   * This method removes the current directory.
   */
  public void removeDirectory() {
    if (parent == null) {
      // JShell.print("this is the home, you can not delete it");
    } else {
      this.parent.dirArray.remove(this);
      this.parent = null;
    } ;
  }

  /**
   * method to remove file from file directory
   * 
   * @param file
   */
  public void removeFile(File file) {
    fileArray.remove(file);
  }

  /**
   * This method updates the pointer pointing to the current directory the user
   * is in (a single Directory Object).
   */
  public static void updateCurrDir(Directory directory) {
    currentDir = directory;
  }

  /**
   * Getter method for current directoy
   * 
   * @return
   */
  public static Directory getCurrDir() {
    return currentDir;
  }

  /**
   * Getter method for root
   * 
   * @return
   */
  public static Directory getRoot() {
    if (root == null) {
      root = new Directory("/");
    }
    return root;

  }

  /**
   * Set the root to a directory
   * 
   * @param directory
   */
  public static void setRoot(Directory directory) {
    root = directory;
  }

  /**
   * This method returns true if the current directory contains a sub- directory
   * with identical name.
   * 
   * @param directory the directory we are checking for.
   */
  public boolean contains(Directory directory) {
    for (Directory d : this.dirArray) {
      if (d.getDirName().equals(directory.getDirName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns true if the current directory contains a file with
   * identical name.
   * 
   * @param file the file we are checking for.
   */
  public boolean contains(File file) {
    for (File f : this.fileArray) {
      if (f.getFileName().equals(file.getFileName())) {
        return true;
      }
    }
    return false;
  }


  /**
   * This method reads the contents inside a file and arrage them into a list
   * 
   * @throws IOException
   */
  public static List<String> showContent(Directory currDir, String fileName)
      throws IOException {
    // here don't know how to get a file?
    // File file = currDir.getFiles();
    Reader file = null;// this is to avoid error
    List<String> list = new ArrayList<String>();
    BufferedReader bufferedReader = new BufferedReader(file);
    String lineTxt = null;
    // read each line of the file and add them into a list
    while ((lineTxt = bufferedReader.readLine()) != null) {
      list.add(lineTxt);
    }
    bufferedReader.close();
    return list;
  }

  /**
   * This method determines whether a path (in the form of a String) leads to a
   * directory or a file.
   * 
   * @param path a path to either a directory or file in the form of a String
   * @return type d denotes directory, f denotes file
   */
  public static String fileOrDirectory(String path) {
    Directory initialDir = Directory.getCurrDir();
    Cd cd = new Cd();
    // check if it is a relative or full path.
    // if it is a full path, there will be a backslash in the beginning.
    if (path.charAt(0) == '/') {
      // then, change directory to root. Path is now relative.
      Directory.updateCurrDir(Directory.getRoot());
    }
    // trim the separating backslashes in the beginning and end of string
    path = path.replaceAll("/$", "").replaceAll("^/", "");
    // separate the path into a sub path containing the path until the last
    // element, and the last element itself.
    String lastElement = path;
    if(path.contains("/")) {
    	int lastSeparator = path.lastIndexOf("/");
    	String subPath = path.substring(0, lastSeparator);
    	lastElement = path.substring(lastSeparator + 1);
    	cd.runCommand(subPath);
    }
    
    // check if the name of the last element matches a directory.
    ArrayList<Directory> subDirectories = Directory.getCurrDir().getChildDir();
    boolean isDir = false;
    for (Directory d : subDirectories) {
      if (d.getDirName().equals(lastElement)) {
        isDir = true;
      }
    }
    // change back to initial directory before returning
    Directory.updateCurrDir(initialDir);
    if (isDir) {
      return "d";
    }
    return "f";
  }

  /**
   * this method returns a duplicate of the current directory, independent of
   * each other.
   */
  public static Directory duplicate(Directory directory) {
    // create new directory object with identical name.
    Directory newDir = new Directory(directory.getDirName());
    // access what the directory contains.
    ArrayList<Directory> dirList = directory.getChildDir();
    ArrayList<File> fileList = directory.getFiles();

    // create copies of each file and add it into duplicate directory.
    for (File f : fileList) {
      File dupeFile = File.duplicate(f);
      newDir.addFile(dupeFile);
    }

    // if directory contains subdirectories, then recursively duplicate.
    for (Directory d : dirList) {
      Directory subDir = Directory.duplicate(d);
      newDir.addDirectory(subDir);
    }

    return newDir;
  }

}
