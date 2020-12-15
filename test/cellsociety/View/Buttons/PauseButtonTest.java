package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.BoardStructure;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class PauseButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main;
  private Button pauseButton;

  @Override
  public void start (Stage stage) {
    main = new Main();
    main.start(stage);
    controller = main.getGameController();
  }

  @Test
  void checkInitiallyDisabled() {

    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    pauseButton = lookup("#PauseButton").query();

    assertTrue(pauseButton.isDisabled());
  }

  @Test
  void checkPausesGame() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    pauseButton = lookup("#PauseButton").query();

    pauseButton.setDisable(false);
    BoardStructure prevBoard = controller.getModelBoard().getCurrBoard();
    clickOn(pauseButton);
    javafxRun(()->main.stepIgnoringIfPaused());
    assertTrue(checkBlinkerPaused(prevBoard));
  }

  /*
   * Know that blinker turns the [4][4] cell on and off each step so if still in same state then
   * paused
   */
  private boolean checkBlinkerPaused(BoardStructure prevBoard) {
    BoardStructure currentBoard = controller.getModelBoard().getCurrBoard();
    return (currentBoard.getCurrCell(4,4).getState() == prevBoard.getCurrCell(4,4).getState());
  }


}