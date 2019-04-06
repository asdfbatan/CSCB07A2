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

import java.io.Serializable;

/**
 * This class represents the File objects inside the file system.
 *
 */
public class File implements Serializable {
  /**
   * initial serialVersionUID
   */
  private static final long serialVersionUID = -765465465465456L;
  /**
   * initial fileName
   */
  private String fileName;
  /**
   * initial fileContent
   */
  private String fileContent;
  /**
   * initial fileDirectory
   */
  private Directory fileDirectory;

  /**
   * Constructor Declaration of Class.
   * 
   * @param fileName name of the file.
   * @param fileDirectory directory of the file.
   */
  public File(String fileName) {
    this.fileContent = "";
    this.fileName = fileName;
  }

  /**
   * This method returns the name of the file.
   * 
   * @return fileName name of the file
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * This method adds the contents into this file.
   * 
   * @param contents this is the contents of the file.
   */
  public void addContent(String contents) {
    this.fileContent = fileContent + contents;
  }

  /**
   * This method adds the contents into this file.
   * 
   * @param contents this is the contents of the file.
   */
  public void replaceContent(String contents) {
    this.fileContent = contents;
  }
  /**
   * This method return the contents of the current file
   * 
   * @return content of the file
   */
  public String getContent() {
    return this.fileContent;
  }

  /**
   * This method mutate the contents of the current file
   * 
   * @return null
   */
  public void reducechar(int charnum) {
    this.fileContent =
        this.fileContent.substring(0, this.fileContent.length() - charnum);
  }

  /**
   * a method to erase content
   */
  public void eraseContent() {
    fileContent = "";
  }

  /**
   * This method sets the directory of this file
   * 
   * @param directory is the directory which this file is in.
   */
  public void setDirectory(Directory directory) {
    this.fileDirectory = directory;
  }


  /**
   * this method creates a duplicate file object, separate from eachother.
   * 
   * @param file
   * @return
   */
  public static File duplicate(File file) {
    File newFile = new File(file.getFileName());
    newFile.addContent(file.getContent());
    return newFile;
  }

  /**
   * This method sets how we define equivalent Files.
   */
  public boolean equals(File file) {
    if (this.fileName.equals(file.fileName)) {
      return true;
    }
    return false;
  }
}


