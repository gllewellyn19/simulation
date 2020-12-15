package cellsociety.View.Buttons;

import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Interfaces.LoadingExportingPropertiesFilesControls;
import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class implements the load style button which loads in a new styles property file which can
 * change the color and images of the cells and the language and if the grid is outlined or not.
 * However, this button just calls the method to do that in game view and doesn't do it itself
 *
 * @author Grace Llewellyn
 */
public class LoadStyleButton extends BoardButton {

  private final FileChooser fileChooser = new FileChooser();

  private final LoadingExportingPropertiesFilesControls loadingExportingPropertiesFilesControls;

  public LoadStyleButton(ResourceBundle resources,
      GamePlayAndSceneControls gamePlayAndSceneControls, LoadingExportingPropertiesFilesControls
      loadingExportingPropertiesFilesControls) {
    super(resources, new Button(), gamePlayAndSceneControls, "LoadStyleButton");
    this.loadingExportingPropertiesFilesControls = loadingExportingPropertiesFilesControls;
  }

  /*
   * Initializing the text and id of the button from the resources bundle and sets the action of the
   * button to be uploading a new properties file to the controller and enabling the play,
   * next generation and export buttons
   */
  @Override
  public void initializeThisButton() {
    Stage stage = super.getGamePlayAndSceneControls().getStage();
    super.initializeThisButton();
    super.getCurrButton().setOnAction(
        e -> readInPropertyFile(stage));
  }



  /*
   * Read in the property file and pass it into the main if it is not null and enable the buttons
   * if the setup was successful
   */
  private void readInPropertyFile(Stage stage) {
    configureFileChooser(fileChooser);
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      loadingExportingPropertiesFilesControls.uploadStylePropertiesFile(file.getAbsolutePath());
    }
  }

  /*
   * Configures the file choose by setting default title of dialog, and only letting properties
   * files be selected
   */
  private void configureFileChooser(FileChooser fileChooser) {
    fileChooser.setTitle(super.getResources().getString("LoadStyleButton"));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Property Files", "*.properties")
    );
  }

}
