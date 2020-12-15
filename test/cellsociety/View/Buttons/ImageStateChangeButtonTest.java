package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.View.GameView;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ImageStateChangeButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main = new Main();
  private Button imageStateChangeButton;

  @Override
  public void start (Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();

  }

  @Test
  void checkInitiallyDisabled() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    imageStateChangeButton = lookup("#ImageStateChangeButton").query();
    assertTrue(imageStateChangeButton.isDisabled());
  }

  @Test
  void checkUploadImage() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    imageStateChangeButton = lookup("#ImageStateChangeButton").query();
    imageStateChangeButton.setDisable(false);
    clickOn(imageStateChangeButton);
  }
}