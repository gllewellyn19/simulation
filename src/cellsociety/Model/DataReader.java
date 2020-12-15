package cellsociety.Model;

import cellsociety.Controller.Controller;
import cellsociety.Model.Exceptions.MissingFilePathException;
import cellsociety.Model.Exceptions.ModelException;
import cellsociety.Model.Exceptions.StartingConfigurationException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

/**
 * Class that takes care of reading data from outside files and can handle other starting
 * configurations like Random and Checkered
 *
 * @author Grace Llewellyn and Luisa Silva
 */
public class DataReader {

  InputStream data;

  /**
   * @param filePath - file path of the csv file
   * @return - List<String[]> of all the elements in the csv file
   * @throws ModelException
   */
  /*
   * Attempt to read the csv and throw errors if cannot open/ read from it
   */
  public List<String[]> readAll(String filePath) throws ModelException {
    try {
      data = new FileInputStream(filePath);
      CSVReader csvReader = new CSVReader(new InputStreamReader(data));
      return csvReader.readAll();
    }
    catch (CsvException | IOException e) {
      throw new MissingFilePathException("readAllException",filePath,e);
    }
  }


  /**
   * @param fileInfo - csv file path
   * @param numberOfStates - number of states of the simulation
   * @return - int[][] structure of the csv data
   * @throws ModelException thrown for numerous different reasons such as the configuration of the
   * csv file being in correct, etc.
   */
  /*
   * Creates the board and returns it as a matrix. Throws exceptions if invalid csv or invalid
   * type of initial configuration. If else ladder because need to change this code anyway if adds
   * new supporting type
   */
  public int[][] placeBoard(String fileInfo, int numberOfStates) throws ModelException {
      if (fileInfo.endsWith(Controller.CSV_EXTENSION) ||
          fileInfo.endsWith(Controller.CSV_EXTENSION + "/")) {
        return placeBoardFromCSV(fileInfo, numberOfStates);
      } else if (fileInfo.startsWith("Random")) {
        return placeBoardRandom(fileInfo, numberOfStates);
      } else if (fileInfo.startsWith("Checkered")) {
        return placeBoardCheckered(fileInfo, numberOfStates);
      } else {
        throw new StartingConfigurationException("invalidStartType");
      }
  }

  /*
   * Places the board by oscillating between each state
   */
  private int[][] placeBoardCheckered(String fileInfo, int numberOfStates) {
    int[] rowsAndCols = getRowsAndColsFromProperty(fileInfo);
    int[][] boardStates = new int[rowsAndCols[0]][rowsAndCols[1]];
    int count = 0;
    for (int i=0; i< boardStates.length; i++) {
      for (int j=0; j< boardStates[0].length; j++) {
        count = setBoardStateBasedOnCounter(numberOfStates, boardStates, count, i, j);
      }
    }
    return boardStates;
  }

  /*
   * Uses a counter to determine which state the given index should be since oscillates between
   * the values of the states
   */
  private int setBoardStateBasedOnCounter(int numberOfStates, int[][] boardStates, int count, int i,
      int j) {
    boardStates[i][j] = count;
    count++;
    if (count >= numberOfStates) {
      count =0;
    }
    return count;
  }

  /*
   * Places the board by randomly generating states for each index
   */
  private int[][] placeBoardRandom(String fileInfo, int numberOfStates) {
    int[] rowsAndCols = getRowsAndColsFromProperty(fileInfo);
    int[][] boardStates = new int[rowsAndCols[0]][rowsAndCols[1]];
    Random rand = new Random();
    for (int i=0; i< boardStates.length; i++) {
      for (int j=0; j< boardStates[0].length; j++) {
        boardStates[i][j] = rand.nextInt(numberOfStates);
      }
    }
    return boardStates;
  }

