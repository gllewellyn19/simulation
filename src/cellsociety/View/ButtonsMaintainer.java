package cellsociety.View;

import cellsociety.Controller.Controller;
import cellsociety.Interfaces.AccessToOtherButtons;
import cellsociety.Interfaces.CreateCSSControls;
import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.LoadingExportingPropertiesFilesControls;
import cellsociety.Interfaces.LanguageControls;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Interfaces.StateColorAndImageChanges;
import cellsociety.View.Buttons.BoardInteractiveFeature;
import cellsociety.View.Buttons.ChooseLanguageButton;
import cellsociety.View.Buttons.ColorChangeButton;
import cellsociety.View.Buttons.ExportCurrentConfigurationButton;
import cellsociety.View.Buttons.ImageStateChangeButton;
import cellsociety.View.Buttons.LaunchGameButton;
import cellsociety.View.Buttons.LoadNewStartingConfigButton;
import cellsociety.View.Buttons.LoadStyleButton;
import cellsociety.View.Buttons.NextGenerationButton;
import cellsociety.View.Buttons.PauseButton;
import cellsociety.View.Buttons.PlayButton;
import cellsociety.View.Buttons.SpeedSlider;
import cellsociety.View.Buttons.ChangeStylesheetButton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The purpose of this class is to maintain and initialize all the buttons in this project. This
 * class keeps all the interactive features in this project in a map because they all implement
 * the BoardInteractiveFeature abstract class. This class also has the capability to update the
 * languages of the buttons and provide access to the buttons. Also, this class creates the starting
 * panels of the buttons to display on the GUI. This class depends on the buttons and the
 * interfaces it has. An example of how to use this class is give it to the gameView to maintain
 * the buttons of the game
 *
 * @author Grace Llewellyn
 */
public class ButtonsMaintainer implements AccessToOtherButtons {

  private final GamePlayAndSceneControls gamePlayAndSceneControls;
  private final LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls;
  private final StateColorAndImageChanges stateColorAndImageChanges;
  private final CreateCSSControls createCSSControls;
  private final LanguageControls languageControls;
  private final ErrorPrintingControls errorPrintingControls;
  private final Map<String, BoardInteractiveFeature> buttons;

  public ButtonsMaintainer(
      GamePlayAndSceneControls gamePlayAndSceneControls,
      LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls,
      StateColorAndImageChanges stateColorAndImageChanges, CreateCSSControls createCSSControls,
      LanguageControls languageControls, ErrorPrintingControls errorPrintingControls) {
    this.gamePlayAndSceneControls = gamePlayAndSceneControls;
    this.loadingExportingPropertiesFilesControls = loadingExportingPropertiesFilesControls;
    this.stateColorAndImageChanges = stateColorAndImageChanges;
    this.createCSSControls = createCSSControls;
    this.languageControls = languageControls;
    this.errorPrintingControls = errorPrintingControls;
    buttons = new HashMap<>();
  }

  /*
   * Make the initial input panel which lets the user select the language and set the buttons in
   * the middle of the screen
   */
  protected Node makeInitialInputPanel() {
    HBox startingButtons = new HBox();
    ResourceBundle resources = getPropertiesFromLanguage(GameView.DEFAULT_STARTING_LANGUAGE);
    BoardInteractiveFeature chooseLanguageButton = new ChooseLanguageButton(resources, languageControls);
    BoardInteractiveFeature launchGameButton = new LaunchGameButton(resources, gamePlayAndSceneControls,
        createCSSControls, errorPrintingControls);
    buttons.put("ChooseLanguageButton", chooseLanguageButton);
    buttons.put("LaunchGameButton", launchGameButton);
    startingButtons.getChildren().addAll(chooseLanguageButton.getCurrInteractiveFeature(),
        launchGameButton.getCurrInteractiveFeature());
    startingButtons.setAlignment(Pos.BASELINE_CENTER);
    return startingButtons;
  }

  /*
   * Makes the navigation panel of buttons for the top of the screen. Either returns everything
   * for the first row or for the bottom row
   */
  protected Node makeNavigationPanel(List<String> buttonsForRow) {
    HBox nodeForRow = new HBox();
    for (String buttonName: buttonsForRow) {
      nodeForRow.getChildren().add(buttons.get(buttonName).getCurrInteractiveFeature());
    }
    return nodeForRow;
  }

  /*
   * Creates the buttons for the top navigation panel and adds them and disables the correct ones
   * for a start of game setting
   */
  protected Node makeInputPanel() {
    createAllButtons();
    return addButtonsToInputPanel();
  }


