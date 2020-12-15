package cellsociety.Interfaces;

import java.util.Properties;

/**
 * Interface that implements methods related to the number of rows, columns, properties
 * and number of states of the new .sim property file
 */
public interface AccessorsForCreatingAScene {

  int getRows();
  int getCols();
  Properties getProperties();
  int getNumberOfStates();

}
