package cellsociety.View.Buttons;

import cellsociety.Interfaces.AccessToOtherButtons;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Interfaces.LoadingExportingPropertiesFilesControls;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * This class implements the load new starting configuration button which loads in a new starting
 * configuration and populates the board with it (doesn't include color but does include author,
 * title, description, neighbor types, edge types, number of types, simulation type, and information
 * about how to make the starting configuration (where CSV is, random, checkered)
 *
 * @author Grace Llewellyn
 */
public class LoadNewStartingConfigButton extends BoardButton {
  private final FileChooser fileChooser = new FileChooser();

  private final LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls;
  private final AccessToOtherButtons accessToOtherButtons;

  public LoadNewStartingConfigButton(ResourceBundle resources,
      GamePlayAndSceneControls gamePlayAndSceneControls, LoadingExportingPropertiesFilesControls
      loadingExportingPropertiesFilesControls, AccessToOtherButtons accessToOtherButtons) {
    super(resources, new Button(), gamePlayAndSceneControls, "LoadNewPropertyFileButton");
    this.loadingExportingPropertiesFilesControls = loadingExportingPropertiesFilesControls;
    this.accessToOtherButtons = accessToOtherButtons;
  }

  /*
   * Initializing the text and id of the button from the resources bundle and sets the action of the
   * button to be uploading a new properties file to the controller and enabling the play,
   * next generation and export buttons
   */
  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    Stage stage = super.getGamePlayAndSceneControls().getStage();
    super.getCurrButton().setOnAction(
        e -> readInPropertyFile(stage));
  }

  /*
   * Read in the property file and pass it into the main if it is not null and enable the button
   * to load the style if the setup was successful because this is the next button that must be
   * pressed
   */
  private void readInPropertyFile(Stage stage) {
    configureFileChooser(fileChooser);
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      loadingExportingPropertiesFilesControls.uploadPropertiesFile(file.getAbsolutePath());
      if (loadingExportingPropertiesFilesControls.getSetupSuccessful()) {
        updateButtonEnables();
      }
    }
  }

  /*
   * Now that a file has been loaded in, updates the play and next generation buttons to be enabled
   */
  private void updateButtonEnables() {
    accessToOtherButtons.getButton("PlayButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("NextGenerationButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("ExportCurrentConfigurationButton").
        getCurrInteractiveFeature().setDisable(false);
    accessToOtherButtons.getButton("ColorChangeButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("ImageStateChangeButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("LoadStyleButton").getCurrInteractiveFeature().
        setDisable(false);

  }

  /*
   * Configures the file choose by setting default title of dialog, and only letting sim files be
   * selected
   */
  private void configureFileChooser(FileChooser fileChooser) {
    fileChooser.setTitle(super.getResources().getString("LoadNewPropertyFileButton"));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Sim Files", "*.sim")
    );
  }
}
