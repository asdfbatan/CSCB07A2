package exceptions;
/**
 * the custom exception for given the incorrect
 *
 */
public class IncorrectPathException extends Exception {
  /**
   * 
   * @param errorMessage
   */
  public IncorrectPathException(String errorMessage) {
    super(errorMessage);
  }
}
