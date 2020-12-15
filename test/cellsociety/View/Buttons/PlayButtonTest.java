package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.BoardStructure;
import cellsociety.View.GameView;
import java.awt.Dimension;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class PlayButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main;
  private Button playButton;

  @Override
  public void start (Stage stage) {
    main = new Main();
    main.start(stage);
    controller = main.getGameController();
  }

  @Test
  void checkInitiallyDisabled() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    playButton = lookup("#PlayButton").query();
    assertTrue(playButton.isDisabled());
  }

  @Test
  void checkPlaysGame() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/spreadingfirespreadtest.csv/",
        "src/resources/SpreadingFire6.sim"));
    playButton = lookup("#PlayButton").query();
    playButton.setDisable(false);
    int prevCell = controller.getModelBoard().getCurrBoard().getCurrCell(0, 0).getState();
    clickOn(playButton);
    javafxRun(()->main.stepIgnoringIfPaused());
    assertFalse(checkFirePaused(prevCell));
  }

  /*
   * Know that the cell at [0][0] changes because controlled testing board
   */
  private boolean checkFirePaused(int prevCell) {
    BoardStructure currentBoard = controller.getModelBoard().getCurrBoard();
    return (currentBoard.getCurrCell(0,0).getState() == prevCell);
  }

}