package cellsociety;

import cellsociety.Controller.Controller;
import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.View.StateFillMaintainer;
import java.awt.Dimension;
import java.util.Properties;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class StateFillMaintainerTest extends DukeApplicationTest {

  private Controller controller;
  private final Main main = new Main();

  @Override
  public void start(Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }


  @Test
  public void getColorInvalidFromHexTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    Assertions.assertThrows(InvalidPropertyException.class,
        () -> stateFill.getColorFromHex("#FF0000" ));
  }


  @Test
  public void checkNewRGBInBoundsTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    assertEquals(255,stateFill.checkNewRGBInBounds(255));

  }

  @Test
  public void checkNewRGBInBoundsWrongBoundTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    assertEquals(0,stateFill.checkNewRGBInBounds(0));
  }

  @Test
  public void checkValidStateTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    Assertions.assertThrows(InvalidPropertyException.class,
        () -> stateFill.checkValidState(4));
  }

  @Test
  public void getColorTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    assertEquals("0x640000ff",stateFill.getColor(0));
  }

  @Test
  public void getColorInexistentStateTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    assertNull(stateFill.getColor(5));
  }

  @Test
  public void getImageLocationTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    assertNull(stateFill.getImageLocation(0));
  }

  @Test
  public void getInexistentImageLocationTest(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFill = new StateFillMaintainer(controller.getNumberOfStates());
    assertNull(stateFill.getImageLocation(8));
  }


}
