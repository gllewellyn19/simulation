package cellsociety.Controller;

import cellsociety.Model.Exceptions.ModelException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;


/**
 * LineGraph contains all methods that create the graph simulation
 */
public class LineGraph {

  private static final int WINDOW_SIZE = 100;
  private final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
  private final NumberAxis yAxis = new NumberAxis();
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  private final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
  public final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

  private final int numOfStates;
  private final int rows;
  private final int cols;
  private final Controller gameController;
  private final Map<Integer, Series<String, Number>> plot_map = new HashMap<>();

  private static final String PLOT_ID = "STATE";
  private static final int GRAPH_WIDTH = 800;
  private static final int GRAPH_HEIGHT = 600;
  private static final String X_AXIS_LABEL = "Time/s";
  private static final String Y_AXIS_LABEL = "Number Of Cells Per States";
  private static final boolean INITIAL_ANIMATION = false;

  public static final String STYLESHEETS_FOLDER = "Stylesheets/";
  public static final String STYLESHEETS_CSS_EXTENSION = "Graph.css";
  public static final String DEFAULT_STYLESHEET = "Default.css";

  /**
   * @param numOfStates - number of states from current simulation
   * @param rows - -  number of rows of current simulation board
   * @param cols -  number of columns of current simulation board
   * @param controller - controller object from controller class
   */
  public LineGraph(int numOfStates, int rows, int cols,Controller controller){
    this.numOfStates = numOfStates;
    this.rows = rows;
    this.cols = cols;
    gameController = controller;
    start_graph();
  }

  /*
   * Starts the graph and defines multiple series to display data of states
   */
  private void start_graph(){
    Stage primaryStage = new Stage();
    primaryStage.setTitle(gameController.getProperties().getProperty("TitleOfSimulation"));
    primaryStage.show();
    createGraph();
    createStatesPlot(lineChart);
    Scene scene = new Scene(lineChart, GRAPH_WIDTH, GRAPH_HEIGHT);
    primaryStage.setScene(scene);
    setCssStyle(scene);
  }

  /*
   * Sets the CSS of the graph
   */
  private void setCssStyle(Scene scene) {
    try {
      scene.getStylesheets().add(getClass().getResource(Controller.DEFAULT_RESOURCE_FOLDER
          + STYLESHEETS_FOLDER
          + gameController.getProperties().getProperty("SimulationType")
          + STYLESHEETS_CSS_EXTENSION).toExternalForm());
    }catch(NullPointerException e) {
      scene.getStylesheets().add(getClass().getResource(Controller.DEFAULT_RESOURCE_FOLDER
          + STYLESHEETS_FOLDER
          + DEFAULT_STYLESHEET
          + STYLESHEETS_CSS_EXTENSION).toExternalForm());

      throw new ModelException("cssFileNotFound",e);
    }
  }

  /*
   * Creates the graph by defining the axis and creating a line chart with the 2 axis
   */
  private void createGraph() {
    xAxis.setLabel(X_AXIS_LABEL);
    xAxis.setAnimated(INITIAL_ANIMATION);
    yAxis.setLabel(Y_AXIS_LABEL);
    yAxis.setAnimated(INITIAL_ANIMATION);

    lineChart.setTitle(gameController.getProperties().getProperty("TitleOfSimulation"));
    lineChart.setAnimated(INITIAL_ANIMATION);
  }

  /*
   * Adds data to the graph by updating the chart and maintaining the same window size
   */
  private void addDataToGraph(XYChart.Series<String, Number> state, int current_state){
      scheduledExecutorService.execute(()-> {
        int data_point = createData(current_state);
        // Update the chart
        Platform.runLater(() -> {
          Date now = new Date();
          state.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), data_point));
          if (state.getData().size() > WINDOW_SIZE){
            state.getData().remove(0);
          }
        });
      });
  }

  /*
   * Defining multiple series to display data of states
   */
  private void createStatesPlot(LineChart<String, Number> lineChart){
    for(int i=0; i<numOfStates; i++){
      XYChart.Series<String, Number> state = new XYChart.Series<>();
      state.setName(PLOT_ID + i);
      lineChart.getData().add(state);
      plot_map.put(i,state);
      addDataToGraph(state, i);
    }
  }

  /**
   * Updates LineGraph object by updating each plot per iteration of the loop
   */
  public void updateLineGraph() {
    for (int i = 0; i < gameController.getNumberOfStates(); i++) {
      addDataToGraph(plot_map.get(i), i);
    }
  }

  /**
   * @param current_state - state of the cell
   * @return new x value of the plot for the given state (how many cells have that state currently)
   */
  public int createData(int current_state){
    int total_sum = 0;
    for(int row=0; row<rows; row++){
      for(int col=0; col<cols; col++){
        if(gameController.getCellState(row,col)== current_state){
          total_sum++;
        }
      }
    }
    return total_sum;
  }
}