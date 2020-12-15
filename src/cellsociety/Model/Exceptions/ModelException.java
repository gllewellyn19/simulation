package cellsociety.Model.Exceptions;

/**
 * Thrown when he have errors in the Model part of our code
 */
public class ModelException extends RuntimeException {

  public ModelException(String message){
    super(message);
  }
  public ModelException(String message, Throwable cause){
    super(message, cause);
  }

}
