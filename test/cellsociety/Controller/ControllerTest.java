package cellsociety.Controller;

import cellsociety.Main;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest extends DukeApplicationTest {


  private Controller controller;
  private final Main main = new Main();


  @Override
  public void start(Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }


 @Test
  public void uploadPropertiesFileTestInexistentPath(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    Assertions.assertThrows(IllegalStateException.class,
        () -> controller.uploadPropertiesFile("null"));

  }
  @Test
  public void uploadPropertiesFileTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    Assertions.assertThrows(IllegalStateException.class,
        () -> controller.uploadPropertiesFile("/Users/luisasilva/WORKSPACE_FOLDER_307/simulation_team19/src/resources/RockPaperScissors4.sim"));

  }


  @Test
  public void getSetupSuccessfulTrueTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    assertTrue(controller.getSetupSuccessful());
  }
  @Test
  public void getSetupSuccessfulFalseTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    assertEquals(false, controller.getSetupSuccessful());
  }


  @Test
  public void getNeighborTypeTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    assertEquals("Square", controller.getNeighborType());
  }


  @Test
  public void getThresholdTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("Blinker_middle.csv/","src/resources/TestingPropertiesFiles/SpreadingFireTest1.sim"));
    assertEquals(".5", controller.getThreshold());
  }


}
