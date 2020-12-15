package cellsociety.View.Boards;

import cellsociety.Model.Exceptions.ModelException;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.shape.Shape;

public class CellPaneStructure {
  public static final double GRID_SIZE_FACTOR = .75;
  Shape[][] gridCells;

  /**
   * @param cellType - type of simulation cell
   * @param rows -  number of rows of csv file provided
   * @param cols - number of columns of csv file provided
   * @param width - width of grid where simulation happens
   * @param height - height of grid where simulation happens
   */
  public CellPaneStructure(String cellType, int rows, int cols, int width, int height) {
    double rectWidth = (width * GRID_SIZE_FACTOR / rows);
    double rectHeight = (height * GRID_SIZE_FACTOR  / cols);
    gridCells = new Shape[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        gridCells[i][j] = getTheCurrentGameType(cellType,rectWidth,rectHeight);
      }
    }
  }


  /**
   * Uses reflection to figure out which shape the game is going to use
   *
   * @param cellShapeType - ex: hexagon, square, triangle
   * @param width - width of  cell
   * @param height - height of cell
   * @return Shape of cell
   */
  public Shape getTheCurrentGameType(String cellShapeType, double width, double height)
  {
    try {
      Class currentGameTypeClass = Class.forName("javafx.scene.shape." + cellShapeType);
      return (Shape) currentGameTypeClass.getDeclaredConstructor(double.class,double.class).newInstance(width,height);
    }
    catch (NoSuchMethodException | IllegalAccessException | InstantiationException | IllegalStateException | InvocationTargetException | ClassNotFoundException e) {
      throw new ModelException("getTheCurrentShapeTypeException",e);
    }
  }

  /**
   * @param i - row number i
   * @param j -  column number j
   * @return - shape of cell on those coordinates
   */
  public Shape getCellPane(int i, int j) {
    return gridCells[i][j];
  }

  /**
   * @return - shape array (with our cells' coordinates) of our grid
   */
  public Shape[][] getGridCells() {
    return gridCells;
  }
}
