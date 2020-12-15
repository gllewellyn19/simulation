package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;
import java.util.Random;

/**
 * Abstract cell superclass
 */
public abstract class Cell {
  public static final int ROW_UP = -1;
  public static final int ROW_DOWN = 1;
  public static final int COL_LEFT = -1;
  public static final int COL_RIGHT = 1;
  public static final int NO_MOVE = 0;

  private int state;
  private int[] simulationStates;
  private final Random randomGenerator;
  private final Neighbor currNeighbors;

  /**
   * @param i - row number
   * @param j - column number
   * @param numberOfStates - number of states of simulation
   * @param neighborType - type of neighbor
   * @param edgeType - type of cell edge
   */
  public Cell(int i, int j, int numberOfStates, String neighborType,String edgeType) {
    NeighborFactory neighborFactory = new NeighborFactory();
    currNeighbors = neighborFactory.getTheCurrentNeighborType(neighborType,edgeType);
    setSimulationStates(numberOfStates);
    randomGenerator = new Random();
  }

  /**
   * @param i - row number
   * @param j - column number
   * @param oldBoard - prev Board
   * @param currBoard - current Board
   */
  public abstract void nextGenerationRules(int i, int j, BoardStructure oldBoard,
      BoardStructure currBoard);

  /**
   * set game state to specified states
   */
  public abstract void setThisGamesStates();

  /**
   * change game state of cell when clicked depending on available states for that simulation
   */
  public abstract void changeStateWhenClicked();

  public int findSingularCellNeighbor(int i, int j, int rowMove, int colMove,BoardStructure oldBoard,int state) {
    return currNeighbors.findSingularCellNeighbor(i,j,rowMove,colMove,oldBoard,state);
  }

  public int findAllCellNeighbors(int i, int j, BoardStructure oldBoard,int state) {
    return currNeighbors.findAllCellNeighbors(i,j,oldBoard,state);
  }

  public int findCrossCellNeighbors(int i, int j, BoardStructure oldBoard,int state) {
    return currNeighbors.findCrossCellNeighbors(i,j,oldBoard,state);
  }

  public void changeStateOfClickedCell(int numStates) {
    state++;
    if (state >= numStates)
      state = 0;
  }

  public void setSimulationStates(int numberOfStates) {
    simulationStates = new int[numberOfStates];
    for (int i = 0; i < numberOfStates; i++) {
      simulationStates[i] = i;
    }
  }

  public int generateRandomNumber(int upperbound) {
    return randomGenerator.nextInt(upperbound);
  }

  public void setState(int newState) {
    state = newState;
  }

  public int getState() {
    return state;
  }

  public int[] getThisGamesStates() {
    return simulationStates;
  }

}