  /*
   * Gets the number of rows and columns from the property file because follows the colon. Throws
   * an error if not a number and stores the row at index 0 and column at index 1
   * Rows and Cols are stores after colon with format :row,col
   */
  private int[] getRowsAndColsFromProperty(String fileInfo) {
    int[] rowsAndCols = new int[2];
    int indexOfColon = getIndexOfPunctuation(fileInfo, ":",
        "invalidFormatStartingKeyColon");
    String rowsAndColsTogether = fileInfo.substring(indexOfColon + 1);
    int indexOfComma = getIndexOfPunctuation(rowsAndColsTogether, ",",
        "invalidFormatStartingKeyComma");
    extractRowOrColValue(rowsAndCols, 0, rowsAndColsTogether.substring(0, indexOfComma),
        "nonNumberRows");
    extractRowOrColValue(rowsAndCols, 1, rowsAndColsTogether.substring(indexOfComma + 1),
        "nonNumberCols");
    return rowsAndCols;
  }

  /*
   * Extracts the row or column value from the given substring and throws an error if it is not a
   * number
   */
  private void extractRowOrColValue(int[] rowsAndCols, int i, String rowOrCol, String exceptionMessage) {
    try {
      rowsAndCols[i] = Integer.parseInt(rowOrCol);
    } catch (NumberFormatException e) {
      throw new StartingConfigurationException(exceptionMessage);
    }
  }

  /*
   * Gets the index of the given punctuation and throws an exception if it is not found
   */
  private int getIndexOfPunctuation(String fileInfo, String punctuation, String exceptionMessage) {
    int indexOfPunctuation = fileInfo.indexOf(punctuation);
    if (indexOfPunctuation == -1) {
      throw new StartingConfigurationException(exceptionMessage);
    }
    return indexOfPunctuation;
  }


  /*
   * If given a file path to a csv, then creates the board from the file path
   */
  private int[][] placeBoardFromCSV(String filePath, int numberOfStates) {
      List<String[]> list_data = readAll(filePath);
      int[] rowsAndCols = getRowsAndColsFromCSV(list_data);
      int[][] board = new int[rowsAndCols[0]][rowsAndCols[1]];
      fillBoardWithStates(numberOfStates, list_data, rowsAndCols[0], rowsAndCols[1], board);
      return board;
  }

  /*
   * Gets the rows and the columns from the csv and throws exception if not a number. Stores the row
   * number at index 0 and column at index 1
   */
  private int[] getRowsAndColsFromCSV(List<String[]> list_data) {
    int[] rowsAndCols = new int[2];
    extractRowOrColValue(rowsAndCols, 0,
        list_data.get(0)[0].replaceAll("[^\\p{Graph}\n\r\t ]", ""),
        "nonNumberRows");
    extractRowOrColValue(rowsAndCols, 1, list_data.get(0)[1], "nonNumberCols");
    return rowsAndCols;
  }


  /*
   * Puts the states into the board matrix and throws an error if csv too big for rows and cols
   */
  private void fillBoardWithStates(int numberOfStates, List<String[]> list_data, int row_size,
      int col_size, int[][] board) {
    for(int row=1; row <= row_size;row++){
      for(int col=0; col < col_size;col++){
        try {
          board[row - 1][col] = getCellStateNumber(list_data.get(row)[col], numberOfStates);
        }
        catch (ArrayIndexOutOfBoundsException e) {
          throw new StartingConfigurationException("placeBoardException",e);
        }
      }
    }
  }

  /**
   * @param stateNum - individual state number in string format
   * @param numOfStates - number of states of the simulation
   * @return - cellState number
   */
  /*
   * Checks to make sure the state is a number and is in the range of the number of states (throws
   * an exception if not)
   */
  public int getCellStateNumber(String stateNum,int numOfStates) {
    int cellState;
    try {
      cellState = Integer.parseInt(stateNum);
      if (cellState < 0 || cellState >= numOfStates)
        throw new StartingConfigurationException("incorrectInputtedStateException");
    }
    catch (NumberFormatException e) {
      throw new StartingConfigurationException("incorrectInputtedStateException",e);
    }
    return cellState;
  }
}
