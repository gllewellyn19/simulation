package cellsociety.View;

import cellsociety.Interfaces.AccessToCellsState;
import cellsociety.Interfaces.AccessorsForCreatingAScene;
import cellsociety.Controller.Controller;
import cellsociety.Interfaces.CreateCSSControls;
import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.LoadingExportingPropertiesFilesControls;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Interfaces.LanguageControls;
import cellsociety.Interfaces.StateColorAndImageChanges;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.View.Boards.DisplayBoard;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class GameView implements CreateCSSControls, LanguageControls, ErrorPrintingControls {

  public static final String STARTING_STYLESHEET = "Default.css";
  public static final String DEFAULT_STARTING_LANGUAGE = "English";
  public static final String STYLESHEETS_FOLDER = "Stylesheets/";
  public static final String CSS_FILE_EXTENSION = ".css";
  public static final String LANGUAGE_FOLDER = "languages/";
  public static final String EXCEPTIONS_FOLDER = "exceptions/";
  public static final String EXCEPTIONS_FILE = "Exceptions";
  public static final String DEFAULT_MESSAGE_MISSING_EXCEPTIONS = "Cannot find resource bundle for "
      + "exceptions";

  private StateFillMaintainer stateFillMaintainer;
  private BorderPane displayLayout;
  private DisplayBoard displayBoard;
  private final ButtonsMaintainer buttons;
  private final AccessToCellsState accessToCellsState;
  private final AccessorsForCreatingAScene accessorsForCreatingAScene;

  private String language = DEFAULT_STARTING_LANGUAGE;

  public GameView(GamePlayAndSceneControls gamePlayAndSceneControls,
      AccessToCellsState accessToCellsState,
      AccessorsForCreatingAScene accessorsForCreatingAScene,
      LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls,
      StateColorAndImageChanges stateColorAndImageChanges) {
    this.accessorsForCreatingAScene = accessorsForCreatingAScene;
    this.accessToCellsState = accessToCellsState;
    buttons = new ButtonsMaintainer(gamePlayAndSceneControls, loadingExportingPropertiesFilesControls,
        stateColorAndImageChanges, this, this, this);
  }


  /**
   * Add all the nodes to the layout, add the gridPane (center), add the buttons, and add the
   * stylesheet
   * @return - a javafx scene
   */
  public Optional<Scene> makeASceneWithInitialCSS() {
    return makeASceneWithCSS(STARTING_STYLESHEET);
  }


  /**
   * Create a scene from the given style sheet
   *
   * @param cssFile - string with name of wanted css file
   * @return - a javafx scene
   */
  public Optional<Scene> makeASceneWithCSS(String cssFile) {
    try {
      int width = Controller.DEFAULT_SIZE.width;
      int height = Controller.DEFAULT_SIZE.height;
      displayLayout = new BorderPane();
      stateFillMaintainer = new StateFillMaintainer(accessorsForCreatingAScene.getNumberOfStates());
      displayBoard = new DisplayBoard(width, height, accessorsForCreatingAScene.getRows(),
          accessorsForCreatingAScene.getCols(), stateFillMaintainer, accessToCellsState);
      displayLayout.setCenter(displayBoard.getDisplayBoard());
      displayLayout.setTop(buttons.makeInputPanel());
      setUpDisplayBoardMouseInput();
      return Optional.of(uploadCSSFile(width, height, cssFile));
    } catch (ModelException | MissingResourceException e) {
      printErrorMessageAlert(e.getMessage());
    }
    return Optional.empty();
  }


  /**
   * * Create a scene from the given style sheet but doesn't modify most of the scene
   *
   * @param cssFile - string with name of wanted css file
   * @return - a new javafx scene with new css
   */
  public Optional<Scene> changeCSS(String cssFile) {
    try {
      displayLayout = new BorderPane();
      displayLayout.setCenter(displayBoard.getDisplayBoard());
      displayLayout.setTop(buttons.addButtonsToInputPanel());
      return Optional.of(uploadCSSFile(Controller.DEFAULT_SIZE.width,
          Controller.DEFAULT_SIZE.height, cssFile));
    } catch (ModelException | MissingResourceException e) {
      printErrorMessageAlert(e.getMessage());
    }
    return Optional.empty();
  }


  /**
   * @param width - width of new scene
   * @param height -  height of new scene
   * @return - starting scene with the default css that appears in the center of the screen
   */
  public Optional<Scene> makeAnInitialScene(int width, int height) {
    try {
      ResourceBundle resources = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE +
          LANGUAGE_FOLDER + DEFAULT_STARTING_LANGUAGE);
      Text topText = new Text(resources.getString("InitialStartText"));
      BorderPane.setAlignment(topText, Pos.TOP_CENTER);
      displayLayout = new BorderPane(buttons.makeInitialInputPanel(), topText, null, null,
          null);
      return Optional.of(uploadCSSFile(width, height, STARTING_STYLESHEET));
    } catch (MissingResourceException e) {
      printErrorMessageAlert(e.getMessage());
    }
    return Optional.empty();
  }


  /**
   * @param width - width of returned scene
   * @param height - height of returned scene
   * @param cssFile - string with name of wanted css file
   * @return - scene with a new style sheet for the view
   * @throws ModelException
   */
  public Scene uploadCSSFile(int width, int height, String cssFile) throws ModelException{
    Scene scene = new Scene(displayLayout, width, height);
    try {
      scene.getStylesheets()
          .add(getClass().getResource(Controller.DEFAULT_RESOURCE_FOLDER + STYLESHEETS_FOLDER +
              cssFile).toExternalForm());
    } catch (NullPointerException e) {
      throw new ModelException("cssFileNotFound",e);
    }
    return scene;
  }

  /**
   * @return current DisplayBoard
   */
  public DisplayBoard getDisplayBoard() {
    return displayBoard;
  }

  private void setUpDisplayBoardMouseInput() {
    displayBoard.getDisplayBoard().setOnMouseClicked(this::findClickedCell);
  }

  /*
   * Finds the clicked cell and changes its state
   */
  private void findClickedCell(MouseEvent event) {
    Node clickedRectangle = (Node) event.getTarget();
    for (int i = 0; i < displayBoard.getGridCells().length; i++) {
      for (int j = 0; j < displayBoard.getGridCells()[i].length; j++) {
        if (displayBoard.getGridCells()[i][j] == clickedRectangle) {
          accessToCellsState.changeStateOfClickedCell(i, j);
        }
      }
    }
  }

  /**
   * @return - stateFillMaintainer object
   */
  public StateFillMaintainer getStateFillMaintainer() {
    return stateFillMaintainer;
  }

  /**
   * @param language - new language wanted
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /**
   * @return - current language in use
   */
  public String getLanguage() {
    return language;
  }

  /**
   * @param key - key contained in exception .properties file with exception message as value
   * Prints the given error message as an alert. Given a key that corresponds to a resource bundle
   * with the correct error message
   */
  public void printErrorMessageAlert(String key) {
    createAlert(key, "");
  }

  /**
   * @param key- key contained in exception .properties file with exception message as value
   * @param additionalMessage - additional message to be printed when exception is caught
   * Creates and display an alert with the given message
   */
  private void createAlert(String key, String additionalMessage) {
    try {
      Alert errorAlert = new Alert(AlertType.ERROR);
      ResourceBundle exceptions = ResourceBundle.getBundle(Controller.DEFAULT_RESOURCE_PACKAGE +
          EXCEPTIONS_FOLDER + language.toLowerCase() + EXCEPTIONS_FILE);
      errorAlert.setHeaderText(exceptions.getString("headerForExceptionsAlerts"));
      errorAlert.setContentText(exceptions.getString(key) + additionalMessage);
      errorAlert.showAndWait();
    } catch (MissingResourceException e) {
      defaultAlertIfNoExceptionsBundle();
    }
  }

  /**
   * @param key- key contained in exception .properties file with exception message as value
   * @param additionalMessage - additional message to be printed when exception is caught
   * Prints the given error message as an alert. Given a key that corresponds to a resource bundle
   * with the correct error message and prints the additional message with a space between that and
   * the original message key
   */
  public void printErrorMessageAlert(String key, String additionalMessage) {
    createAlert(key, " " + additionalMessage);
  }

  /*
   * The default alert if cannot find the exceptions bundle because cannot get the exceptions to
   * print so must use default values
   */
  private void defaultAlertIfNoExceptionsBundle() {
    Alert errorAlert = new Alert(AlertType.ERROR);
    errorAlert.setContentText(DEFAULT_MESSAGE_MISSING_EXCEPTIONS);
    errorAlert.showAndWait();
  }

  /**
   * @param properties -property file
   * Applies a new style properties file to the game view that can include cell colors and images,
   * neighbor type, edge type, shape type, if you can see the grid outline or not, and language
   */
  public void applyStylePropertiesFile(Properties properties) {
    stateFillMaintainer.uploadStyleProperties(getMapForImagesOrColors(properties, "StateColor"),
        getMapForImagesOrColors(properties, "ImagePath"));
    setGridLinesIfApplicable(properties.getProperty("GridOutlined"));
    changeLanguageAndUpdateBoard(properties.getProperty("Language"));
    displayBoard.updateDisplay();
  }

  /*
   * changes the language variable and updates all the buttons to reflect the language change
   */
  private void changeLanguageAndUpdateBoard(String newLanguage) {
    if (newLanguage != null) {
      language = newLanguage;
    }
    buttons.updateButtonLanguage(language);
  }

  /*
   * Sets the neighbor, edge, or grid outline if the property style file specifies
   */
  private void setGridLinesIfApplicable(String gridOutline) {
    if (gridOutline != null) {
      displayBoard.changeGridOutline(gridOutline.equalsIgnoreCase("Yes"));
    }
  }

  /*
   * Creates an mapping of the state number to the string representing the image path or color
   */
  private Map<Integer, String> getMapForImagesOrColors(Properties properties, String prefix) {
    Map<Integer, String> mappingImagesOrColors = new HashMap<>();

    for (int i=0; i < accessorsForCreatingAScene.getNumberOfStates(); i++) {
      String property = properties.getProperty(prefix+i);
      if (property != null){
        mappingImagesOrColors.put(i, property);
      }
    }
    return mappingImagesOrColors;
  }

}
