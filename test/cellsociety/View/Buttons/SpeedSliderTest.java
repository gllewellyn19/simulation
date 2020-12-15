package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SpeedSliderTest extends DukeApplicationTest {

  private Controller controller;
  private Main main = new Main();
  private Slider slider;
  @Override
  public void start (Stage stage) {
    main.start(stage);
    controller = main.getGameController();
  }

  @Test
  void checkInitiallyDisabled() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    slider = lookup("#SpeedSlider").query();
    assertTrue(slider.isDisabled());
  }

  @Test
  void checkSliderStartValue() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    slider = lookup("#SpeedSlider").query();
    assertEquals(0,slider.getValue());
  }

  @Test
  void checkSliderChangesValue() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    slider = lookup("#SpeedSlider").query();
    setValue(slider,-1);
    assertEquals(-1,slider.getValue());
  }

  @Test
  void checkSliderSlowsTime() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    slider = lookup("#SpeedSlider").query();
    setValue(slider,-1);
    double nextRate = main.getAnimationRate();
    assertEquals(nextRate,Math.pow(1.3,-1));
  }

  @Test
  void checkSliderSpeedsUpTime() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting(
        "data/TestingCSV/Blinker_middle.csv/",
        "src/resources/TestingPropertiesFiles/TestingGameOfLife2.sim"));
    slider = lookup("#SpeedSlider").query();
    setValue(slider,6);
    double nextRate = main.getAnimationRate();
    assertEquals(nextRate,Math.pow(1.3,6));
  }

}