package cellsociety.Model.GameCells;

import cellsociety.Model.BoardStructure;

public abstract class Neighbor {
  public static final int ROW_UP = -1;
  public static final int ROW_DOWN = 1;
  public static final int COL_LEFT = -1;
  public static final int COL_RIGHT = 1;
  public static final int NO_MOVE = 0;

  private final Edge edgeType;

  public Neighbor(String edge) {
    EdgeFactory edgeFactory = new EdgeFactory();
    edgeType = edgeFactory.getTheCurrentEdgeType(edge);
    setNeighborIndices();
  }

  /**
   * Checks the state of requested neighbor. If requested neighbor is not apart of neighbor configuration, does nothing
   */
  public int findSingularCellNeighbor(int i, int j, int rowMove, int colMove,BoardStructure oldBoard,int state) {
    return findNeighborStateInt(i,j,rowMove,colMove,oldBoard,state);
  }

  /**
   * Useful method if CA simulation wants to easily check all neighbors
   */
  public int findAllCellNeighbors(int i, int j, BoardStructure oldBoard,int state) {
    int neighborCount = 0;
    neighborCount += findNeighborStateInt(i , j ,ROW_UP,COL_LEFT, oldBoard,state);
    neighborCount += findNeighborStateInt(i , j,ROW_UP,COL_RIGHT, oldBoard,state);
    neighborCount += findNeighborStateInt(i , j,ROW_DOWN,COL_LEFT, oldBoard,state);
    neighborCount += findNeighborStateInt(i , j,ROW_DOWN,COL_RIGHT, oldBoard,state);
    neighborCount += findCrossCellNeighbors(i,j,oldBoard,state);
    return neighborCount;
  }

  /**
   * Useful method if CA simulation wants to easily check only four direction neighbors
   */
  public int findCrossCellNeighbors(int i, int j, BoardStructure oldBoard,int state) {
    int neighborCount = 0;
    neighborCount += findNeighborStateInt(i , j,ROW_UP,NO_MOVE, oldBoard,state);
    neighborCount += findNeighborStateInt(i, j,NO_MOVE,COL_LEFT, oldBoard,state);
    neighborCount += findNeighborStateInt(i, j,NO_MOVE,COL_RIGHT, oldBoard,state);
    neighborCount += findNeighborStateInt(i , j, ROW_DOWN,NO_MOVE,oldBoard,state);
    return neighborCount;
  }

  /**
   * Returns 1 if the requested neighbor is valid and is the sent in state
   */
  public int findNeighborStateInt(int i, int j,int rowMove, int colMove, BoardStructure oldBoard, int state) {
    if (!checkIfValidIndices(rowMove,colMove))
      return 0;
    i = edgeType.checkEdgePolicyRow(i,rowMove,oldBoard);
    j = edgeType.checkEdgePolicyCol(j,colMove,oldBoard);
    if(checkOldBoard(oldBoard,i,j) || checkOldBoardState(oldBoard,i,j,state)){
      return 0;
    }
    return 1;
  }

  private boolean checkOldBoard(BoardStructure oldBoard, int i,int j){
    return (i < 0|| i >= oldBoard.getRows() || j < 0||j >= oldBoard.getCols());
  }

  private boolean checkOldBoardState(BoardStructure oldBoard, int i,int j, int state){
    return oldBoard.getCurrCell(i,j).getState() != state;
  }


  public abstract void setNeighborIndices();

  public abstract boolean checkIfValidIndices(int rowMove,int colMove);
  
}
