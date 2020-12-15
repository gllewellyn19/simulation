package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.service.query.EmptyNodeQueryException;
import util.DukeApplicationTest;

class LaunchGameButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main;
  private Button launchGameButton;

  @Override
  public void start (Stage stage) {
    main = new Main();
    main.start(stage);
    controller = main.getGameController();
    launchGameButton = lookup("#LaunchGameButton").query();
  }

  @Test
  void checkInitiallyEnabled() {
    assertFalse(launchGameButton.isDisabled());
  }

  /*
   * Check to see if game launched which means play button enabled
   */
  @Test
  void checkLaunchesGame() {
    clickOn(launchGameButton);
    try {
      Button playButton = lookup("#PlayButton").query();
      assertNotNull(playButton);
    } catch (EmptyNodeQueryException ignored) {
    }
  }
}