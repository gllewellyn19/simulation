package cellsociety.Model.Exceptions;

/**
 * Thrown in case we have an exception in a value of a property value
 */
public abstract class PropertyValueException extends RuntimeException{

  private final String propertyInfo;

  public PropertyValueException(String message, String propertyInfo){
    super(message);
    this.propertyInfo = propertyInfo;
  }

  public PropertyValueException(String message, String propertyInfo, Throwable e){
    super(message, e);
    this.propertyInfo = propertyInfo;
  }

  public String getPropertyInfo() {
    return propertyInfo;
  }

}
