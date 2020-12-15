package cellsociety.Model;

import cellsociety.Model.Exceptions.MissingPropertyException;
import java.util.List;
import java.util.Properties;

/**
 * Checks that the given properties file has all the property requirements needed and throws
 * exceptions as needed as soon as the constructor is called
 *
 * @author Grace Llewellyn
 */
public class CheckPropertiesRequirements {

  private void checkPropertyNotNull(String property, String propertyName) {
    if (property == null) {
      throw new MissingPropertyException(propertyName);
    }
  }

  /**
   * check property file in complete, with all necessary information to run the simulation
   * @param properties - properties object
   */
  public void checkAllPropertiesRequiredPresent(Properties properties) {
    List<String> propertyNames = List.of("SimulationType", "TitleOfSimulation", "Author",
        "Description", "InitialConfiguration", "NumberOfStates");
    for (String propertyName: propertyNames) {
      checkPropertyNotNull(properties.getProperty(propertyName), propertyName);
    }
  }

}
