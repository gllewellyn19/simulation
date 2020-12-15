package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;
import java.util.Random;

/**
 * Randomly chooses another cell in the board to be a neighbor if requested row,col is outside bounds
 */
public class ToroidalRandom extends Edge {
  private final Random randomGen = new Random();

  @Override
  public int checkEdgePolicyRow(int i,int rowMove, BoardStructure oldBoard) {
    if (checkRowWithinBoundaries(i + rowMove,oldBoard))
      return i + rowMove;
    i = randomGen.nextInt(oldBoard.getRows());
    return i;
  }

  @Override
  public int checkEdgePolicyCol(int j, int colMove,BoardStructure oldBoard) {
    if (checkRowWithinBoundaries(j + colMove,oldBoard))
      return j + colMove;
    j = randomGen.nextInt(oldBoard.getCols());
    return j;
  }
}
