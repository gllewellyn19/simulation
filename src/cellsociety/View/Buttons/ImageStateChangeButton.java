package cellsociety.View.Buttons;

import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Interfaces.StateColorAndImageChanges;
import cellsociety.View.StateFillMaintainer;
import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;


/**
 * This class implements the image change button that allows the user to upload an image to make
 * the cells of a certain state that image
 *
 * @author Grace Llewellyn
 */
public class ImageStateChangeButton extends BoardButton {

  private final FileChooser fileChooser = new FileChooser();

  private final StateColorAndImageChanges stateColorAndImageChanges;
  private final ErrorPrintingControls errorPrintingControls;

  public ImageStateChangeButton(ResourceBundle resources, GamePlayAndSceneControls
      gamePlayAndSceneControls, StateColorAndImageChanges stateColorAndImageChanges,
      ErrorPrintingControls errorPrintingControls) {
    super(resources, new Button(), gamePlayAndSceneControls, "ImageStateChangeButton");
    this.stateColorAndImageChanges = stateColorAndImageChanges;
    this.errorPrintingControls = errorPrintingControls;
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    super.getCurrButton().setOnAction(event -> onImageStateChangeClick());
  }

  /*
   * Gets the state number and image path and stores it in inputs array (states number at index 0
   * and file path at index 1). If the user presses cancel for the input dialog boxes, then nothing
   * happens
   */
  private boolean getInputStateAndImagePath(String[] inputs) {
    Optional<String> stateNum = getStateNumber();
    if (stateNum.isPresent()) {
      checkStateNumValid(stateNum.get());
      inputs[0] = stateNum.get();
      inputs[1] = getImageFilePath();
      return !inputs[1].equals("");
    }
    return false;
  }

  /*
   * Checks to make sure that the state number is a number and throws an error if not
   */
  private void checkStateNumValid(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new ModelException("checkValidStateException");
    }
  }

  /*
   * Gets the state number for the change. Returns null if state number not present in an Optional
   */
  private Optional<String> getStateNumber() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle(super.getResources().getString(super.getId()));
    dialog.setHeaderText(super.getResources().getString(super.getId()));
    dialog.setContentText(super.getResources().getString(super.getId()));

    return dialog.showAndWait();
  }


  /*
   * Pauses the game then gets the inputs from the user and changes the image in the state fill
   * maintainer if no errors
   */
  public void onImageStateChangeClick() {
    try {
      super.getGamePlayAndSceneControls().pause();
      String[] inputs = new String[2];
      if (getInputStateAndImagePath(inputs)) {
        changeStateImage(Integer.parseInt(inputs[0]), inputs[1]);
      }
    } catch (ModelException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage());
    } catch (InvalidPropertyException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage(), e.getPropertyInfo());
    }
  }

  /*
   * Gets the path to the image (only allows the user to select images) and returns an empty string
   * if the user doesn't enter a file
   */
  private String getImageFilePath() {
    configureFileChooser(fileChooser);
    File file = fileChooser.showOpenDialog(super.getGamePlayAndSceneControls().getStage());
    if (file != null) {
      return file.getAbsolutePath();
    }
    return "";
  }

  /*
   * Configures the file choose by setting default title of dialog, and only letting image files be
   * selected. The image files that are allowed to be selected are those supposed by the Java image
   * class
   */
  private void configureFileChooser(FileChooser fileChooser) {
    fileChooser.setTitle(super.getResources().getString(super.getId()));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("JPG Images", "*.JPG"),
        new FileChooser.ExtensionFilter("PNG Images", "*.PNG"),
        new FileChooser.ExtensionFilter("GIF Images", "*.GIF"),
        new FileChooser.ExtensionFilter("BMP Images", "*.BMP")
    );
  }

  /*
   * Changes the state image in the state fill maintainer then updates the board
   */
  private void changeStateImage(int stateNumber, String imagePath) {
    StateFillMaintainer stateFillMaintainer = stateColorAndImageChanges.getGameView().
        getStateFillMaintainer();
    stateFillMaintainer.changeStateImage(stateNumber, imagePath);
    stateColorAndImageChanges.getGameView().getDisplayBoard().updateDisplay();
  }

}
