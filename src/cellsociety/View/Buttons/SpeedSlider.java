package cellsociety.View.Buttons;

import cellsociety.Interfaces.GamePlayAndSceneControls;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * This class implements the speed slider for the project that changes the speed based on its value
 *
 * @author Grace Llewellyn
 */
public class SpeedSlider extends BoardInteractiveFeature{

  public static final int SPEED_MIN = -2;
  public static final int SPEED_MAX = 8;
  public static final int SPEED_START = 0;
  public static final int SLIDER_INCREMENT = 1;
  public static final int NO_TICK = 0;
  public static final String ID = "SpeedSlider";

  private final Slider speedSlider;
  private VBox slider;

  public SpeedSlider(ResourceBundle resources, GamePlayAndSceneControls gamePlayAndSceneControls) {
    super(resources);
    slider = new VBox();
    speedSlider = new Slider(SPEED_MIN, SPEED_MAX, SPEED_START);
    speedSlider.setShowTickMarks(true);
    speedSlider.setShowTickLabels(true);
    speedSlider.setSnapToTicks(true);
    speedSlider.setMajorTickUnit(SLIDER_INCREMENT);
    speedSlider.setMinorTickCount(NO_TICK);
    speedSlider.setBlockIncrement(SLIDER_INCREMENT);
    speedSlider.setId(ID);
    speedSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        gamePlayAndSceneControls.slowDownOrSpeedUpAnimation(newValue.doubleValue()));
    Label speedLabel = new Label(super.getResources().getString(ID));
    slider.getChildren().addAll(speedLabel, speedSlider);
  }

  public Node getCurrInteractiveFeature() {
    return speedSlider;
  }


  @Override
  public void updateLanguageOnFeature() {
    slider = new VBox();
    Label speedLabel = new Label(super.getResources().getString(ID));
    slider.getChildren().addAll(speedLabel, speedSlider);
  }
}
