package cellsociety.View.Buttons;

import cellsociety.Interfaces.CreateCSSControls;
import cellsociety.Interfaces.ErrorPrintingControls;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.Model.Exceptions.ModelException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * This class implements the launch game button which just starts the game after the splash screen
 *
 * @author Grace Llewellyn
 */
public class LaunchGameButton extends BoardButton {

  private final CreateCSSControls createCSSControls;
  private final ErrorPrintingControls errorPrintingControls;

  public LaunchGameButton(ResourceBundle resources,
      GamePlayAndSceneControls gamePlayAndSceneControls, CreateCSSControls createCSSControls,
      ErrorPrintingControls errorPrintingControls) {
    super(resources, new Button(), gamePlayAndSceneControls, "LaunchGameButton");
    this.createCSSControls = createCSSControls;
    this.errorPrintingControls = errorPrintingControls;
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    super.getCurrButton().setOnAction(event -> onLaunchClick());
  }


  /*
   * Starts the simulation game after the language was chosen
   */
  private void onLaunchClick() {
    try {
      Optional<Scene> scene = createCSSControls.makeASceneWithInitialCSS();
      scene.ifPresent(value -> super.getGamePlayAndSceneControls().setScene(value));
    } catch (ModelException e) {
      errorPrintingControls.printErrorMessageAlert(e.getMessage());
      throw new ModelException(e.getMessage(),e);
    }
  }

}