  /**
   * Adds all the buttons to the input panel in 3 rows
   *
   * @return  - Node that is our Panel where buttons are contained
   */
  public Node addButtonsToInputPanel() {
    VBox buttonsInputPanel = new VBox();
    List<String> firstRowButtons = List.of("PauseButton", "PlayButton", "NextGenerationButton",
        "LoadNewStartingConfigButton", "LoadStyleButton");
    List<String> secondRowButtons = List.of("ExportCurrentConfigurationButton", "ColorChangeButton",
        "ImageStateChangeButton");
    List<String> thirdRowButtons = List.of("ChangeStylesheetButton", "SpeedSlider");
    buttonsInputPanel.getChildren().add(makeNavigationPanel(firstRowButtons));
    buttonsInputPanel.getChildren().add(makeNavigationPanel(secondRowButtons));
    buttonsInputPanel.getChildren().add(makeNavigationPanel(thirdRowButtons));
    return buttonsInputPanel;
  }

  /*
   * Creates all the buttons once the language is known and the game is launching
   */
  private void createAllButtons() {
    ResourceBundle resources = getPropertiesFromLanguage(languageControls.getLanguage());
    addGamePlayButtons(resources);
    addLoadingExportCurrentConfigurationButton(resources);
    addStyleButtons(resources);
    startingEnableOfButtons();
  }

  /*
   * Add the buttons that concern the style of the game
   */
  private void addStyleButtons(ResourceBundle resources) {
    buttons.put("LoadStyleButton", new LoadStyleButton(resources, gamePlayAndSceneControls,
        loadingExportingPropertiesFilesControls));
    buttons.put("ChangeStylesheetButton", new ChangeStylesheetButton(resources, createCSSControls,
        errorPrintingControls, gamePlayAndSceneControls));
    buttons.put("ImageStateChangeButton", new ImageStateChangeButton(resources,
        gamePlayAndSceneControls, stateColorAndImageChanges, errorPrintingControls));
    buttons.put("ColorChangeButton", new ColorChangeButton(resources, gamePlayAndSceneControls,
        stateColorAndImageChanges, errorPrintingControls));
  }

  /*
   * Adds the buttons that concern the play of the game like play and pause
   */
  private void addGamePlayButtons(ResourceBundle resources) {
    buttons.put("SpeedSlider", new SpeedSlider(resources, gamePlayAndSceneControls));
    buttons.put("NextGenerationButton", new NextGenerationButton(resources, gamePlayAndSceneControls));
      buttons.put("PlayButton", new PlayButton(resources, gamePlayAndSceneControls,
          this));
      buttons.put("PauseButton", new PauseButton(resources, gamePlayAndSceneControls,
          this));
  }

  /*
   * Adds the buttons that concern exporting and loading in property files for the game
   */
  private void addLoadingExportCurrentConfigurationButton(ResourceBundle resources) {
    buttons.put("ExportCurrentConfigurationButton", new ExportCurrentConfigurationButton(resources,
          gamePlayAndSceneControls, loadingExportingPropertiesFilesControls,
          errorPrintingControls));
    buttons.put("LoadNewStartingConfigButton", new LoadNewStartingConfigButton(resources,
        gamePlayAndSceneControls, loadingExportingPropertiesFilesControls, this));
  }

  private ResourceBundle getPropertiesFromLanguage(String language) {
    return ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE +
        GameView.LANGUAGE_FOLDER + language.toLowerCase());
  }

  /*
   * Enable and disable certain buttons to start
   */
  private void startingEnableOfButtons() {
    List<String> buttonsToDisable = List.of("PauseButton", "PlayButton", "NextGenerationButton",
        "ExportCurrentConfigurationButton", "ColorChangeButton", "ImageStateChangeButton",
        "SpeedSlider", "LoadStyleButton");
    for (String buttonName: buttonsToDisable) {
      buttons.get(buttonName).getCurrInteractiveFeature().setDisable(true);
    }
  }

  /*
   * Returns the requested button based on the string type of the button- aligns with what the
   * button is called in the resource properties file
   */
  public BoardInteractiveFeature getButton(String type) {
    BoardInteractiveFeature buttonToReturn = buttons.get(type);
    if (buttonToReturn == null) {
      throw new ModelException("getButtonException");
    }
    return buttonToReturn;
  }

  /**
   * Updates the language of all of the interactive features of the problem
   * @param newLanguage - string representing new language for button
   */
  public void updateButtonLanguage(String newLanguage) {
    ResourceBundle newLanguageResources = getPropertiesFromLanguage(newLanguage);
    for (Map.Entry<String,BoardInteractiveFeature> entry : buttons.entrySet()){
      entry.getValue().updateLanguage(newLanguageResources);
    }
  }

}
