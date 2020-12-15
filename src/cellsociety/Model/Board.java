package cellsociety.Model;

import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Model.GameCells.Cell;
import cellsociety.Model.GameCells.GameCellFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Board {


  public static final char DEFAULT_SEPARATOR = ',';
  public static final String EXCEL_EXTENSION = ".csv";
  private final BoardStructure currBoard;
  private final BoardStructure prevBoard;
  private double gameThreshold;

  /**
   * @param fileInfo - property file of the wanted simulation
   * @param gameType - simulation type
   * @param threshold - simulation threshold
   * @param numberOfStates - simulation number of states
   */
  /*
   * Threshold can be negative which means no threshold
   */
  public Board(String fileInfo, String gameType, String threshold, int numberOfStates, String neighborType, String edgeType) {
    DataReader dataReader = new DataReader();
    int[][] stateArray = dataReader.placeBoard(fileInfo, numberOfStates);
    GameCellFactory gameCellFactory = new GameCellFactory();
    setGameThreshold(threshold);
    currBoard = new BoardStructure(stateArray, gameType, gameCellFactory, gameThreshold,
        numberOfStates, neighborType, edgeType);
    prevBoard = new BoardStructure(stateArray, gameType, gameCellFactory, gameThreshold,
        numberOfStates, neighborType, edgeType);
  }

  /**
   * Change Board generation in the simulation to the next available one.
   */
  public void changeBoardGeneration() {
    cloneBoardOver(prevBoard, currBoard);
    for (int i = 0; i < currBoard.getRows(); i++) {
      for (int j = 0; j < currBoard.getCols(); j++) {
        currBoard.getCurrCell(i, j).nextGenerationRules(i, j, prevBoard, currBoard);
      }
    }
  }

  /**
   * @param newBoard - new Board
   * @param cloningBoard - board structure that will be the clone of newBoard
   */
  public void cloneBoardOver(BoardStructure newBoard, BoardStructure cloningBoard) {
    for (int i = 0; i < newBoard.getRows(); i++) {
      for (int j = 0; j < newBoard.getCols(); j++) {
        newBoard.getCurrCell(i, j).setState(cloningBoard.getCurrCell(i, j).getState());
      }
    }
  }

  /**
   * @return  - current simulation board
   */
  public BoardStructure getCurrBoard() {
    return currBoard;
  }

  /**
   * Exports the current board to a csv and saves it at the given file path
   * @param whereToSave - file path indicating where to save csv file
   */
  public void exportCurrentBoardAsCSV(String whereToSave) {
    FileWriter writer;
    try {
      writer = new FileWriter(whereToSave + EXCEL_EXTENSION);
      addFirstLine(writer);
      for (Cell[] line : currBoard.getCurrBoardStructure()) {
        writeLine(writer, line);
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new ModelException("exportCurrentBoardException", e);
    }
  }

  /*
   * Adds the first line to the csv that is the number of rows and columns (in that order)
   */
  private void addFirstLine(Writer w) throws IOException {
    String headerRow =
        String.valueOf(currBoard.getRows()) + DEFAULT_SEPARATOR + currBoard.getCols() + "\n";
    w.append(headerRow);
  }

  /*
   * Writes a line of cells out to the writer to write to the CSV
   */
  private void writeLine(Writer w, Cell[] values) throws IOException {
    boolean first = true;
    StringBuilder sb = new StringBuilder();
    for (Cell c : values) {
      if (!first) {
        sb.append(DEFAULT_SEPARATOR);
      }
      sb.append(c.getState());
      first = false;
    }
    sb.append("\n");
    w.append(sb.toString());
  }

  /**
   * Sets the game threshold to -1 if no threshold or to the given double if applicable (throws
   *  exception if not given number)
   * @param threshold - simulation threshold
   */
  public void setGameThreshold(String threshold) {
    if (threshold == null) {
      gameThreshold = -1;
    } else {
      checkAndSetThreshold(threshold);
    }
  }

  /*
   * Sets the game threshold if its a number and greater than 0 (throws exception if not)
   */
  private void checkAndSetThreshold(String threshold) {
    try {
      double newThreshold = Double.parseDouble(threshold);
      if (newThreshold < 0) {
        throw new InvalidPropertyException("Threshold");
      }
      gameThreshold = newThreshold;
    } catch (NumberFormatException e) {
      throw new InvalidPropertyException("Threshold");
    }
  }

}

