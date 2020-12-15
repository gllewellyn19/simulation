package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.View.GameView;
import java.util.concurrent.TimeUnit;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class ColorChangeButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main = new Main();
  private Button colorChangeButton;

  @Override
  public void start (Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  void checkInitiallyDisabled() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    colorChangeButton = lookup("#ColorChangeButton").query();
    assertTrue(colorChangeButton.isDisabled());
  }

  @Test
  void checkChangeColor() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    colorChangeButton = lookup("#ColorChangeButton").query();
    colorChangeButton.setDisable(false);
    clickOn(colorChangeButton);
    writeToInputDialog("0");
    writeToInputDialog("Orange");
  }

  @Test
  void checkChangeInvalidColor() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    colorChangeButton = lookup("#ColorChangeButton").query();
    colorChangeButton.setDisable(false);
    String checkEndingWith = enterInvalidColor();
    Node dialogPane = lookup(".dialog-pane").query();
    from(dialogPane).lookup((Text t) -> t.getText().endsWith(checkEndingWith));
    //assertThrows(InvalidPropertyException.class, this::enterInvalidColor);
  }

  private String enterInvalidColor() {
    String state = "0";
    String color = "kdfjghdjfhgkjf";
    String toReturn = "StateColor"+state+": "+color;
    clickOn(colorChangeButton);
    writeToInputDialog(state);
    writeToInputDialog(color);
    return toReturn;
  }

  @Test
  void checkChangeInvalidStateNumber() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    colorChangeButton = lookup("#ColorChangeButton").query();
    colorChangeButton.setDisable(false);
    enterInvalidStateNumber();
    Node dialogPane = lookup(".dialog-pane").query();
    from(dialogPane).lookup((Text t) -> t.getText().endsWith("State"));
    //assertThrows(InvalidPropertyException.class, this::enterInvalidStateNumber);
  }

  private void enterInvalidStateNumber() {
    clickOn(colorChangeButton);
    writeToInputDialog("100");
    writeToInputDialog("Red");
  }
}