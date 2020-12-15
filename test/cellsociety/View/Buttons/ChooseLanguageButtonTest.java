package cellsociety.View.Buttons;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.View.GameView;
import java.awt.Choice;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.service.query.EmptyNodeQueryException;
import util.DukeApplicationTest;

class ChooseLanguageButtonTest extends DukeApplicationTest {

  private Controller controller;
  private Main main;
  private Button launchGameButton;
  private ChoiceBox<String> languageChangeButton;

  @Override
  public void start (Stage stage) {
    main = new Main();
    main.start(stage);
    controller = main.getGameController();
    launchGameButton = lookup("#LaunchGameButton").query();
    languageChangeButton = lookup("#ChooseLanguageButton").query();
  }

  @Test
  void checkInitiallyEnabled() {
    assertFalse(languageChangeButton.isDisabled());
  }

  @Test
  void checkEnglishLanguage() {
    checkRightLanguage("English");
  }

  @Test
  void checkSpanishLanguage() {
    checkRightLanguage("Spanish");
  }

  @Test
  void checkPortugueseLanguage() {
    checkRightLanguage("Portuguese");
  }

  /*
   * Checks to see if the play button has the correct language
   */
  private void checkRightLanguage(String language) {
    select(languageChangeButton, language);
    clickOn(launchGameButton);
    String buttonNameTesting = "PlayButton";
    Button playButton = lookup("#" + buttonNameTesting).query();
    ResourceBundle resources = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE +
            GameView.LANGUAGE_FOLDER + language);
    assertEquals(resources.getString(buttonNameTesting), playButton.getText());
  }
}