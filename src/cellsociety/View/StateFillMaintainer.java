package cellsociety.View;

import cellsociety.Model.Exceptions.InvalidPropertyException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

/**
 * This class deals with setting the fill of the cells for the DisplayBoard. It defaults to random
 * colors initially, then changes once the user adds in the new style properties file. Also, this
 * class doesn't care about what the keys are for the properties file since it's passed in as a map.
 * This class also is called when the user uploads a new color or image from the button and that
 * overrides the current color or image. This class also provides access to the current color
 * and image. This class doesn't depend on other classes. An example of how to use this class is
 * give it to the DisplayBoard so that it can update the cells accordingly
 * Note: This class prefers showing the image if both color and image are provided
 *
 * @author Grace Llewellyn
 */
public class  StateFillMaintainer {

  private static final int INC_RGB_BY = 100;
  private static final int MAX_RGB = 255;
  public static final String HEX_PREFIX1 = "0x";
  public static final String HEX_PREFIX2 = "#";
  private static final int LENGTH_HEX_COLOR = 8;
  private static final String ERROR_SEPARATOR = ": ";
  private static final int HEX_START_VALUE1 = 2;
  private static final int HEX_END_VALUE1 = 4;
  private static final int HEX_END_VALUE2 = 6;
  private static final int HEX_END_VALUE3 = 8;
  private static final int HEX_RADIX = 16;
  public static final int NUM_RGB_VALUES = 3;
  public static final String COLOR_IDENTIFIER = "StateColor";
  public static final String IMAGE_IDENTIFIER = "ImagePath";

  private final int[] rgb = new int[NUM_RGB_VALUES];
  private int currentIndexRGB;
  private final ImagePattern[] cellStateImages;
  private final String[] imageLocations;
  private final Color[] cellStateColors;



  public StateFillMaintainer(int numberOfStates) {
    currentIndexRGB = 0;
    cellStateImages = new ImagePattern[numberOfStates];
    cellStateColors = new Color[numberOfStates];
    imageLocations = new String[numberOfStates];
    addDefaultColors();
  }

  /*
   * Populate cellStateColors with default colors to use until a style sheet is uploaded
   */
  private void addDefaultColors() {
    for (int i=0; i< cellStateColors.length; i++) {
      cellStateColors[i] = getNewDefaultColor();
    }
  }

  /**
   * @param newColors - Map<Integer,String> with new color names as value and state number as key
   * @param newImages - Map<Integer, String> with new image as value and state number as key
   * Takes in a new style properties object and doesn't expect anything from this object, but throws
   * errors accordingly if missing file paths. Also, updates all color and image states that this
   * properties file maintains. All mapping keys are in bounds
   */
  public void uploadStyleProperties(Map<Integer,String> newColors, Map<Integer, String> newImages) {
    populateArrayCellImageFileNamesFromMap(newImages);
    populateArrayCellColorsFromMap(newColors);
  }

  /*
   * Adds all the images as image patterns to the list so that can use setFill. If property missing
   * or file not found, index remains null so that the setFill function knows to use color instead
   */
  private void populateArrayCellImageFileNamesFromMap(Map<Integer, String> newImages) {
    for (Map.Entry<Integer, String> entry : newImages.entrySet()) {
      addNewImageState(entry.getValue(), entry.getKey());
    }
  }

  /**
   * @param filePath - file path from image
   * @param i - index for new image
   * Adds a new image state based on the file name and at the given index
   */
  public void addNewImageState(String filePath, int i) {
    try {
      imageLocations[i] = filePath;
      Image image = new Image(new FileInputStream(filePath));
      cellStateImages[i] = new ImagePattern(image);
    } catch (FileNotFoundException e) {
      throw new InvalidPropertyException(IMAGE_IDENTIFIER + i + ERROR_SEPARATOR + filePath,e);
    }
  }

  /*
   * Populates the array of state colors from the map and checks to see if the colors are valid and
   * throws exception if not
   */
  private void populateArrayCellColorsFromMap(Map<Integer, String> newColors) {
    for (Map.Entry<Integer, String> entry : newColors.entrySet()) {
      addNewColor(entry.getKey(), entry.getValue());
    }
  }

