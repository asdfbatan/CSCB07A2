package driver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * A class implement functions for redirection
 *
 */
public class Redirection extends Commands {

  /**
   * constructor for redirection
   */
  public Redirection() {

  }

  /**
   * This method can either > Outfile or >> Outfile. this can applied to every
   * command except for exit. This command will capture the output of the
   * commands and redirects it to the outfile. When the command does not give
   * any output then you must not create an Outfile.
   * 
   * @param command is the command that we want the output from
   * @param param is the rest of the input from the user which includes the
   *        information that we need.
   */
  public void runCommand(String outputFromCommand, List<String> param) {

    // save the current directory
    Directory currDirectory = Directory.getCurrDir();

    // save the file name
    String fileName = param.get(1);
    System.out.println("fileName = " + fileName);
    // find the if the command wants overwrite or append.
    String appendOrOverwrite = param.get(0);

    // if there is no file in the current Directory then create a new file
    if (currDirectory.getFiles().size() == 0) {
      // make a new file and add the output of the command into the file
      File newFile = new File(fileName);
      newFile.addContent(outputFromCommand);
      // add the file into the current directory
      currDirectory.addFile(newFile);
    } else {
      Boolean fileFound = false;
      // loop through all the files in the current directory
      for (File file : currDirectory.getFiles()) {
        // check if the file exist in the current directory
        if (file.getFileName().equals(fileName)) {
          fileFound = true;
          // call helper method that add the output from the command into the
          // file.
          redirectionHelper(file, outputFromCommand, appendOrOverwrite);
        }
      }
      // if file is not in the current directory
      if (fileFound == false) {
        File newFile = new File(fileName);
        // call helper method that add the output from the command into the
        // file.
        redirectionHelper(newFile, outputFromCommand, appendOrOverwrite);
        // add the file into the current directory
        currDirectory.addFile(newFile);
      }
    }

  }

  /**
   * This is a helper method for redirection, this method will either append or
   * overwrite the command output into a file.
   * 
   * @param file is the file that is going to store the command output
   * @param commandOutput is a string with the output from the command
   * @param appendOrOverwrite is a string with either > or >>
   */
  public void redirectionHelper(File file, String commandOutput,
      String appendOrOverwrite) {
    // if > （Overwrite)
    if (appendOrOverwrite.equals(">")) {
      // erase the contents in the file then add the command output into the
      // file
      file.eraseContent();
      file.addContent(commandOutput);
    }
    // if >> (Append)
    else if (appendOrOverwrite.equals(">>")) {
      // add the command output into the file
      file.addContent(commandOutput);
    }
  }

  // TEST
  public static void main(String[] args) {
    // TEST
    // CAPITAL indicates directory, LOWER CASE indicates file.
    // The hierarchy is as:
    // A
    // B e C
    // d D
    Directory.updateCurrDir(Directory.getRoot());

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
    // Directory.getRoot().addFile(e);
    C.addFile(d);

    Ls ls = new Ls();
    Pwd pwd = new Pwd();
    Redirection redirect = new Redirection();
    List<String> list = new ArrayList<String>();
    list.add(">>");
    list.add("e");

    // redirect.runCommand(pwd, list);
    System.out.println(ls.runCommand());

    ArrayList<File> files = Directory.getCurrDir().getFiles();
    for (File f : files) {
      System.out.println(f.getContent());
    }
  }

}
