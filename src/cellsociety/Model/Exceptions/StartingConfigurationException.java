package cellsociety.Model.Exceptions;

/**
 * Thrown when we have an invalid starting configuration
 */
public class StartingConfigurationException extends RuntimeException {

  public StartingConfigurationException(String message){
    super(message);
  }


  public StartingConfigurationException(String message, Throwable cause){
    super(message, cause);
  }


}
