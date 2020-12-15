package cellsociety.View.Buttons;

import cellsociety.Interfaces.LanguageControls;
import cellsociety.View.GameView;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ChoiceBox;

public class ChooseLanguageButton extends BoardChoiceBox {

  public static final List<String> POTENTIAL_LANGUAGES = List.of("English", "Spanish", "Portuguese");

  private final LanguageControls languageControls;

  /**
   * @param resources - ResourceBundle file with text with wanted language of buttons
   * @param languageControls - interface object with setLanguage and getLanguage methods
   */
  public ChooseLanguageButton(ResourceBundle resources, LanguageControls languageControls) {
    super(resources, new ChoiceBox<>());
    this.languageControls = languageControls;
  }


  /*
   * Initializing the text and id of the button from the resources bundle and sets the action of the
   * button to be uploading a new language
   */
  @Override
  public void initializeThisButton() {
    ChoiceBox<String> thisButton = super.getCurrButton();
    thisButton.setValue(GameView.DEFAULT_STARTING_LANGUAGE);
    super.setID("ChooseLanguageButton");
    thisButton.getItems().addAll(POTENTIAL_LANGUAGES);
    thisButton.setOnAction(
        e -> languageControls.setLanguage(thisButton.getValue()));
  }

  /*
   * This feature is only shown on the splash screen at the beginning, so the language does not need
   * to be changes for it
   */
  public void updateLanguageOnFeature() {
  }
}
