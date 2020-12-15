simulation
====

This project implements a cellular automata simulator.

Names:
Justin Lorenz (jml166)
Grace Llewellyn (gal16)
Luisa Silva (lps23)

### Timeline

Start Date: October 2nd, 2020

Finish Date:  October 19th, 2020

Hours Spent: 90 hours

### Primary Roles

* Grace:
	* Worked on both the front end and the back end of the project
		* Did almost all of the buttons and their functionality as well as the the buttons maintainer class
		* All testing for buttons and DataReader and Missing game properties
		* Created abstract design for buttons
		* Errors checks for invalid and missing properties (custom exceptions)
		* Made sure that all exceptions being thrown were caught and the correct message happened
		* Created interfaces for the project and has classes implement them
		* Created ability to change languages in the project (including exceptions)
		* Created different starting configurations for the file
		* Dealt with many small bug fixes through FIXME tags
		* Created splash screen for project and changing CSS files
		* Did most of optional style properties file

* Justin:
    * Focused primarily on implementing the model part of the program
        * Created the Cell classes as well as the rules that run the simulations
        * Created the different types of edges
        * Created the different types of neighbors
        * Created the Board Structure and ModelBoard implementation
    * Helped out in other parts of the program where need be: gameview, buttons, controller, etc.
    * Created tests for model and display board 

* Luisa: 
    * Data Reading class: placing board out of csv file
    * Exception handling and made new custom exceptions and testing for them:
        * ModelException
        * Missing file path exception
    * Controller class tests
    * Linegraph class and implementation with its tests
    * css file reading for linegraph
    * Refactoring


### Resources Used
[Help with css files](https://www.callicoder.com/javafx-css-tutorial/)

[Help with line graphs](https://stackoverflow.com/questions/24899115/javafx-why-linechart-repeats-colors-after-few-plots)

### Running the Program

Main class: Our class called Main is how we launch the Simulation

Data files needed: The following folders in src/resources: languages, exceptions, Stylesheets, data folder, and all the files for the tests

For a new simulation we need a .sim property file with the following specifications:

* Simulation Type (ex: Percolation)
* Title Of Simulation: will appear on javaFx window header (ex: FreeFlowing)
* Author: will appear on javaFx window header together with title (ex: John Smith)
* Description: description of simulation, that will also appear on javaFx window header (ex: Water is flowing )
* InitialConfiguration: path to csv file that contains initial configuration of the simulation (ex: data/TestingCSV/PercolationTest.csv)
* Number of States (ex: 3)
* Neighbor Type (ex: Square)
* Edge Type (ex: Finite)

We also need property file to define some style elements for the graph simulation like 
 (if none is specified the default value will be used)
StateColor+ stateNum (ex StateColor0=WHITE)
ImagePath + stateNum (ex ImagePath0=cellimages/sadFace.jpg)
GridOutlined (Yes or no)
Language (ex English, Spanish)

We need a css property file to specify layout of simulation (if none is specified the default value will be used)

Features implemented:

* Simulation javaFx window
* Language selection (English, Portuguese and Spanish)
* Color style selection (Default, Fall Colors Style,Dark Theme, Light Theme, Big Text)
* Buttons:
    * Launch game button
    * Load new property file button
    * Play button: starts simulation (and graph simulation)
    * Next Generation button: changes board generation once every mouse click
    * Pause button: pause simulation during play
    * Export current configuration button: export board configuration to csv
    * Change cell state color button: brings up pop up window to choose the state and new color for that state for simulation
    * Change cell state image button: brings up pop up window to choose the state and new image for that state for simulation
* Slider accelerates or decelerates simulation speed.
* Graph that keeps that of number of shapes for each state in the simulation and that launches after choosing .sim file and keeps going after main simulation is closed.


### Notes/Assumptions

Assumptions or Simplifications:

Notes about Properities files describing game. Put the game name in camel case and should exactly match the name of the Cell designed to implement that game (minus the word Cell. For example, GameOfLife as the simulation name would match GameOfLifeCell).  File name should be path not in quotes and relative to simulation_team19. On and off colors should be in all capitals 
The Cell that the user implements should be in src/cellsociety/Model/GameCells

Only image file types accepted are jpg, png, gif, and bmp because these are the only image types accepted by the java image class

InitialConfiguration- either a csv file, "Random:numRows,numCols" for a random generation, or "Checkered:numRows,numCols" for a board that oscillates between the states (best results if odd number of rows and columns)

How to add a new language: Create a language file with .properties and put it in src/resources/languages then add the option to the language button as the name of the language file.  Also, create language + Exceptions.properties and save it in src/resources/exceptions. The language files needs to contain all necessary keys which can be found in any of the existing language and exception properties.

We only look at the data in the csv that is inside the bounds of the given row and column range. This gives the user greater flexibility because they can modify the row and column range for the same csv file

How to match color of graph and squared simulation: specify the same state color for both .sim file and css graph style file, by
first specifying color of of states on .sim property file and then match it by specifying the same
colors for the css file for graph.

Where files css files are located: add new css File for new simulation on Resources -> Stylesheets folder

Where files .sim files are located:.sim property files are located in resources folder

Where language css files are located: resources -> languages folder

Where exceptions property files are located: resouces -> exceptions

Where files used for testing are located: resources -> TestingPropertiesFiles


Interesting data files:

* src -> resources -> Percolation2.sim

Known Bugs:

* When main simulation stops, the graph simulation keeps recording data points, but those are constant. If
the user wants to stop the graph too, just hit pause button.
* To stop simulation from running hit "Stop" Main button on intellij, otherwise the simulation keeps running even after both windows are closed

Errors from design coach: We believe the methods marked as too complex aren't too complex, GameCellFactory needs to have that many parameters, method is empty for reason noted in header, and control structures don't need curly brackets

Extra credit:
Did almost all optional and started to implement shapes optional 
Extensive erorr checking. For example, if cannot find the exception bundle, just has a default message instead of breaking

### Impressions

We enjoyed working on the project very much. Everyone already had at least some experience with
javaFx due to our last project and past experiences so it was much easier to navigate the requirements
due each week. It was a very visual project, which made it easier to test and debug as we could easily see the
results of our code popping up in our screen. Hope users have as much fun exploring it as we did coding it.

