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
 * A command tree. Takes no input and return a string representation of full
 * file System.
 * 
 * @author zhangti
 */
public class Tree extends Commands {

  /**
   * constructor for tree
   */
  public Tree() {}

  /**
   * The body of tree command, give the start return text of the root
   */
  public String runCommand() {
    // root representation
    StringBuffer text = new StringBuffer().append("/");
    Directory directory = Directory.getRoot();
    // change return type to string
    String buffer = new String(runCommandHelper(directory, text));
    System.out.println(buffer);
    return buffer;
  }

  /**
   * initial rank
   */
  private int rank = 0;
  /**
   * initial blank
   */
  private String blank;

  /**
   * A recursion method to add information of each directory in the full path
   * into the return text.
   * 
   * @param path
   * @param text
   * @return
   */
  public StringBuffer runCommandHelper(Directory path, StringBuffer text) {
    if (path.getChildDir().isEmpty()) {
    } else {
      // for directory with children directories.
      for (Directory dir : path.getChildDir()) {
        // calculate the needed tabs
        rank = dir.getFullPath().size();
        blank = forBlank(rank - 1);
        text.append(blank + dir.getDirName());
        for (File file : dir.getFiles()) {
          rank = dir.getFullPath().size() + 1;
          blank = forBlank(rank - 1);
          text.append(blank + file.getFileName());
        }
        // recursion the current directory
        this.runCommandHelper(dir, text);
      }
    }
    return text;
  }

  /**
   * A method to compile tabs and change lines for each rank
   * 
   * @param rank
   * @return
   */
  public String forBlank(int rank) {
    String blank = "";
    for (int i = 0; i < rank; i++) {
      blank = blank + "\t";
    }
    return "\n" + blank;
  }

  public static void main(String[] args) {
    // TEST
    // The output should be like:
    // /
    // A
    // file1
    // A1
    // A2
    // D
    // B
    // file2
    // C
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory A1 = new Directory("A1");
    Directory A2 = new Directory("A2");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory D = new Directory("D");


    Directory.getRoot().addDirectory(A);
    A.addDirectory(A1);
    A.addDirectory(A2);
    A2.addDirectory(D);
    A.addDirectory(B);
    A.addDirectory(C);

    File file1 = new File("file1");
    File file2 = new File("file2");
    A.addFile(file1);
    B.addFile(file2);
    Tree tree = new Tree();
    tree.runCommand();
  }
}
