package cellsociety.Model;

import cellsociety.Model.GameCells.Cell;
import cellsociety.Model.GameCells.GameCellFactory;

public class BoardStructure {
  private final Cell[][] currBoardStructure;
  private final GameCellFactory gameCellFactory;
  private final int rows;
  private final int cols;

  public BoardStructure(int[][] stateArray, String gameType,GameCellFactory cellFactory,
      double threshold,int numberOfStates,String neighborType,String edgeType) {
    gameCellFactory = cellFactory;
    rows = stateArray.length;
    cols = stateArray[0].length;
    currBoardStructure = new Cell[rows][cols];
    initializeBoard(stateArray,gameType,threshold,numberOfStates,neighborType,edgeType);
  }

  /**
   * @return number of rows of current board
   */
  public int getRows() {
    return rows;
  }

  /**
   * @return number of columns of current board
   */
  public int getCols() {
    return cols;
  }

  /*
   * Initializes the board using reflection to see the board type
   */
  private void initializeBoard(int[][] newStates,String gameType,double threshold,
      int numberOfStates,String neighborType,String edgeType) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        currBoardStructure[i][j] = gameCellFactory.getTheCurrentGameType(gameType,i,j,threshold,numberOfStates,neighborType,edgeType);
        currBoardStructure[i][j].setState(newStates[i][j]);
      }
    }
  }

  /**
   * @param i - row number
   * @param j -  column number
   * @return cell in place i,j of current board structure
   */
  public Cell getCurrCell(int i, int j) {
    return currBoardStructure[i][j];
  }

  /**
   * @return current board structure
   */
  public Cell[][] getCurrBoardStructure() {
    return currBoardStructure;
  }


}
