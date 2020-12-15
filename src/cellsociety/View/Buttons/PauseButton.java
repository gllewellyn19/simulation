package cellsociety.View.Buttons;

import cellsociety.Interfaces.AccessToOtherButtons;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * This class implements the pause button which pauses the game and enables the play and next
 * generation buttons and disables itself and the speed slider
 *
 * @author Grace Llewellyn
 */
public class PauseButton extends BoardButton {

  private final AccessToOtherButtons accessToOtherButtons;

  public PauseButton(ResourceBundle resources,
      GamePlayAndSceneControls gamePlayAndSceneControls, AccessToOtherButtons accessToOtherButtons) {
    super(resources, new Button(), gamePlayAndSceneControls, "PauseButton");
    this.accessToOtherButtons = accessToOtherButtons;
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    super.getCurrButton().setOnAction(event -> onPauseClick());
  }

  /*
   * When pause is clicks, disables pause and enables next generation and play buttons. Also pauses
   * the game and disables the speed slider
   */
  private void onPauseClick() {
    accessToOtherButtons.getButton("PlayButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("NextGenerationButton").getCurrInteractiveFeature().
        setDisable(false);
    accessToOtherButtons.getButton("SpeedSlider").getCurrInteractiveFeature().
        setDisable(true);
    super.getCurrButton().setDisable(true);
    super.getGamePlayAndSceneControls().pause();
  }
}
