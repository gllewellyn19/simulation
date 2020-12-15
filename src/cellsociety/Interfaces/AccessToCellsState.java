package cellsociety.Interfaces;

/**
 * Implements methods that give access to the current cell state
 */
public interface AccessToCellsState {

  int getCellState(int i, int j);

  void changeStateOfClickedCell(int i, int j);

}
