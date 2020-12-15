package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;

public abstract class Edge {

  public boolean checkRowWithinBoundaries(int i,BoardStructure oldBoard) {
    return (i >= 0 && i < oldBoard.getRows());
  }

  public boolean checkColWithinBoundaries(int j,BoardStructure oldBoard) {
    return  (j >= 0 && j < oldBoard.getCols());
  }

  public abstract int checkEdgePolicyRow(int i, int rowMove, BoardStructure oldBoard);
  public abstract int checkEdgePolicyCol(int j, int colMove, BoardStructure oldBoard);

}
