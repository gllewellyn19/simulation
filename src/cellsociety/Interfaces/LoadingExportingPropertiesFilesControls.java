package cellsociety.Interfaces;

import cellsociety.View.GameView;

public interface LoadingExportingPropertiesFilesControls {

  void exportBoardAsCSV(String filePathForCSV);
  int getNumberOfStates();
  String getThreshold();
  String getGameType();
  GameView getGameView();
  void uploadPropertiesFile(String propertiesFilePath);
  boolean getSetupSuccessful();
  void uploadStylePropertiesFile(String propertiesFilePath);
  String getEdgeType();
  String getNeighborType();

}
