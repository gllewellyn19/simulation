package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;

public class Finite extends Edge {

  @Override
  public int checkEdgePolicyRow(int i, int rowMove,BoardStructure oldBoard) {
    return i + rowMove;
  }

  @Override
  public int checkEdgePolicyCol(int j, int colMove,BoardStructure oldBoard) {
    return j + colMove;
  }
}
