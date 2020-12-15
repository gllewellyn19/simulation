package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.View.GameView;
import java.awt.Dimension;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * This class cannot be fully tested because the load button feature is controlled by OS, not
 * Javafx
 */
class LoadNewStartingConfigButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main = new Main();
  private Button loadNewConfigButton;

  @Override
  public void start (Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  void checkButtonEnabledBeginning() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    loadNewConfigButton = lookup("#LoadNewPropertyFileButton").query();
    assertFalse(loadNewConfigButton.isDisabled());
  }

  @Test
  void checkCanSelectFile() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    loadNewConfigButton = lookup("#LoadNewPropertyFileButton").query();
    loadNewConfigButton.setDisable(false);
    clickOn(loadNewConfigButton);
  }

}