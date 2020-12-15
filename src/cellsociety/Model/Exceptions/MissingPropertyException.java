package cellsociety.Model.Exceptions;

/**
 * Thrown when we have a property in a property file that is missing
 */
public class MissingPropertyException extends PropertyValueException {

  public static final String ERROR_KEY = "missingPropertyException";

  public MissingPropertyException(String propertyName){
    super(ERROR_KEY, propertyName);
  }

}
