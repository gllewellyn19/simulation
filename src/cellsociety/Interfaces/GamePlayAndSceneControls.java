package cellsociety.Interfaces;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface GamePlayAndSceneControls {

  void pause();
  void unpause();
  void slowDownOrSpeedUpAnimation(double multiplier);
  void stepIgnoringIfPaused();
  void setScene(Scene scene);
  Stage getStage();
  Scene getScene();

}
