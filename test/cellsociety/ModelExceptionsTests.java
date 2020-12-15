package cellsociety;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.DataReader;
import cellsociety.Model.Exceptions.MissingFilePathException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Model.Exceptions.StartingConfigurationException;
import cellsociety.Model.GameCells.GameCellFactory;
import javafx.stage.Stage;
//import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.rules.ExpectedException;
import util.DukeApplicationTest;

public class ModelExceptionsTests extends DukeApplicationTest {
  private Controller controller;
  DataReader dT = new DataReader();
  private Main main = new Main();

  @Override
  public void start(Stage stage) {
    javafxRun(()->main.start(new Stage()));
    controller = main.getGameController();
  }


  //Test exception for csv that does not match rows and columns specified
  @Test
  public void getUnmatchedRowTest() {
    Assertions.assertThrows(NullPointerException.class,
        () -> dT.placeBoard("data/TestingCSV/unmatchedRow_test.csv",controller.getNumberOfStates()));
  }

}
