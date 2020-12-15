package cellsociety.Model.Exceptions;

/**
 * Thrown when we have a missing File Path
 */
public class MissingFilePathException extends RuntimeException {

  private final String filePath;

  public MissingFilePathException(String message, String filePath, Throwable cause){
    super(message);
    this.filePath = filePath;
  }

  public String getFilePath() {
    return filePath;
  }

}
