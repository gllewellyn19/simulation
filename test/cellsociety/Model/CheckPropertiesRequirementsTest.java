package cellsociety.Model;

import cellsociety.Model.Exceptions.MissingPropertyException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class CheckPropertiesRequirementsTest extends DukeApplicationTest {

  private final CheckPropertiesRequirements checkPropertiesRequirements = new CheckPropertiesRequirements();

  @Test
  void checkNoInitialConfigurationProperty() {
    Properties properties = getPropertiesObject("data/missingPropertiesTesting/"
        + "InitialConfiguration.properties");
    Exception exception = Assertions.assertThrows(MissingPropertyException.class, () ->
        checkPropertiesRequirements.checkAllPropertiesRequiredPresent(properties));
  }

  @Test
  void checkNoAuthorProperty() {
    Properties properties = getPropertiesObject("data/missingPropertiesTesting/"
        + "missingAuthor.sim");
    Exception exception = Assertions.assertThrows(MissingPropertyException.class, () ->
        checkPropertiesRequirements.checkAllPropertiesRequiredPresent(properties));
  }

  @Test
  void checkNoTitleProperty() {
    Properties properties = getPropertiesObject("data/missingPropertiesTesting/"
        + "MissingTitle.properties");
    Exception exception = Assertions.assertThrows(MissingPropertyException.class, () ->
        checkPropertiesRequirements.checkAllPropertiesRequiredPresent(properties));
  }

  @Test
  void checkNoSimulationTypeProperty() {
    Properties properties = getPropertiesObject("data/missingPropertiesTesting/"
        + "missingSimulationType.sim");
    Exception exception = Assertions.assertThrows(MissingPropertyException.class, () ->
        checkPropertiesRequirements.checkAllPropertiesRequiredPresent(properties));
  }

  @Test
  void checkNoDescriptionProperty() {
    Properties properties = getPropertiesObject("data/missingPropertiesTesting/"
        + "missingDescription.sim");
    Exception exception = Assertions.assertThrows(MissingPropertyException.class, () ->
        checkPropertiesRequirements.checkAllPropertiesRequiredPresent(properties));
  }

  @Test
  void checkNoNumStatesProperty() {
    Properties properties = getPropertiesObject("data/missingPropertiesTesting/"
        + "missingNumStates.sim");
    Exception exception = Assertions.assertThrows(MissingPropertyException.class, () ->
        checkPropertiesRequirements.checkAllPropertiesRequiredPresent(properties));
  }

  private Properties getPropertiesObject(String propertiesFilePath) {
    Properties propertiesToReturn = new Properties();
    try {
      FileInputStream fis = new FileInputStream(propertiesFilePath);
      propertiesToReturn.load(fis);
      fis.close();
    } catch(IOException ignored) {
    }
    return propertiesToReturn;
  }

}