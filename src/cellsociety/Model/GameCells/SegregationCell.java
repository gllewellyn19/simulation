package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;

public class SegregationCell extends Cell {
  public static final int STATE_OPEN_INDEX = 0;
  public static final int STATE_FIRST_GROUP_INDEX = 1;
  public static final int STATE_SECOND_GROUP_INDEX = 2;

  private int openState;
  private int firstGroupState;
  private int secondGroupState;
  private final int[] thisGameStates;
  private final double threshold;

  public SegregationCell(int i, int j,double gameThreshold, int numberOfStates,String neighborType,String edgeType) {
    super(i, j,numberOfStates,neighborType,edgeType);
    thisGameStates = getThisGamesStates();
    setThisGamesStates();
    threshold = gameThreshold;
  }

  /**
   * Implements the game of life rules
   */
  @Override
  public void nextGenerationRules(int i, int j, BoardStructure oldBoard, BoardStructure currBoard) {
    if (currBoard.getCurrCell(i,j).getState() == openState)
      return;
    double firstGroupNeighbors = findAllCellNeighbors(i,j,oldBoard,firstGroupState);
    double secondGroupNeighbors = findAllCellNeighbors(i,j,oldBoard,secondGroupState);
    double totalAliveNeighbors = firstGroupNeighbors + secondGroupNeighbors;
    double sameStateNeighbors;
    if (getState() == firstGroupState)
      sameStateNeighbors = firstGroupNeighbors;
    else
      sameStateNeighbors = secondGroupNeighbors;
    if (sameStateNeighbors / totalAliveNeighbors < threshold)
      moveToOpenSpace(i,j,oldBoard,currBoard);
  }


  private void moveToOpenSpace(int i, int j, BoardStructure oldBoard, BoardStructure currBoard) {
    while (currBoard.getCurrCell(i,j).getState() != openState) {
      i = super.generateRandomNumber(oldBoard.getRows());
      j = super.generateRandomNumber(oldBoard.getCols());
    }
    currBoard.getCurrCell(i,j).setState(getState());
    setState(openState);
  }

  @Override
  public void setThisGamesStates() {
    openState = thisGameStates[STATE_OPEN_INDEX];
    firstGroupState = thisGameStates[STATE_FIRST_GROUP_INDEX];
    secondGroupState = thisGameStates[STATE_SECOND_GROUP_INDEX];
  }

  @Override
  public void changeStateWhenClicked() {
    changeStateOfClickedCell(thisGameStates.length);
  }


}
