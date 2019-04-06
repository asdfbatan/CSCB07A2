package exceptions;
/**
 * the custom exception for wrong user input for jshell
 *
 */
public class InValidCommandException extends Exception {
  /**
   * 
   * @param errorMessage
   */
  public InValidCommandException(String errorMessage) {
    super(errorMessage);
  }
}