  /**
   * @param i  - state index
   * @param newColor - new color string
   * Adds a new color to the list of colors at the specified index/ state. Color can be written in
   * hex or normal
   */
  public void addNewColor(int i, String newColor) {
    try {
      if (newColor.startsWith(HEX_PREFIX1)) {
        cellStateColors[i] = getColorFromHex(newColor.substring(HEX_PREFIX1.length()));
      } else if (newColor.startsWith(HEX_PREFIX2)) {
        cellStateColors[i] = getColorFromHex(newColor.substring(HEX_PREFIX2.length()));
      } else {
        cellStateColors[i] = Color.web(newColor);
      }
    } catch (IllegalArgumentException e) {
      throw new InvalidPropertyException(COLOR_IDENTIFIER + i + ERROR_SEPARATOR + newColor,e);
    }
  }

  /**
   * @param hexColor - color in hex notation
   * @return - Color object from specified hex string
   * Returns a color object from the hex value of a color
   */
  public Color getColorFromHex(String hexColor) {
    if (hexColor.length() != LENGTH_HEX_COLOR) {
      throw new InvalidPropertyException(COLOR_IDENTIFIER + ERROR_SEPARATOR + hexColor);
    }
    return Color.rgb(
        Integer.valueOf(hexColor.substring(HEX_START_VALUE1, HEX_END_VALUE1), HEX_RADIX),
        Integer.valueOf(hexColor.substring(HEX_END_VALUE1, HEX_END_VALUE2), HEX_RADIX),
        Integer.valueOf(hexColor.substring(HEX_END_VALUE2, HEX_END_VALUE3), HEX_RADIX));
  }

  /*
   * Gets a new default color (can be called an infinite number of times)
   */
  private Color getNewDefaultColor() {
    int newRGBVal = rgb[currentIndexRGB] + INC_RGB_BY;
    newRGBVal = checkNewRGBInBounds(newRGBVal);
    rgb[currentIndexRGB] = newRGBVal;
    return Color.rgb(rgb[0], rgb[1], rgb[2]);
  }

  /**
   * @param newRGBVal - RGB value (3 of them specify a color)
   * @return - new RGB int value
   * Checks if the new RGB value is in bounds and if it is not then sets that index to 0 and jumps
   * to a new index in rbg to work with
   */
  public int checkNewRGBInBounds(int newRGBVal) {
    if (newRGBVal > MAX_RGB) {
      rgb[currentIndexRGB] = 0;
      if (currentIndexRGB < rgb.length - 1) {
        currentIndexRGB++;
      } else {
        currentIndexRGB = 0;
      }
      newRGBVal = rgb[currentIndexRGB];
    }
    return newRGBVal;
  }

  /**
   * @param toFill - shape of cell
   * @param state -  state of current cell
   * Tries to set the fill as an image if applicable, assumes that state is in bounds since checked
   * elsewhere
   */
  public void setFillOfCell(Shape toFill, int state) {
    if (cellStateImages[state] == null) {
      toFill.setFill(cellStateColors[state]);
    } else {
      toFill.setFill(cellStateImages[state]);
    }
  }

  /**
   * @param state - state of cell
   * @return - string with image location
   * Returns the image location of the given state after making sure it is in bounds (returns null
   * if not)
   */
  public String getImageLocation(int state) {
    if (state < imageLocations.length) {
      return imageLocations[state];
    }
    return null;
  }

  /**
   * @param state - state of cell
   * @return - string color of cell
   * Returns the color of the given state after making sure it is in bounds (returns null if not)
   */
  public String getColor(int state) {
    if (state < cellStateColors.length) {
      return cellStateColors[state].toString();
    }
    return null;
  }

  /**
   * @param state - state of cell
   * @param newColor - new color to change cell state to
   * Changes the color at the given state (color can be hex or written out and valid in Java Color
   * class). Makes the image at that state null because image takes precedence if it is present
   */
  public void changeStateColor(int state, String newColor) {
    checkValidState(state);
    addNewColor(state, newColor);
    cellStateImages[state] = null;
    imageLocations[state] = null;
  }

  /**
   * @param state - cell state
   * @param imageLocation - image location string
   * Changes the image at the given state and stores that images location for exporting later.
   */
  public void changeStateImage(int state, String imageLocation) {
    checkValidState(state);
    addNewImageState(imageLocation, state);
  }

  /**
   * @param state -  cell state
   * Checks if the given state is within the number of states of the game and throws an invalid
   * property exception if not
   */
  public void checkValidState(int state) {
    if (state < 0 || state >= cellStateColors.length) {
      throw new InvalidPropertyException("State");
    }
  }


  /*
   * For testing purposes only
   */
  public Color getStateColor(int state) {
    return cellStateColors[state];
  }

}
