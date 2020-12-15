package cellsociety.Model.Exceptions;

/**
 * Thrown when we have an invalid property in a property file
 */
public class InvalidPropertyException extends PropertyValueException {

  public static final String ERROR_KEY = "invalidPropertyException";


  public InvalidPropertyException(String propertyName){
    super(ERROR_KEY, propertyName);
  }

  public InvalidPropertyException(String propertyName, Throwable e){
    super(ERROR_KEY, propertyName, e);
  }

}
