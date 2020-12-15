# Simulation Design Final
### Names

Justin Lorenz (jml166)
Grace Llewellyn (gal16)
Luisa Silva (lps23)



## Team Roles and Responsibilities


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



## Design goals

#### What Features are Easy to Add

Adding new simulation types/neighbors/edges
Adding new buttons
Creating a new Design type - using css files
Changing colors from simulation states
Add new language
Add new style sheet
See below for more information 

## High-level Design

In this project, we decided that we would implement the MVC set up as described in class. Basically, we have split our project into these three main parts. We have the model section that deals with all model board structure and the game algorithms and has no reference of any JavaFx components. We then have the view which consists of many different JavaFx components such as the display board, GUI, buttons, etc. We then have the controller as the main linkage between both which basically takes information from one of the branches such as model or view and passes that needed information or instruction to the other sector. What I mean by this is that the model and view have no knowledge of the other one existing, but they both require each other's knowledge to function. However, they can't pass this info to each other, so they actually have the controller to serve as this bridge. For example, the model board updates every cycle based on when the controller tells it to do so. However, the view needs to update its display based on the new model data, so instead of sending the model board into the view, the view grabs the correct information from the controller such as the current state at the cell at position i,j. Overall, our project can be split into these 3 main components that all work hand and hand together, and then when you look at each component themselves, there are plenty of subclasses that make them up.

#### Core Classes
Controller- connects the model and view of the game and handles loading in a property file and calling the appropriate methods 
Cell - one abstract super class that holds common methods for all the cells in the game. Each individual cell class represents a new type of simulation and has methods such as holding the new rules of each simulation.
Board - Holds the boardstructure for the model and continuously updates the board structure and cells based off when the controller tells it to
Edge - one abstract class that has methods that check if the requested cell went out of bounds, and then has subclasses that determine what to do if the cell went out of bounds such as do nothing or find a neighbor on the opposite side of the board.
Neighbor - one abstract class that has some methods such as find singular neighbor. Has subclasses where the actual indices for the current neighbor structure is defined.
Gameview -> Gameview dealt with the visual of the game and had a button maintainer (which maintained the buttons) and a display board (which maintained the board). Gameview also created the scenes, dealt with the CSS, and printed the error messages 
Button Maintainer- Maintains a map of all the buttons after creating them and adds them to a node to display on the board. Also starting the initial enabling of the buttons
DisplayBoard - Holds the view board structure for the GUI. Continually updates the board colors based on the states that are being updated in the model. Also holds the ability to respond to mouse input and let the controller know to tell the model to change the state.
BoardInteractiveFeature- overarching abstract class that is also implemented by 2 abstract classes BoardChoiceBox (for drop down buttons) and BoardButton (for clicking buttons). This abstract class was implemented by all clickable things in the game (buttons, sliders, choice boxes) and had some basic methods like updating the language and getting the resources bundle that had the text for the buttons. 

## Assumptions that Affect the Design

Notes about Properties files describing the game. Put the game name in camel case and should exactly match the name of the Cell designed to implement that game (minus the word Cell. For example, GameOfLife as the simulation name would match GameOfLifeCell).  File name should be path not in quotes and relative to simulation_team19. On and off colors should be in all capitals
The Cell that the user implements should be in src/cellsociety/Model/GameCells


We only look at the data in the csv that is inside the bounds of the given row and column range. This gives the user greater flexibility because they can modify the row and column range for the same csv file

We made all interactive features in the board implement BoardInteractiveFeature so that we could keep that in a map, but then we only had access to the methods that this had. 
DisplayBoard would have a CellPaneStructure to implement its design.
StateFillMaintainer completely dealt with the image patterns and colors for the grid and made images take precedence over colors if the user gave both in the style properties file. 

#### Features Affected by Assumptions

InitialConfiguration- either a csv file, "Random:numRows,numCols" for a random generation, or "Checkered:numRows,numCols" for a board that oscillates between the states (best results if odd number of rows and columns)

Only image file types accepted are jpg, png, gif, and bmp because these are the only image types accepted by the java image class



## Significant differences from Original Plan

In our original plan we had the MVC plan in mind, which is the essence of our current code, but many details changed from that first plan. Firstly, we made the view also hold all the buttons that were displayed in our main simulation screen. We also maintained the idea of the Controller being a “middle-man” between model and view, but we ended up adding a main class that takes care of starting our scene and launching, which helps us decrease the two way dependency of the Controller and View classes. We also added a Line Graph class to the controller package that deals with another type of simulation (for which we didn’t account for in the original plan). 


## New Features HowTo


#### Easy to Add Features

Add new buttons - add a new button subclass for that button that extends BoardInteractiveFeature abstarct superclass (and potentially one of the other abstract classes depending on the type of button- click button implements BoardButton and drop down implements BoardChoiceBox) and add that button the buttons panel in the ButtonsMaintainer class.

New cell classes/ simulation type - add a new subclass that extends Cell superclass with new simulation rules, overriding three specific methods: nextGenerationRules, setThisGamesStates, changeStateWhenClicked. Nothing needs to be changed with a key, but the naming convention for the SimulationType value needs to be the same as the cell class minus the work cell. For example, for the game of life, the value for the SimulationType is GameOfLife and the cell class that corresponds with it is GameOfLifeCell. You also have to make sure the number of states is passed in for the value of NumberOfStates in the properties file. 


How to match color of graph and squared simulation: specify the same state color for both .sim file and css graph style file, by first specifying color of states on .sim property file and then match it by specifying the same colors for the css file for graph. 

New edges - easy to implement because only need to extend the edges class and implement the methods that describe what should happen if an edge is encountered on the board. Put in the Cell folder. Integrate into project by passing in the name of the class as the EdgeType value in the style properties file. 

New neighbors - easy to implement because only need to extend the abstract neighbor class and list the allowed neighbors for that specific neighbor structure in the simulation. Put in the Cell folder. Integrate into project by passing in the name of the class as the NeighborType value in the style properties file. 

Different types of board structures - can easily change between board structures by changing the data structure inside of the board structure class and everything should change accordingly. 

New language- Create a language file with .properties and put it in src/resources/languages then add the option to the language button as the name of the language file.  Also, create language + Exceptions.properties and save it in src/resources/exceptions. The language files needs to contain all necessary keys which can be found in any of the existing language and exception properties.

New stylesheet- Create the CSS stylesheet and put it in the style sheets folder then add to all the language properties file “NameStyleSheet + next available index.” For example, there are 4 style sheets so the next style sheet key should be named NameStyleSheet4 and then the value should be exactly the same as the file name, but can have spaces. Also add this to every language properties file with how you would like it to appear for that button. Also make sure the file is named in english in the style sheets folder 


#### Other Features not yet Done


Different types of shapes for the GUI display board 
Currently we have the game setup to be open to adding new types of shapes into the game. We did not get to that during the actual project timeline, but we have the cellpanestructure class already set up to use reflection to define the requested game shape. To add a new shape, simply request this new shape from the .sim file if it is already defined in javafx or if your shape doesn’t have a built in class, create a new subclass of a shape that draws the polygon in the correct dimensions.
The states of the simulations being represented by enumeration- this change would be rather hard to implement and would take changing some core classes. The tradeoffs are discussed in analysis. 
Expanding board structure that updates its own rows and cols if an edge is hit
