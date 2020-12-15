package cellsociety.View.Buttons;

import cellsociety.Interfaces.GamePlayAndSceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * This class implements the next generation button which just steps through the game and is only
 * enabled when the game is paused
 *
 * @author Grace Llewellyn
 */
public class NextGenerationButton extends BoardButton {
  public NextGenerationButton(ResourceBundle resources, GamePlayAndSceneControls
      gamePlayAndSceneControls) {
    super(resources, new Button(), gamePlayAndSceneControls, "NextGenerationButton");
  }

  @Override
  public void initializeThisButton() {
    super.initializeThisButton();
    super.getCurrButton().setOnAction(event -> getGamePlayAndSceneControls().stepIgnoringIfPaused());
  }

}
