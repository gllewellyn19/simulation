package cellsociety.View;

import cellsociety.Controller.Controller;
import cellsociety.Main;
import cellsociety.View.Boards.DisplayBoard;
import cellsociety.View.StateFillMaintainer;
import java.util.Properties;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Shape;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;


public class GameViewTest extends DukeApplicationTest {


  private Controller controller;
  private Main main = new Main();

  @Override
  public void start(Stage stage) {
    main.start(new Stage());
    controller = main.getGameController();
  }

  @Test
  public void displayBeaconEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[2][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[2][1].getFill());
  }

  @Test
  public void displayBeaconMiddleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beacon_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[6][5].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[6][5].getFill());
  }

  @Test
  public void displayBeehiveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Beehive.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[4][6].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[4][6].getFill());
  }

  @Test
  public void displayBlinkerEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[2][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[2][1].getFill());
  }

  @Test
  public void displayBlinkerMiddleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Blinker_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[3][5].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[3][5].getFill());
  }

  @Test
  public void displayBlockTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Block.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[3][4].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[3][4].getFill());
  }

  @Test
  public void displayToadEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Toad_edge.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[2][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[2][1].getFill());
  }

  @Test
  public void displayToadMiddleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Toad_middle.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[4][3].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[4][3].getFill());
  }


  @Test
  public void displayTubTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/Tub.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLife1.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[4][5].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[4][5].getFill());
  }

  @Test
  public void displayPercolationFlowDownTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowdowntest.csv/","src/resources/Percolation2.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(2),gridCells[0][1].getFill());
  }

  @Test
  public void displayPercolationFlowUpTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowuptest.csv/","src/resources/Percolation2.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
  }

  @Test
  public void displayPercolationNoFlowTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationflowuptest.csv/","src/resources/Percolation2.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][1].getFill());
  }

  @Test
  public void displayPreyPredatorPreyEatenTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpreyeatentest.csv/","src/resources/PreyPredator3.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(2),gridCells[0][0].getFill());
  }

  @Test
  public void displayPreyPredatorPreyMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpreymovetest.csv/","src/resources/PreyPredator3.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
  }

  @Test
  public void displayPreyPredatorPredatorMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/preypredatorpredatormovetest.csv/","src/resources/PreyPredator3.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(2),gridCells[0][0].getFill());
  }

  @Test
  public void displayRSPRockChangeGreaterThanThresholdTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprockchangetest.csv/","src/resources/RockPaperScissors4.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[1][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[1][1].getFill());
  }

  @Test
  public void displayRSPRockNoChangeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprocknochangetest.csv/","src/resources/RockPaperScissors4.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
  }

  @Test
  public void displayRSPRockScissorsNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/rsprockscissorneighborstest.csv/","src/resources/RockPaperScissors4.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
  }

  @Test
  public void displaySegregationNoMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationnomovetest.csv/","src/resources/Segregation5.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
  }

  @Test
  public void displaySegregationMoveTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationmovetest.csv/","src/resources/Segregation5.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertTrue(gridCells[0][0].getFill() != stateFillMaintainer.getStateColor(1));
  }

  @Test
  public void displaySegregationNoMoveThresholdTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/segregationnomovethresholdtest.csv/","src/resources/Segregation5.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
  }

  @Test
  public void displaySpreadingFireNoSpreadTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfirenospreadtest.csv/","src/resources/SpreadingFire6.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[1][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[1][0].getFill());
  }

  @Test
  public void displaySpreadingFireSpreadTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfirespreadtest.csv/","src/resources/SpreadingFire6.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(2),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
  }

  @Test
  public void displaySpreadingFireBurnTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/spreadingfireburntest.csv/","src/resources/SpreadingFire6.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(2),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[0][0].getFill());
  }

  @Test
  public void displayModelSquareNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeSquareNeighbor.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[1][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[1][1].getFill());
  }

  @Test
  public void displayModelTriangleNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeTriangleNeighbor.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[1][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[1][1].getFill());
  }

  @Test
  public void displayModelCrossNeighborTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/gameoflifeneighbors.csv/","src/resources/TestingPropertiesFiles/TestingGameOfLifeCrossNeighbor.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[1][1].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(0),gridCells[1][1].getFill());
  }

  @Test
  public void displayModelFiniteEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationedge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationFiniteEdge.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][2].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][2].getFill());
  }

  @Test
  public void displayModelToroidalEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationedge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationToroidalEdge.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][2].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][2].getFill());
  }

  @Test
  public void displayModelRandomToroidalEdgeTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationrandomtoroidaledge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationToroidalRandomEdge.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    Shape[][] gridCells = displayBoard.getGridCells();
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
    javafxRun(controller::updateBoardForStep);
    assertEquals(stateFillMaintainer.getStateColor(1),gridCells[0][0].getFill());
  }

  @Test
  void checkGridOutlineStyleTest() {
    javafxRun(()->controller.createAndReadInNewBoardForTesting("data/TestingCSV/percolationrandomtoroidaledge.csv/","src/resources/TestingPropertiesFiles/TestingPercolationToroidalRandomEdge.sim"));
    StateFillMaintainer stateFillMaintainer = controller.getGameView().getStateFillMaintainer();
    DisplayBoard displayBoard = controller.getGameView().getDisplayBoard();
    javafxRun(controller::updateBoardForStep);
    displayBoard.changeGridOutline(true);
    javafxRun(controller::updateBoardForStep);
    assertEquals(Color.BLACK,displayBoard.getGridCells()[0][0].getStroke());
  }

}

