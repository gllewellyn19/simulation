package cellsociety.View.Buttons;

import cellsociety.Controller.Controller;
import cellsociety.Interfaces.CreateCSSControls;
import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.View.GameView;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

public class ChangeStylesheetButton extends BoardChoiceBox {

  public static final String BUTTON_OPTIONS_IDENTIFIER = "NameStyleSheet";

  private final CreateCSSControls createCSSControls;
  private final ErrorPrintingControls errorPrintingControls;
  private final GamePlayAndSceneControls gamePlayAndSceneControls;
  private Map<String, String> englishCSSMappings;

  public ChangeStylesheetButton(ResourceBundle resources, CreateCSSControls createCSSControls,
      ErrorPrintingControls errorPrintingControls, GamePlayAndSceneControls gamePlayAndSceneControls) {
    super(resources, new ChoiceBox<>());
    this.createCSSControls = createCSSControls;
    this.errorPrintingControls = errorPrintingControls;
    this.gamePlayAndSceneControls = gamePlayAndSceneControls;
  }


  /*
   * Initializing the text and id of the button from the resources bundle and sets the action of the
   * button to be uploading a new CSS file to gameView
   */
  @Override
  public void initializeThisButton() {
    ChoiceBox<String> thisButton = super.getCurrButton();
    super.setID("UploadNewStyleButton");
    addOptionsToButton(thisButton);
    thisButton.valueProperty().addListener(e -> readInCSSFile(thisButton.getValue()));
  }

  /*
   * Adds the different style options to the button from the resource file while there are still
   * options to add in (displayed in the resources file as BUTTON_OPTIONS_IDENTIFIER + the number
   * of the stylesheet starting with 0)
   */
  private void addOptionsToButton(ChoiceBox<String> styleButton) {
    englishCSSMappings = new HashMap<>();
    try {
      ResourceBundle englishResources = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE +
          GameView.LANGUAGE_FOLDER + GameView.DEFAULT_STARTING_LANGUAGE);
      addOptionsCSSAndEnglish(styleButton, englishResources);
      styleButton.setValue(super.getResources().getString("NameStyleSheet0"));
    } catch (ModelException | MissingResourceException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage());
    }
  }

  /*
   * Adds the options to the button and the css file in the current language to a mapping that
   * maps to the english language to find the CSS later
   */
  private void addOptionsCSSAndEnglish(ChoiceBox<String> styleButton,
      ResourceBundle englishResources) {
    boolean stillButtonsToAdd = true;
    int count = 0;
    while (stillButtonsToAdd) {
      try {
        count = addButtonOption(styleButton, englishResources, count);
      } catch (MissingResourceException e) {
        stillButtonsToAdd = false;
      }
    }
  }

  /*
   * Adds a single option to the button and the css file in the current language to a mapping that
   * maps to the english language to find the CSS later
   */
  private int addButtonOption(ChoiceBox<String> styleButton, ResourceBundle englishResources,
      int count) {
    String option = super.getResources().getString(BUTTON_OPTIONS_IDENTIFIER + count);
    String englishOption = englishResources.getString(BUTTON_OPTIONS_IDENTIFIER + count);
    englishCSSMappings.put(option, englishOption);
    styleButton.getItems().add(option);
    count++;
    return count;
  }


  /*
   * Read in the property file and pass it into the main if it is not null and enable the buttons
   * if the setup was successful. CSS sometimes comes in as null, but just ignore because will be
   * passed in as actual value later
   */
  private void readInCSSFile(String cssFile) {
    try {
      String cssFileEnglish = englishCSSMappings.get(cssFile).replaceAll(" ", "");
      Optional<Scene> scene = createCSSControls.changeCSS(cssFileEnglish +
          GameView.CSS_FILE_EXTENSION);
      scene.ifPresent(gamePlayAndSceneControls::setScene);
    } catch (ModelException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage());
    } catch (NullPointerException ignored) {
    }
  }

  /**
   * updates language of that button's text
   */
  public void updateLanguageOnFeature() {
    super.getCurrButton().getItems().clear();
    addOptionsToButton(super.getCurrButton());
  }

}
