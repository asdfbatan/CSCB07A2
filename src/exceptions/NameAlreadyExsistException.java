package exceptions;
/**
 * the custom exception class for two file or directory with same same name
 *
 */
public class NameAlreadyExsistException extends Exception {
  /**
   * 
   * @param errorMessage
   */
  public NameAlreadyExsistException(String errorMessage) {
    super(errorMessage);
  }
}
