package cellsociety.Model;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.Exceptions.MissingFilePathException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Model.Exceptions.StartingConfigurationException;
import javafx.stage.Stage;
//import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.rules.ExpectedException;
import util.DukeApplicationTest;

class DataReaderTest extends DukeApplicationTest {

  private final DataReader dataReader = new DataReader();

  @Test
  void placeBoardCSV() {
    int[][] states = dataReader.placeBoard("data/TestingCSV/Beacon_edge.csv", 2);
    assertEquals(1, states[2][1]);
  }

  @Test
  void placeBoardRandom() {
    int[][] states = dataReader.placeBoard("Random:10,10", 2);
    assertEquals(10, states.length);
    assertEquals(10, states[0].length);
  }

  @Test
  void placeBoardCheckered() {
    int[][] states = dataReader.placeBoard("Checkered:11,11", 2);
    assertEquals(0, states[0][0]);
    assertEquals(1, states[0][1]);
  }

  @Test
  void nonNumberRowsOrCols() {
    Assertions.assertThrows(StartingConfigurationException.class,
        () -> dataReader.placeBoard("data/TestingCSV/nonNumberRowAndCol.csv",
            2));

    StartingConfigurationException exception = assertThrows(
        StartingConfigurationException.class,
        () -> { throw new StartingConfigurationException("a message"); }
    );

    assertEquals("a message", exception.getMessage());

  }

  @Test
  void invalidStartType() {
    Assertions.assertThrows(StartingConfigurationException.class,
        () -> dataReader.placeBoard("jhguglh",
            2));
  }

  @Test
  void cannotFindCSV() {
    Assertions.assertThrows(MissingFilePathException.class,
        () -> dataReader.placeBoard("data/TestingCSV/dkjgf.csv",
            2));
  }

  @Test
  void csvTooSmallForRowsAndCols() {
    Assertions.assertThrows(StartingConfigurationException.class,
        () -> dataReader.placeBoard("data/TestingCSV/rowOutOfBounds.csv",
            2));
  }

  @Test
  void invalidState() {
    Assertions.assertThrows(StartingConfigurationException.class,
        () -> dataReader.placeBoard("data/TestingCSV/invalidState.csv",
            2));
  }

  @Test
  void missingCommaInitialConfigurationKey() {
    Assertions.assertThrows(StartingConfigurationException.class,
        () -> dataReader.placeBoard("Random:10",
            2));
  }

  @Test
  void missingColonInitialConfigurationKey() {
    Assertions.assertThrows(StartingConfigurationException.class,
        () -> dataReader.placeBoard("Checkered",
            2));
  }

}