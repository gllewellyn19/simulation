package cellsociety.View.Buttons;

import java.util.ResourceBundle;
import javafx.scene.Node;

public abstract class BoardInteractiveFeature {

  private ResourceBundle resources;

  public BoardInteractiveFeature(ResourceBundle resources) {
    this.resources = resources;
  }

  /**
   * @return - ResourceBundle object
   */
  public ResourceBundle getResources() {
    return resources;
  }

  /**
   * @param newLanguageResources - name of ResourceBundle of new specified language
   */
  public void updateLanguage(ResourceBundle newLanguageResources) {
    this.resources = newLanguageResources;
    updateLanguageOnFeature();
  }

  public abstract void updateLanguageOnFeature();

  public abstract Node getCurrInteractiveFeature();

}
