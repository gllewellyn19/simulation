package cellsociety.View.Buttons;

import cellsociety.Controller.Controller;
import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.LoadingExportingPropertiesFilesControls;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Model.Exceptions.ModelException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * This class implements the export configuration button that exports the current properties of the
 * game into a properties file and exports the current board as a CSV
 *
 * @author Grace Llewellyn
 */
public class ExportCurrentConfigurationButton extends BoardButton {

  public static final String SIM_EXTENSION = ".sim";
  public static final String RESOURCES_FOLDER = "data";

  private final FileChooser fileChooser = new FileChooser();

  private final LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls;
  private final ErrorPrintingControls errorPrintingControls;

  public ExportCurrentConfigurationButton(ResourceBundle resources,
      GamePlayAndSceneControls gamePlayAndSceneControls,
      LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls,
      ErrorPrintingControls errorPrintingControls) {
    super(resources, new Button(), gamePlayAndSceneControls, "ExportCurrentConfigurationButton");
    this.loadingExportingPropertiesFilesControls = loadingExportingPropertiesFilesControls;
    this.errorPrintingControls = errorPrintingControls;
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    Scene scene = super.getGamePlayAndSceneControls().getScene();
    super.getCurrButton().setOnAction(
        e -> {
          if (scene != null) {
            File saveFile = fileChooser.showSaveDialog(scene.getWindow());
            if (saveFile != null) {
              onExportClick(saveFile.getAbsolutePath(), saveFile.getName());
            }
          }
        });
  }


  /*
   * Pauses the game and then exports the current configuration of the board as a csv
   */
  private void onExportClick(String filePath, String fileName) {
    try {
      super.getGamePlayAndSceneControls().pause();
      String filePathForCSV = (new File("")) + RESOURCES_FOLDER + "/" + fileName;
      loadingExportingPropertiesFilesControls.exportBoardAsCSV(filePathForCSV);
      createPropertiesFile(filePath, fileName);
    } catch (ModelException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage());
    }
  }

  /*
   * Creates a new properties file with given information and information from the current program
   */
  private void createPropertiesFile(String filePath, String fileName) {
    List<String> keysForPropertiesFile = new ArrayList<>(List.of("SimulationType",
        "TitleOfSimulation", "Author", "Description", "InitialConfiguration", "NumberOfStates"));
    List<String> toWriteProperties = new ArrayList<>();
    boolean successfullyGotInput = getTitleAuthorDescriptionInput(toWriteProperties);

    if (successfullyGotInput) {
      getThresholdGameTypeFilename(fileName, keysForPropertiesFile,
          toWriteProperties);
      getNeighborAndEdgeTypeAndNumStates(toWriteProperties, keysForPropertiesFile);
      writeToPropertiesFile(filePath, keysForPropertiesFile, toWriteProperties);
    }
  }

  /*
   * Adds the neighbor and edge types of the list of properties
   */
  private void getNeighborAndEdgeTypeAndNumStates(List<String> toWriteToProperties, List<String>
      keysForPropertiesFile) {
    toWriteToProperties.add(Integer.toString(loadingExportingPropertiesFilesControls.getNumberOfStates()));

    String neighborType = loadingExportingPropertiesFilesControls.getNeighborType();
    if (neighborType != null) {
      toWriteToProperties.add(neighborType);
      keysForPropertiesFile.add("NeighborType");
    }

    String edgeType = loadingExportingPropertiesFilesControls.getEdgeType();
    if (edgeType != null) {
      toWriteToProperties.add(edgeType);
      keysForPropertiesFile.add("NeighborType");
    }
  }

  /**
   * @param filePath - file path where the properties file we want to write to is
   * @param keysForPropertiesFile - List<String> of all the keys for that property file
   * @param toWriteToProperties - List<String> of all the values for that property file
   * Writes the properties and keys to the properties file
   */
  public void writeToPropertiesFile(String filePath, List<String> keysForPropertiesFile,
      List<String> toWriteToProperties) {
    try {
      FileWriter writer = new FileWriter(filePath + SIM_EXTENSION);
      for (int i = 0; i < keysForPropertiesFile.size(); i++) {
        writer.write(keysForPropertiesFile.get(i) + "=" + toWriteToProperties.get(i));
        writer.flush();
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      throw new ModelException("writeToPropertiesFileException",e);
    }
  }

  /*
   * Gets the threshold, game type, and file name and adds to the keys and the properties to write
   */
  private void getThresholdGameTypeFilename(String fileName, List<String> keysForPropertiesFile,
      List<String> toWriteToProperties) {
    String threshold = loadingExportingPropertiesFilesControls.getThreshold();
    String gameType = loadingExportingPropertiesFilesControls.getGameType();
    toWriteToProperties.add(0, gameType);
    toWriteToProperties.add(RESOURCES_FOLDER + "/" + fileName + Controller.CSV_EXTENSION);
    if (threshold!=null) {
      toWriteToProperties.add(threshold);
      keysForPropertiesFile.add("Threshold");
    }
  }

  /*
   * Get the title, author and description for the user and save it along in the list of properties
   * to write
   */
  private boolean getTitleAuthorDescriptionInput(List<String> toWriteToProperties) {
    List<String> messages = List.of("title", "author", "description");
    for (int i=0; i< messages.size(); i++) {
      toWriteToProperties.add(JOptionPane.showInputDialog("Please input "+ messages.get(i)+":"));
      if (toWriteToProperties.get(i)==null) {
        return false;
      }
    }
    return true;
  }

}
