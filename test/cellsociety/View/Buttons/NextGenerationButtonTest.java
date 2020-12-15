package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.BoardStructure;
import cellsociety.View.GameView;
import java.awt.Dimension;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class NextGenerationButtonTest extends DukeApplicationTest {

  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  private Controller controller;
  private Main main = new Main();
  private Button playButton;
  private Button nextGenerationButton;

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
    nextGenerationButton = lookup("#NextGenerationButton").query();
    assertTrue(nextGenerationButton.isDisabled());
  }

  @Test
  void checkStepsGame() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    nextGenerationButton = lookup("#NextGenerationButton").query();
    nextGenerationButton.setDisable(false);
    int prevCell = controller.getModelBoard().getCurrBoard().getCurrCell(3,5).getState();
    clickOn(nextGenerationButton);
    assertFalse(checkBlinkerPaused(prevCell));
  }

  /*
   * Know that blinker turns the [4][4] cell on and off each step so if still in same state then
   * paused
   */
  private boolean checkBlinkerPaused(int prevCell) {
    BoardStructure currentBoard = controller.getModelBoard().getCurrBoard();
    return (currentBoard.getCurrCell(3,5).getState() == prevCell);
  }


}