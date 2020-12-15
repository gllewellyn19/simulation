package cellsociety.View.Boards;

import cellsociety.Interfaces.AccessToCellsState;
import cellsociety.View.StateFillMaintainer;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class DisplayBoard {
  public static final int INITIAL_FILL_STATE = 0;
  public static final int VERTICAL_CELL_GAP = 2;
  public static final int HORIZONTAL_CELL_GAP = 2;
  public static final Color GRID_OUTLINE = Color.BLACK;
  public static final String DEFAULT_SHAPE = "Rectangle";

  private final GridPane gridPane;
  private final CellPaneStructure cellPaneStructure;
  private final StateFillMaintainer stateFillMaintainer;
  private final AccessToCellsState accessToCellsState;
  private final int rows;
  private final int cols;
  private boolean needOutline = true;

  /**
   * @param width - width of display board
   * @param height -  height of display board
   * @param rows -  number of rows in display board
   * @param cols - number of columns in display board
   * @param stateFillMaintainer - stateFillMaintainer object that takes care of color state
   * @param accessToCellsState - accessToCellsState interface object that contains getCellState method and
   *  changeStateOfClickedCell method
   */
  public DisplayBoard(int width, int height, int rows, int cols,
      StateFillMaintainer stateFillMaintainer, AccessToCellsState accessToCellsState) {
    this.accessToCellsState = accessToCellsState;
    this.rows = rows;
    this.cols = cols;
    gridPane = new GridPane();
    cellPaneStructure = new CellPaneStructure(DEFAULT_SHAPE,rows,cols,width,height);
    gridPane.setVgap(VERTICAL_CELL_GAP);
    gridPane.setHgap(HORIZONTAL_CELL_GAP);
    gridPane.setAlignment(Pos.CENTER);
    this.stateFillMaintainer = stateFillMaintainer;
    initializeDisplayBoard();
  }

  /*
   * Initializes the game board to be filled with shapes of the first index of the state fill
   * (know has to be at least one fill because can't have 0 state types)
   */
  public void initializeDisplayBoard() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        stateFillMaintainer.setFillOfCell(cellPaneStructure.getCellPane(i,j), INITIAL_FILL_STATE);
        gridPane.add(cellPaneStructure.getCellPane(i,j),i, j);
      }
    }
  }

  /*
   * Updates the display of the board based on the new states. Assumes that the states of the board
   * are within bounds of the state colors (should be the same amount of states)
   */
  public void updateDisplay() {
    gridPane.getChildren().clear();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        stateFillMaintainer.setFillOfCell(cellPaneStructure.getCellPane(i,j), accessToCellsState.getCellState(i,j));
        if (needOutline)
          cellPaneStructure.getCellPane(i,j).setStroke(GRID_OUTLINE);
        gridPane.add(cellPaneStructure.getCellPane(i,j),j,i);
      }
    }
  }

  /*
   * Changes the grid to have an outline if the boolean is true and to not have an outline if the
   * boolean is not true
   */
  public void changeGridOutline(boolean shouldEnableGridLines) {
    needOutline = shouldEnableGridLines;
  }

  /**
   * @return  GridPane object
   */
  public GridPane getDisplayBoard() {
    return gridPane;
  }

  /**
   * @return shape array of all of our cells
   */
  public Shape[][] getGridCells() {
    return cellPaneStructure.getGridCells();
  }
}
