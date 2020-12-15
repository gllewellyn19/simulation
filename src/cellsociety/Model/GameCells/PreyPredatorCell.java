package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;
import java.util.ArrayList;
import java.util.List;

public class PreyPredatorCell extends Cell {
  public static final int INDEX_OFFSET = 1;
  public static final int STATE_OPEN_INDEX = 0;
  public static final int STATE_PREY_INDEX = 1;
  public static final int STATE_PREDATOR_INDEX = 2;
  public static final String NORTH_DIRECTION = "North";
  public static final String EAST_DIRECTION = "East";
  public static final String SOUTH_DIRECTION = "South";
  public static final String WEST_DIRECTION = "West";

  private int openState;
  private int preyState;
  private int predatorState;
  private final int[] thisGameStates;


  public PreyPredatorCell(int i, int j, int numberOfStates,String neighborType,String edgeType) {
    super(i, j,numberOfStates,neighborType,edgeType);
    thisGameStates = getThisGamesStates();
    setThisGamesStates();
  }

  @Override
  public void nextGenerationRules(int i, int j, BoardStructure oldBoard,
      BoardStructure currBoard) {
    if (getState() == openState || oldBoard.getCurrCell(i,j).getState() != currBoard.getCurrCell(i,j).getState())
      return;
    List<String> openMoves = new ArrayList<>();
    if (checkNeighbors(i, j, currBoard, openMoves)) {
      return;
    }
    String nextMoveDirection = openMoves.get(super.generateRandomNumber(openMoves.size()));
    moveToNextState(i,j,currBoard,nextMoveDirection);
  }

  /*
   * Checks all neighbors of the cell to decide if should return
   */
  private boolean checkNeighbors(int i, int j, BoardStructure currBoard, List<String> openMoves) {
    if (checkOpenNeighbor(i, j,ROW_UP,NO_MOVE, currBoard))
      openMoves.add(NORTH_DIRECTION);
    if (checkOpenNeighbor(i, j, ROW_DOWN,NO_MOVE, currBoard))
      openMoves.add(SOUTH_DIRECTION);
    if (checkOpenNeighbor(i, j, NO_MOVE,COL_RIGHT, currBoard))
      openMoves.add(EAST_DIRECTION);
    if (checkOpenNeighbor(i, j, NO_MOVE,COL_LEFT, currBoard))
      openMoves.add(WEST_DIRECTION);
    return openMoves.size() == 0;
  }

  //Method is not that complex (design coach)
  private void moveToNextState(int i, int j, BoardStructure currBoard, String direction) {
    switch (direction) {
        case NORTH_DIRECTION -> i -= INDEX_OFFSET;
        case SOUTH_DIRECTION -> i += INDEX_OFFSET;
        case EAST_DIRECTION -> j += INDEX_OFFSET;
        case WEST_DIRECTION -> j -= INDEX_OFFSET;
    }
    currBoard.getCurrCell(i,j).setState(getState());
    setState(openState);
  }

  private boolean checkOpenNeighbor(int i, int j, int rowMove, int colMove, BoardStructure currBoard) {
    return (determineIfNeighborOpen(i, j, rowMove, colMove, currBoard)) || (getState() == preyState
        && findSingularCellNeighbor(i, j, rowMove, colMove, currBoard, openState) == 1);
  }

  private boolean determineIfNeighborOpen(int i, int j, int rowMove, int colMove,
      BoardStructure currBoard) {
    return getState() == predatorState && (
        findSingularCellNeighbor(i, j, rowMove, colMove, currBoard, preyState) == 1 ||
            findSingularCellNeighbor(i, j, rowMove, colMove, currBoard, openState) == 1);
  }

  @Override
  public void setThisGamesStates() {
    openState = thisGameStates[STATE_OPEN_INDEX];
    preyState = thisGameStates[STATE_PREY_INDEX];
    predatorState = thisGameStates[STATE_PREDATOR_INDEX];
  }

  @Override
  public void changeStateWhenClicked() {
    changeStateOfClickedCell(thisGameStates.length);
  }


}

