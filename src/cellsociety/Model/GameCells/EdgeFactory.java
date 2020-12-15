package cellsociety.Model.GameCells;

import cellsociety.Model.Exceptions.InvalidPropertyException;
import cellsociety.Model.Exceptions.ModelException;
import java.lang.reflect.InvocationTargetException;

public class EdgeFactory {
  public Edge getTheCurrentEdgeType(String edgeType)
  {
    try {
      if (edgeType == null)
        return new Finite();
      Class currentGameTypeClass = Class.forName("cellsociety.Model.GameCells."+edgeType);
      return (Edge)currentGameTypeClass.getDeclaredConstructor().newInstance();
    }
    catch (NoSuchMethodException | IllegalAccessException | InstantiationException | IllegalStateException | InvocationTargetException | ClassNotFoundException e) {
      throw new InvalidPropertyException("EdgeType",e);
    }
  }
}
