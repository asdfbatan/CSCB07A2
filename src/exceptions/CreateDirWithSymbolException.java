package exceptions;
/**
 * the custom exception for creating diretory with symbol
 *
 */
public class CreateDirWithSymbolException extends Exception {

  /**
   * 
   * @param errorMessage
   */
  public CreateDirWithSymbolException(String errorMessage) {
    super(errorMessage);
  }
}
