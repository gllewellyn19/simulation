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
 * This class cannot be fully tested because the save button feature is controlled by OS, not
 * Javafx
 */
class ExportCurrentConfigurationButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main = new Main();
  private Button exportCurrentConfigurationButton;

  @Override
  public void start (Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  void checkButtonDisabledWhenNoConfigurationFile() {
    assertNull(exportCurrentConfigurationButton);
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    exportCurrentConfigurationButton = lookup("#ExportCurrentConfigurationButton").query();
    assertTrue(exportCurrentConfigurationButton.isDisabled());
  }

  @Test
  void checkCanClickOnExportConfigButton() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    exportCurrentConfigurationButton = lookup("#ExportCurrentConfigurationButton").query();
    exportCurrentConfigurationButton.setDisable(false);
    clickOn(exportCurrentConfigurationButton);
  }
}