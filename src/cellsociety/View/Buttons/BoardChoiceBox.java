package cellsociety.View.Buttons;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

public abstract class BoardChoiceBox extends BoardInteractiveFeature {

  private final ChoiceBox<String> currButton;

  public BoardChoiceBox(ResourceBundle resources, ChoiceBox<String> b){
    super(resources);
    currButton = b;
    initializeThisButton();
  }

  /**
   * Initialize button functionality
   */
  public abstract void initializeThisButton();

  /**
   * @param id - sets ID of the button this new ID
   */
  public void setID(String id) {
    currButton.setId(id);
  }

  public Node getCurrInteractiveFeature() {
    return currButton;
  }

  /**
   * @return ChoiceBox<String> button
   */
  public ChoiceBox<String> getCurrButton() { return currButton; }

  /**
   * @return value string of the button you want
   */
  public String getValue() {
    return currButton.getValue();
  }

}
