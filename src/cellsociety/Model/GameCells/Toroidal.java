package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;

public class Toroidal extends Edge {

  @Override
  public int checkEdgePolicyRow(int i,int rowMove,BoardStructure oldBoard) {
    if (checkRowWithinBoundaries(i + rowMove,oldBoard))
      return i + rowMove;
    else if (i + rowMove < 0)
      i = oldBoard.getRows() - 1;
    else
      i = 0;
    return i;
  }

  @Override
  public int checkEdgePolicyCol(int j, int colMove,BoardStructure oldBoard) {
    if (checkRowWithinBoundaries(j + colMove,oldBoard))
      return j + colMove;
    else if (j + colMove < 0)
      j = oldBoard.getCols() - 1;
    else
      j = 0;
    return j;
  }
}
