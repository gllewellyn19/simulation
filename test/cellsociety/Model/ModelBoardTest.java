package cellsociety.Model;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.Model.Board;
import cellsociety.Model.DataReader;
import cellsociety.View.GameView;
import java.awt.Dimension;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


import static org.junit.jupiter.api.Assertions.*;


public class ModelBoardTest extends DukeApplicationTest {
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  private Controller controller;
  private Main main = new Main();
  private DataReader dataReader = new DataReader();
  private Board gameBoard;

  @Override
  public void start(Stage stage) {
    //javafxRun(()->main.start(new Stage()));
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  public void checkDimensionsModelBoardTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(10,gameBoard.getCurrBoard().getRows());
    assertEquals(10,gameBoard.getCurrBoard().getCols());
  }

  @Test
  public void BeaconEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(2,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,controller.getModelBoard().getCurrBoard().getCurrCell(2,1).getState());
  }

  @Test
  public void BeaconMiddleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(6,5).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(6,5).getState());
  }

  @Test
  public void BeehiveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beehive.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(4,6).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(4,6).getState());
  }

  @Test
  public void BlinkerEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(2,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(2,1).getState());
  }

  @Test
  public void BlinkerMiddleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(3,5).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(3,5).getState());
  }

  @Test
  public void BlockTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Block.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(3,4).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(3,4).getState());
  }

  @Test
  public void ToadEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Toad_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(2,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(2,1).getState());
  }

  @Test
  public void ToadMiddleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Toad_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(4,3).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(4,3).getState());
  }

  @Test
  public void TubTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Tub.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(4,5).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(4,5).getState());
  }

  @Test
  public void PercolationFlowDownTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowdowntest.csv/","src/resources/Percolation2.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(2,gameBoard.getCurrBoard().getCurrCell(0,1).getState());
  }

  @Test
  public void PercolationFlowUpTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowuptest.csv/","src/resources/Percolation2.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void PercolationNoFlowTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationnoflowtest.csv/","src/resources/Percolation2.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,1).getState());
  }

  @Test
  public void PreyPredatorPreyEatenTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpreyeatentest.csv/","src/resources/PreyPredator3.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(2,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void PreyPredatorPreyMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpreymovetest.csv/","src/resources/PreyPredator3.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void PreyPredatorPredatorMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpredatormovetest.csv/","src/resources/PreyPredator3.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(2,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void RSPRockChangeGreaterThanThresholdTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprockchangetest.csv/","src/resources/RockPaperScissors4.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
  }

  @Test
  public void RSPRockNoChangeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprocknochangetest.csv/","src/resources/RockPaperScissors4.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void RSPRockScissorsNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprockscissorneighborstest.csv/","src/resources/RockPaperScissors4.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void SegregationNoMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationnomovetest.csv/","src/resources/Segregation5.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }


  @Test
  public void SegregationNoMoveThresholdTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationnomovethresholdtest.csv/","src/resources/Segregation5.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }


  @Test
  public void SpreadingFireNoSpreadTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfirenospreadtest.csv/","src/resources/SpreadingFire6.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(1,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(1,0).getState());
  }

  @Test
  public void SpreadingFireSpreadTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfirespreadtest.csv/","src/resources/SpreadingFire6.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(2,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void SpreadingFireBurnTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfireburntest.csv/","src/resources/SpreadingFire6.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(2,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }

  @Test
  public void ModelSquareNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeSquareNeighbor.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
  }

  @Test
  public void ModelTriangleNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeTriangleNeighbor.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
  }

  @Test
  public void ModelCrossNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeCrossNeighbor.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(0,gameBoard.getCurrBoard().getCurrCell(1,1).getState());
  }

  @Test
  public void ModelFiniteEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationedge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationFiniteEdge.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,2).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,2).getState());
  }

  @Test
  public void ModelToroidalEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationedge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationToroidalEdge.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,2).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,2).getState());
  }

  @Test
  public void ModelRandomToroidalEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationrandomtoroidaledge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationToroidalRandomEdge.sim"));
    gameBoard = controller.getModelBoard();
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
    javafxRun(()->controller.updateBoardForStep());
    assertEquals(1,gameBoard.getCurrBoard().getCurrCell(0,0).getState());
  }








}
