package cellsociety.View.Buttons;

import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Interfaces.StateColorAndImageChanges;
import cellsociety.View.StateFillMaintainer;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

/**
 * This class implements the button to change the color of all cells of a certain state
 *
 * @author Grace Llewellyn
 */
public class ColorChangeButton extends BoardButton {

  private final StateColorAndImageChanges stateColorAndImageChanges;
  private final ErrorPrintingControls errorPrintingControls;

  public ColorChangeButton(ResourceBundle resources, GamePlayAndSceneControls
      gamePlayAndSceneControls, StateColorAndImageChanges stateColorAndImageChanges,
      ErrorPrintingControls errorPrintingControls) {
    super(resources, new Button(), gamePlayAndSceneControls, "ColorChangeButton");
    this.stateColorAndImageChanges = stateColorAndImageChanges;
    this.errorPrintingControls = errorPrintingControls;
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    super.getCurrButton().setOnAction(event -> onColorChangeClick());
  }


  /*
   * Pauses the game then gets the inputs from the user and changes the color in the state fill
   * maintainer if no errors
   */
  public void onColorChangeClick() {
    try {
      super.getGamePlayAndSceneControls().pause();
      String[] inputs = new String[2];
      boolean receivedBothInputs = getInputStateAndColor(inputs);
      if (receivedBothInputs) {
        changeStateColor(Integer.parseInt(inputs[0]), inputs[1]);
      }
    } catch (ModelException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage());
    } catch (InvalidPropertyException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage(), e.getPropertyInfo());
    }
  }

  /*
   * Gets the input state and color and puts it in the inputs array (states number at index 0 and
   * color at index 1). If the user presses cancel for the input dialog box, then nothing happens.
   * Checks to make sure input state is a number
   */
  private boolean getInputStateAndColor(String[] inputs) {
    List<String> messages = List.of(super.getResources().getString("EnterStateNumberMessage"),
        super.getResources().getString("EnterStateColorMessage"));
    for (int i = 0; i < inputs.length; i++) {
      TextInputDialog dialog = createDialogBox(messages.get(i));

      Optional<String> answer = dialog.showAndWait();
      if (answer.isEmpty()) {
        return false;
      }
      checkIfFirstInputIsNumber(i, answer.get());
      inputs[i] = answer.get();
    }
    return true;
  }

  /*
   * Checks to see if on the first input and makes sure it is a number if not throws an exception
   */
  private void checkIfFirstInputIsNumber(int i, String input) {
    if (i == 0) {
      try {
        Integer.parseInt(input);
      } catch (NumberFormatException e) {
        throw new ModelException("checkValidStateException");
      }
    }
  }

  /*
   * Creates dialog box with the corresponding message
   */
  private TextInputDialog createDialogBox(String message) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle(super.getResources().getString(super.getId()));
    dialog.setHeaderText(super.getResources().getString(super.getId()));
    dialog.setContentText(message);
    return dialog;
  }

  /*
   * Changes the state color in the state fill maintainer then updates the board
   */
  private void changeStateColor(int stateNumber, String newColor) {
    StateFillMaintainer stateFillMaintainer = stateColorAndImageChanges.getGameView().
        getStateFillMaintainer();
    stateFillMaintainer.changeStateColor(stateNumber, newColor);
    stateColorAndImageChanges.getGameView().getDisplayBoard().updateDisplay();
  }


}
