package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;

public class GameOfLifeCell extends Cell {
  public static final int LONELY_NEIGHBOR_COUNT = 1;
  public static final int GOOD_TIMES_NEIGHBOR_COUNT = 3;
  public static final int OVERCROWDED_NEIGHBOR_COUNT = 4;
  public static final int STATE_ON_INDEX = 1;
  public static final int STATE_OFF_INDEX = 0;
  private int deadState;
  private int aliveState;
  private final int[] thisGameStates;

  public GameOfLifeCell(int i, int j, int numberOfStates,String neighborType,String edgeType) {
    super(i, j,numberOfStates,neighborType,edgeType);
    thisGameStates = getThisGamesStates();
    setThisGamesStates();
  }

  @Override
  public void setThisGamesStates() {
    deadState = thisGameStates[STATE_OFF_INDEX];
    aliveState = thisGameStates[STATE_ON_INDEX];
  }

  /**
   * Implements the game of life rules
   */
  //method is not that complex (design coach)
  @Override
  public void nextGenerationRules(int i, int j, BoardStructure oldBoard,
      BoardStructure currBoard) {
    int neighborCount = findAllCellNeighbors(i, j, oldBoard,aliveState);
    if (neighborCount <= LONELY_NEIGHBOR_COUNT || neighborCount >= OVERCROWDED_NEIGHBOR_COUNT &&
        getState() == aliveState) {
      setState(deadState);
    }
    else if (getState() == deadState && neighborCount == GOOD_TIMES_NEIGHBOR_COUNT) {
      setState(aliveState);
    }
  }

  @Override
  public void changeStateWhenClicked() {
    changeStateOfClickedCell(thisGameStates.length);
  }


}
