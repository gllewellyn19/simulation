package cellsociety.Model.GameCells.Enumeration;

public enum GameOfLifeStates {
  ALIVE ("Alive"),
  DEAD ("Dead");

  // immutable instance variables
  private final String state;


  // constructor cannot be called, but private just to emphasize that
  private GameOfLifeStates (String state) {
    this.state = state;
  }

  // getters, no setters
  public String getState () {
    return state;
  }

  /**
   * Returns correct instance based on given value (could be a String, number, boolean, etc.)
   *
   * Note, method's complexity depends on complexity of names used or if you prefer to use toString() or
   * other more readable name to appear in a data file.
   */
  public static GameOfLifeStates fromValue (String name) {
    // simple name, simple conversion
    return GameOfLifeStates.valueOf(name.toUpperCase());
    // or, if using underscores for long names
//        return Planet.valueOf(name.replaceAll(" ", "_").toUpperCase());
    // non-standard name or numeric value, may require a search
//        for (Planet p : Planet.values()) {
//            if (p.name().equalsIgnoreCase(name)) {
//                return p;
//            }
//        }
    // or, may have to make Map if some other means (like abbreviation, alternate language, general value, etc.)
  }

}
