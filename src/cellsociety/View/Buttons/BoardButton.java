package cellsociety.View.Buttons;

import cellsociety.Interfaces.GamePlayAndSceneControls;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;


public abstract class BoardButton extends BoardInteractiveFeature {

  private final ButtonBase currButton;
  private final GamePlayAndSceneControls gamePlayAndSceneControls;
  private final String id;

  public BoardButton(ResourceBundle resources, ButtonBase b, GamePlayAndSceneControls
      gamePlayAndSceneControls, String id) {
    super(resources);
    this.gamePlayAndSceneControls = gamePlayAndSceneControls;
    currButton = b;
    this.id = id;
    initializeThisButton();
  }

  /**
   * initialize button by setting its text and ID
   */
  public void initializeThisButton() {
    setTextAndID();
  }

  /**
   * sets text and ID of the button
   */
  private void setTextAndID() {
    currButton.setText(super.getResources().getString(id));
    currButton.setId(id);
  }

  /*
   * Updates the resources for the button to reflect the new language and sets the text for the
   * button again
   */
  public void updateLanguageOnFeature() throws MissingResourceException {
    currButton.setText(super.getResources().getString(id));
  }

  /**
   * @return GamePlayAndSceneControls object
   */
  public GamePlayAndSceneControls getGamePlayAndSceneControls() {
    return gamePlayAndSceneControls;
  }

  /**
   * @return - current button
   */
  public ButtonBase getCurrButton() {
    return currButton;
  }

  /**
   * @return - Node representing interactive feature
   */
  public Node getCurrInteractiveFeature() {
    return currButton;
  }

  /**
   * @return - ID of the button you want
   */
  protected String getId() {
    return id;
  }
}
