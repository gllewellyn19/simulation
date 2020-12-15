package cellsociety.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cellsociety.Main;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class LineGraphTest extends DukeApplicationTest {

  private Controller controller;
  private final Main main = new Main();
  private LineGraph lineGraph;


  @Override
  public void start(Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  public void startingDataValueOnGraphBlinker() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(3,lineGraph.createData(1));
  }

  @Test
  public void dataValueOnInexistentStateGraph() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(3) );
  }

  @Test
  public void graphValueOnBlinkerGameOfLife() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    lineGraph = controller.getCurrLineGraph();
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(98,lineGraph.createData(0) );
    assertEquals(2,lineGraph.createData(1) );
  }

  @Test
  public void graphValueGameOfLifeBlock() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Block.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(4,lineGraph.createData(1) );
    assertEquals(96,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(4,lineGraph.createData(1) );
    assertEquals(96,lineGraph.createData(0) );
  }

  @Test
  public void graphValuePercolationFlowUp() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowuptest.csv/","src/resources/Percolation2.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(23,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(23,lineGraph.createData(0) );
  }

  @Test
  public void graphValuePercolationFlowDown() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowdowntest.csv/","src/resources/Percolation2.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(3,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(3,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
  }



  @Test
  public void graphValuePreyPredator() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpreyeatentest.csv/","src/resources/PreyPredator3.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(2,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(1,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(2,lineGraph.createData(2) );
    assertEquals(0,lineGraph.createData(1) );
    assertEquals(2,lineGraph.createData(0) );
  }

  @Test
  public void graphValueRockScissorsPaper() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprockchangetest.csv/","src/resources/RockPaperScissors4.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(8,lineGraph.createData(1) );
    assertEquals(1,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(9,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
  }
  @Test
  public void checkUpdateLineGraph(){
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    lineGraph = controller.getCurrLineGraph();
    javafxRun(()->controller.getCurrLineGraph().updateLineGraph());
    assertEquals(97,lineGraph.createData(0) );
    assertEquals(3,lineGraph.createData(1) );
  }

  @Test
  public void graphValueSegregationNoMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationnomovetest.csv/","src/resources/Segregation5.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(6,lineGraph.createData(1) );
    assertEquals(3,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(6,lineGraph.createData(1) );
    assertEquals(3,lineGraph.createData(0) );
  }

  @Test
  public void graphValueSegregationMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationmovetest.csv/","src/resources/Segregation5.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(3,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(5,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(3,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(5,lineGraph.createData(0) );
  }

  @Test
  public void graphValueSegregationNoMoveThresholdTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationnomovethresholdtest.csv/","src/resources/Segregation5.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(4,lineGraph.createData(1) );
    assertEquals(5,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(4,lineGraph.createData(1) );
    assertEquals(5,lineGraph.createData(0) );
  }

  @Test
  public void graphValueSpreadingFireNoSpreadTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfirenospreadtest.csv/","src/resources/SpreadingFire6.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(0,lineGraph.createData(1) );
    assertEquals(8,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(0,lineGraph.createData(1) );
    assertEquals(9,lineGraph.createData(0) );
  }

  @Test
  public void graphValueSpreadingFireSpreadTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfirespreadtest.csv/","src/resources/SpreadingFire6.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(8,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
  }

  @Test
  public void graphValueSpreadingFireBurnTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfireburntest.csv/","src/resources/SpreadingFire6.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(0,lineGraph.createData(1) );
    assertEquals(3,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(0,lineGraph.createData(1) );
    assertEquals(4,lineGraph.createData(0) );
  }

  @Test
  public void graphValueModelSquareNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeSquareNeighbor.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(5,lineGraph.createData(1) );
    assertEquals(4,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(4,lineGraph.createData(1) );
    assertEquals(5,lineGraph.createData(0) );
  }

  @Test
  public void graphValueModelTriangleNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeTriangleNeighbor.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(5,lineGraph.createData(1) );
    assertEquals(4,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(1,lineGraph.createData(1) );
    assertEquals(8,lineGraph.createData(0) );
  }

  @Test
  public void graphValueModelCrossNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeCrossNeighbor.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(5,lineGraph.createData(1) );
    assertEquals(4,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,lineGraph.createData(2) );
    assertEquals(4,lineGraph.createData(1) );
    assertEquals(5,lineGraph.createData(0) );
  }

  @Test
  public void graphValueModelFiniteEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationedge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationFiniteEdge.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(8,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(3,lineGraph.createData(2) );
    assertEquals(6,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
  }

  @Test
  public void graphValueModelToroidalEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationedge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationToroidalEdge.sim"));
    lineGraph = controller.getCurrLineGraph();
    assertEquals(1,lineGraph.createData(2) );
    assertEquals(8,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(4,lineGraph.createData(2) );
    assertEquals(5,lineGraph.createData(1) );
    assertEquals(0,lineGraph.createData(0) );
  }

}
