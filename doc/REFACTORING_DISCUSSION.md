## Lab Discussion
### Team
19
### Names
Justin Lorenz jml166
Luisa Silva lps23
Grace Llewellyn gal16

### Issues in Current Code

Immutable Interfaces are not present
Have some hidden dependencies
Have to change setters to protected
Magic values that deal with hex/color codings
Intrafile duplication with the get property part of our code

#### Method or Class - Button Class
 * Design issues - SRP/Open Closed

The one issue that we noticed with the open closed principle in the button class is that when we have to add a new button you must change the actual gameview class. This is not good because this could lead to problems because we are changing existing code. To fix this problem, we are going to focus on creating a new overall button class that will be the host of the declaration/initialization of our buttons. Our other problem with the single responsibility principle applies to whether the button classes should be able to access the controller. Currently have that as the case, but if we didn’t want that to happen, we would have to add a ton of new button methods in the controller. This would lead to the controller breaking the SRP, so we feel that we could also fix this issue by having another overall button class. 

 * Design issue

The two way dependency the View and the Controller classes have is reflected on every Button class we have. We need the controller to initialize our buttons which makes the Buttons dependent on the controller and we need the gameview class on Controller to set up our scene on the start method. We also have other 2 way dependencies in our code that need to be fixed

#### Method or Class
 * Design issues
We currently don’t have any immutable interfaces in our code which is something Duvall has talked a lot about in class. We should do this in order to restrict how much control each class has and how many methods it can call. 

 * Design issue
We have to change the setters in our project to protected because only things in the same package should be able to modify the instance variables of a fellow class in its package


### Refactoring Plan

 * What are the code's biggest issues?

Our code’s biggest issue at the moment is the two way dependencies between some of the classes, such as GameView and Controller. This is something that is throughout the project a lot and something we have struggled with for a while. We also have a bunch of testing bugs that we are trying to get worked out. We also have a lot of methods and need commented and refactoring 

 * Which issues are easy to fix and which are hard?

The issues that are currently easy to fix would be implementing immutable interfaces as well as changing our setters to protected. The changes that would be hard would be the changes involved with altering the hidden dependencies. These changes would require us to reevaluate the code and figure out how to properly integrate the MVC without any violations, which requires much more effort than simple changes.

 * What are good ways to implement the changes "in place"?

Good ways to implement these changes in place is to fix the small errors on design coach such as:

*fixing magic numbers
*get rid of public static fields
*add curly brackets to the control structures

In addition, we also plan on creating a new Main class (that will contain our launch method) to decrease the two way dependencies on our classes. Because that way we wouldn’t have the controller depending on gameview class and gameview depending on controller class.



### Refactoring Work

 * Issue chosen: Fix and Alternatives
We chose to fix the problem with our buttons in game view by creating a new buttons class that deals with creating all of the buttons and putting them on the screen so that we aren’t violating single responsibility principle. Gameview would then have a Buttons class 


 * Issue chosen: Fix and Alternatives

To decrease the dependencies within our project we decided (due to Duvall’s help) to create a Main class that has a controller, model, and game view. This is meant to reduce the dependencies in our code and the main class is the class that you run. Also, we are going to create more interfaces that are passed into functions so that these functions have more limited access. 
