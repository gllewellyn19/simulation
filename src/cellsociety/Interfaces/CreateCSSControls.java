package cellsociety.Interfaces;

import java.util.Optional;
import javafx.scene.Scene;

public interface CreateCSSControls {

  Optional<Scene> makeASceneWithInitialCSS();
  Optional<Scene> changeCSS(String cssFile);

}
