package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LoadNewStyleButtonTest extends DukeApplicationTest {
  private Controller controller;
  private Main main = new Main();
  private Button loadNewStyleButton;

  @Override
  public void start (Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  void checkInitiallyDisabled() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    loadNewStyleButton = lookup("#LoadStyleButton").query();
    assertTrue(loadNewStyleButton.isDisabled());
  }

  @Test
  void checkCanSelectFile() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    loadNewStyleButton = lookup("#LoadStyleButton").query();
    loadNewStyleButton.setDisable(false);
    clickOn(loadNewStyleButton);
  }




}
