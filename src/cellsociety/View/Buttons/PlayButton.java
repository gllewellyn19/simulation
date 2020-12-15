package cellsociety.View.Buttons;

import cellsociety.Interfaces.AccessToOtherButtons;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * This class implements the play button which plays the game and disables the play and next
 * generation and enables the pause button and the speed slider
 *
 * @author Grace Llewellyn
 */
public class PlayButton extends BoardButton {

  private final AccessToOtherButtons accessToOtherButtons;

  public PlayButton(ResourceBundle resources,
      GamePlayAndSceneControls gamePlayAndSceneControls, AccessToOtherButtons accessToOtherButtons) {
    super(resources, new Button(), gamePlayAndSceneControls, "PlayButton");
    this.accessToOtherButtons = accessToOtherButtons;
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    super.getCurrButton().setOnAction(event -> onPlayClick());
  }


  /*
   * Disables the next generation and play button, and makes the pause button enabled
   * and pauses the game and enables the speed slider
   */
  private void onPlayClick() {
    accessToOtherButtons.getButton("PauseButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("NextGenerationButton").getCurrInteractiveFeature().
        setDisable(true);
    accessToOtherButtons.getButton("SpeedSlider").getCurrInteractiveFeature().
        setDisable(false);
    super.getCurrButton().setDisable(true);
    super.getGamePlayAndSceneControls().unpause();
  }
}
