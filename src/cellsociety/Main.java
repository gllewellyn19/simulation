package cellsociety;

import cellsociety.Controller.Controller;
import cellsociety.Interfaces.GamePlayAndSceneControls;
import cellsociety.View.GameView;
import java.awt.Dimension;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements GamePlayAndSceneControls {

  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  public static final String DEFAULT_TITLE = "Simulation Game";
  public static final double ONE_SECOND_DELAY = 1;
  public static final double CURRENT_ANIMATION_SPEED_FACTOR = 1.3;


  private boolean pause = true;
  private Stage stage;
  private Scene scene;
  private Timeline animation;
  private Controller gameController;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    gameController = new Controller(this);
    GameView gameView = gameController.getGameView();
    Optional<Scene> sceneTemp = gameView.makeAnInitialScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    if (sceneTemp.isPresent()) {
      scene = sceneTemp.get();
      stage.setScene(scene);
      stage.setTitle(DEFAULT_TITLE);
      stage.show();
      setUpAnimation();
      this.stage = stage;
    }
  }

  /*
   * Creates the animation for the game
   */
  private void setUpAnimation() {
    KeyFrame frame = new KeyFrame(Duration.seconds(ONE_SECOND_DELAY), e -> step());
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /*
   * Steps through the function but only if the game is not paused and the set up was successful
   */
  private void step() {
    if (!pause && gameController.getSetupSuccessful()) {
      gameController.updateBoardForStep();
    }
  }

  public void stepIgnoringIfPaused() {
    gameController.updateBoardForStep();
  }

  public void pause() {
    pause = true;
  }

  public void unpause() {
    pause = false;
  }

  /*
   * Speeds up or slows down the current animation rate by the given multiplier
   */
  public void slowDownOrSpeedUpAnimation(double modifyingRate) {
    animation.setRate(Math.pow(CURRENT_ANIMATION_SPEED_FACTOR,modifyingRate));
  }

  //for testing
  public double getAnimationRate() {
    return animation.getCurrentRate();
  }

  public Stage getStage() {
    return stage;
  }

  public Scene getScene() {
    return scene;
  }

  //Methods only given access through interface
  public void setScene(Scene scene) {
    this.scene = scene;
    stage.setScene(scene);
  }

  //for testing- pass interface for testing
  public Controller getGameController() {
    return gameController;
  }

}
