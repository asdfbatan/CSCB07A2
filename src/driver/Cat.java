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

import exceptions.*;

/**
 * This is that Cat command that handles the case for when the user wants to
 * print out the contents of one or more files in the shell. If more than one
 * file is to be printed, then each file is separated by 3 line breaks.
 *
 */
public class Cat extends Commands {
  /**
   * The constructor for the cat object.
   */
  public Cat() {

  }

  /**
   * This method returns a String of contents of (several) files concatenated,
   * separated by three line breaks between on file and another.
   * 
   * @param params the paths to each file.
   * @return output the output of Cat.
   */
  public String runCommand(String params) throws IncorrectPathException {
    // get directory
    Directory directory = Directory.getCurrDir();
    // first split each name
    String[] fileNameList = params.trim().split(" ");
    // instantiate the string representing output
    String output = "";
    // open and concatenate each file to output.
    for (String param : fileNameList) {
      boolean found = false;
      String fileName = param.trim();

      for (File file : directory.getFiles()) {
        if (file.getFileName().equals(fileName)) {
          found = true;
          String content = file.getContent();
          if (content != null) {
            output = output + content;
            output = output + "\n" + "\n" + "\n" + "\n";
          }
        }
      }
      if (found == false) {
        throw new IncorrectPathException("param");
      }
    }
    // remove extra line breaks at the end
    return output.trim();
  }

  // Test
  public static void main(String[] args) throws Exception {
    // TEST
    // The hierarchy is as:
    // /
    // A
    // B C
    // file1 file2
    Directory.updateCurrDir(Directory.getRoot());
    Directory A = new Directory("A");
    Directory B = new Directory("B");
    Directory C = new Directory("C");
    Directory.getRoot().addDirectory(A);
    A.addDirectory(B);
    A.addDirectory(C);
    System.out.println("------------follow should printout file content");
    File file1 = new File("file1");
    file1.addContent("I'm file1 content\n");
    file1.addContent("I'm file1 content and nice to meet you");
    System.out.println(file1.getContent());
    File file2 = new File("file2");
    file2.addContent("I'm file2 content");
    System.out.println(file2.getContent());
    System.out.println("------------follow should print 2 file address");
    C.addFile(file1);
    C.addFile(file2);
    System.out.println(C.getFiles());

    System.out.println(
        "------------follow check if files were actually add into directory");
    Cat cat = new Cat();
    Cd cd = new Cd();

    cd.runCommand("/A/C");
    System.out.println(Directory.getCurrDir().getFiles());
    System.out
        .println("-----------follow check if cat works for multiple files");
    System.out.println(cat.runCommand("file1 file2"));
    System.out.println("-----------follow check if cat works for single files");
    System.out.println(cat.runCommand("    file1 "));

    // test for single file
    // String mockCommand1 = "file1";
    // cat.runCommand(mockCommand1);

    // test for duplicate files
    // String mockCommand2 = "file1 file2";
    // cat.runCommand(mockCommand2);

  }

}
