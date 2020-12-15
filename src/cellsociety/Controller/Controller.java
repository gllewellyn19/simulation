package cellsociety.Controller;

import cellsociety.Interfaces.AccessToCellsState;
import cellsociety.Interfaces.AccessorsForCreatingAScene;
import cellsociety.Interfaces.LoadingExportingPropertiesFilesControls;
import cellsociety.Model.Board;
import cellsociety.Model.CheckPropertiesRequirements;
import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.Model.Exceptions.MissingFilePathException;
import cellsociety.Model.Exceptions.MissingPropertyException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Interfaces.StateColorAndImageChanges;
import cellsociety.Model.Exceptions.PropertyValueException;
import cellsociety.Model.Exceptions.StartingConfigurationException;
import cellsociety.View.GameView;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.Properties;
import javafx.scene.Scene;

/**
 * Controller class links both the Model and View elements of our code
 */
public class Controller implements AccessToCellsState, AccessorsForCreatingAScene,
    LoadingExportingPropertiesFilesControls, StateColorAndImageChanges {

  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  public static final String SEPARATOR_FOR_GUI_TITLE = "- ";
  public static final String CSV_EXTENSION = ".csv";

  private Board modelBoard;
  private final GameView gameView;
  private Properties properties;
  private boolean setupSuccessful = true;
  private int rows;
  private int cols;
  private int numOfStates;
  private LineGraph currLineGraph;
  private final GamePlayAndSceneControls gamePlayAndSceneControls;

  /**
   * @param gamePlayAndSceneControls - interface object that holds methods such as pause, unpause,
   *                                 setTitle, setScene, setStage, getScene and slowDownOrSpeedUpAnimation
   */
  public Controller(GamePlayAndSceneControls gamePlayAndSceneControls) {
    this.gamePlayAndSceneControls = gamePlayAndSceneControls;
    gameView = new GameView(gamePlayAndSceneControls,
        this, this, this,
        this);
  }

  /**
   * Uploads a properties file and creates the board from it after making sure that all required
   * keys are present
   *
   * @param propertiesFilePath - path where properties file is found
   */
  public void uploadPropertiesFile(String propertiesFilePath) {
    setupSuccessful = true;
    try {
      properties = getRequiredPropertiesFromFile(propertiesFilePath);
      String title = properties.getProperty("TitleOfSimulation") + SEPARATOR_FOR_GUI_TITLE +
          properties.getProperty("Author") + SEPARATOR_FOR_GUI_TITLE + properties
          .getProperty("Description");
      gamePlayAndSceneControls.getStage().setTitle(title);
      createAndReadInNewBoard();
    } catch (ModelException | MissingResourceException | StartingConfigurationException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage());
    } catch (PropertyValueException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage(), e.getPropertyInfo());
    } catch (MissingFilePathException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage(), e.getFilePath());
    }
  }

  /**
   * Applies a new style properties file to the scene
   *
   * @param propertiesFilePath - path where properties file is found
   */
  public void uploadStylePropertiesFile(String propertiesFilePath) {
    try {
      Properties properties = getPropertiesFromFile(propertiesFilePath);
      gameView.applyStylePropertiesFile(properties);
    } catch (ModelException | MissingResourceException e) {
      gameView.printErrorMessageAlert(e.getMessage());
    } catch (InvalidPropertyException e) {
      gameView.printErrorMessageAlert(e.getMessage(), e.getPropertyInfo());
    }
  }

  /*
   * Returns a properties object from the given file path and throws a MissingPropertyException
   * if one of the required values is missing
   */
  private Properties getRequiredPropertiesFromFile(String propertiesFilePath) throws ModelException,
      MissingPropertyException {
    Properties properties = getPropertiesFromFile(propertiesFilePath);
    CheckPropertiesRequirements pRequirements = new CheckPropertiesRequirements();
    pRequirements.checkAllPropertiesRequiredPresent(properties);
    return properties;
  }

  /*
   * Returns a new properties object from the given file path
   */
  private Properties getPropertiesFromFile(String propertiesFilePath) throws ModelException {
    Properties properties = new Properties();
    try {
      FileInputStream fis = new FileInputStream(propertiesFilePath);
      properties.load(fis);
      fis.close();
    } catch (IOException e) {
      throw new ModelException("getPropertiesException", e);
    }
    return properties;
  }

  /*
   * Updates the board element and the model element each step
   */
  public void updateBoardForStep() {
    modelBoard.changeBoardGeneration();
    gameView.getDisplayBoard().updateDisplay();
    currLineGraph.updateLineGraph();
  }

  /*
   * Creates a new board from the file in the properties file
   */
  public void createAndReadInNewBoard() {
    createModelBoard(properties.getProperty("InitialConfiguration"));
    currLineGraph = new LineGraph(getNumberOfStates(), rows, cols, this);
    Optional<Scene> scene = gameView.makeASceneWithInitialCSS();
    setSceneIfPresent(scene);
  }

  /**
   * @param fileInfo           - object in the form properties.getProperty("wanted_key")
   * @param propertiesFilePath - path where properties file is found
   */
  /*
   * Creates a new board from the file in the properties file made separate function because testing
   */
  public void createAndReadInNewBoardForTesting(String fileInfo, String propertiesFilePath) {
    try {
      properties = getRequiredPropertiesFromFile(propertiesFilePath);
      createModelBoard(fileInfo);
      currLineGraph = new LineGraph(getNumberOfStates(), rows, cols, this);
      Optional<Scene> scene = gameView.makeASceneWithInitialCSS();
      setSceneIfPresent(scene);

    } catch (ModelException | MissingResourceException | StartingConfigurationException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage());
    } catch (PropertyValueException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage(), e.getPropertyInfo());
    } catch (MissingFilePathException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage(), e.getFilePath());
    }
  }

  //We believe optional is the best way to represent this value
  private void setSceneIfPresent(Optional<Scene> scene) {
    if (scene.isPresent()) {
      gamePlayAndSceneControls.setScene(scene.get());
      gameView.getDisplayBoard().updateDisplay();
    }
  }

  /*
   * Creates the model board from the specified file info and gets the rows and columns from it
   */
  private void createModelBoard(String fileInfo) {
    setNumberOfStates(properties.getProperty("NumberOfStates"));
    modelBoard = new Board(fileInfo, properties.getProperty("SimulationType"),
        properties.getProperty("Threshold"), numOfStates, properties.getProperty("NeighborType"),
        properties.getProperty("EdgeType"));
    rows = modelBoard.getCurrBoard().getRows();
    cols = modelBoard.getCurrBoard().getCols();
  }

  /**
   * For export configuration button so the button doesn't need to know the property key
   *
   * @return - value from key "SimulationType" in properties file
   */
  public String getGameType() {
    return properties.getProperty("SimulationType");
  }

  /**
   * @return - gameView object
   */
  public GameView getGameView() {
    return gameView;
  }

  /**
   * For export configuration button so the button doesn't need to know the property key
   *
   * @return - value from key "Threshold" in properties file
   */
  public String getThreshold() {
    return properties.getProperty("Threshold");
  }

  /**
   * For export configuration button so the button doesn't need to know the property key
   *
   * @return - value from key "EdgeType" in properties file
   */
  public String getEdgeType() {
    return properties.getProperty("EdgeType");
  }

  /**
   * For export configuration button so the button doesn't need to know the property key
   *
   * @return - value from key "NeighborType" in properties file
   */
  public String getNeighborType() {
    return properties.getProperty("NeighborType");
  }

  /**
   * Used to know whether to enable buttons or not
   *
   * @return - boolean stating state of success of the current setup
   */
  public boolean getSetupSuccessful() {
    return setupSuccessful;
  }

  /**
   * @param i - i coordinate of cell (row number)
   * @param j - j coordinate of cell (column number)
   */
  public void changeStateOfClickedCell(int i, int j) {
    modelBoard.getCurrBoard().getCurrCell(i, j).changeStateWhenClicked();
    gameView.getDisplayBoard().updateDisplay();
  }

  /**
   * @param i - i coordinate of cell (row number)
   * @param j - j coordinate of cell (column number)
   * @return - the state of the requested cell (moved to controller to promote the MVC)
   */
  public int getCellState(int i, int j) {
    return modelBoard.getCurrBoard().getCurrCell(i, j).getState();
  }

  /**
   * @return - total rows number of current board
   */
  public int getRows() {
    return rows;
  }

  /**
   * @return - total column number of current board, used for creating a new display board
   */
  public int getCols() {
    return cols;
  }

  /**
   * @return properties object used for line graph
   */
  public Properties getProperties() {
    return properties;
  }

  /**
   * Exports the model board as a CSV (saves the CSV to the file path in model board)
   *
   * @param filePath - path to save current board setup as csv
   */
  public void exportBoardAsCSV(String filePath) {
    try {
      modelBoard.exportCurrentBoardAsCSV(filePath);
    } catch (ModelException e) {
      setupSuccessful = false;
      gameView.printErrorMessageAlert(e.getMessage());
    }
  }

  /*
   * Sets the number of states and throws an exception if not a number
   */
  private void setNumberOfStates(String numberOfStates) {
    try {
      numOfStates = Integer.parseInt(numberOfStates);
    } catch (NumberFormatException e) {
      throw new ModelException("incorrectNumOfStatesException", e);
    }
  }

  /**
   * @return - number of states of current board
   */
  public int getNumberOfStates() {
    return numOfStates;
  }

  /**
   * SOLELY USED FOR TESTING: Not actually breaking MVC
   */
  public Board getModelBoard() {
    return modelBoard;
  }

  public LineGraph getCurrLineGraph() {
    return currLineGraph;
  }
}

