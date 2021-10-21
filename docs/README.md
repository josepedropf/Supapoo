# LPOO_37 - Supa

In this video game the player controls a red ball called Murphy. To complete a level, they must collect a specific
amount of infotrons and reach the exit. It includes several levels of varying difficulty.  
This game is based on Supaplex (<https://supaplex.fandom.com/>).  
This project was developed by José Ferreira (<up201904515@edu.fe.up.pt>) and Lucas Santos (<up201904517@edu.fe.up.pt>)

## Implemented Features

- **Bases** - Green stationary blocks that may prevent other elements from falling.
- **Zonks** - Balls that can be pushed and fall under the influence of gravity. The player may need to move them to
  complete the level.
- **Infotrons** - Collectable items required to complete a level. The player needs to collect them all to finish a
  level.
- **XEnemies** - Enemies that move only along the X-axis and change direction when detecting collisions along said axis.
- **Ports** - Portals that allow Murphy to teleport to the other side. These may be unidirectional.
- **Jokers** - Enemies that, if able, always move in a random pattern.
- **Explosions** - Some items explode when hitting the ground (or other objects) after falling, and Murphy dies if hit
  by an explosion.
- **End Blocks** - A block that Murphy must touch (after collecting all the Infotrons) in order to end a certain level.
- **Falling** - Some objects will fall or slide to the sides until they have some surface to support them.
- **Move** - Murphy moves, controlled by the arrow keys.
- **Collect/Eat/Slurp** - Murphy can collect Infotrons and eat Bases. Additionally, if the `Ctrl` key is pressed when
  using the arrow keys, Murphy can collect these items without moving to their positions (slurping).
- **Push** - Murphy may push Zonks to the sides, when possible.
- **Pause** - The player can pause the level by pressing `P`.
- **Restart** - The player can restart the current level by pressing `R`.
- **Skip** - The player can skip to the next level by pressing `H`.
- **Quit** - The player can quit the level and return to the main menu by pressing `Q`.
- **Sprites** - Levels elements are drawn using multiple characters as "pixels", to form more complex images.
- **Menus** - The user has available menus to start/quit the game, as well as select the desired level.
- **Splash screens** - A message is displayed for a limited amount of time (when winning or losing a level, for example)
  .

## Design

### Creating different menus, levels and level elements

#### Problem in context

There was the need to create different levels and menus, with different elements and layouts. Otherwise, the levels and
menus creation would need to be hardcoded into the models.  
This would be a violation of the **Single Responsability Principle**, even if we moved the level and menu creation to
methods of the Game class.

#### The Pattern

We have applied the [**Builder** Pattern](https://refactoring.guru/design-patterns/builder). This pattern provides an
abstract class for creating objects, allowing subclasses to alter the type of objects that will be created.  
This pattern enabled us to address the identified problems because it allowed us to easily define different builders to
be used for all kinds of elements.

#### Implementation

The following diagram shows how the pattern’s roles were mapped to the application classes.  
![Builder Pattern UML](/docs/images/BuilderPatternUML.png)

The classes where this pattern was applied can be found in the following files:

- [MenuBuilder](../src/main/java/com/g37/supa/model/menu/MenuBuilder.java)
- [StartMenuBuilder](../src/main/java/com/g37/supa/model/menu/StartMenuBuilder.java)
- [PauseMenuBuilder](../src/main/java/com/g37/supa/model/menu/PauseMenuBuilder.java)
- [WinMenuBuilder](../src/main/java/com/g37/supa/model/menu/WinMenuBuilder.java)
- [LostMenuBuilder](../src/main/java/com/g37/supa/model/menu/LostMenuBuilder.java)
- [LevelBuilder](../src/main/java/com/g37/supa/model/level/LevelBuilder.java)
- [LoaderLevelBuilder](../src/main/java/com/g37/supa/model/level/LoaderLevelBuilder.java)
- [LevelElementBuilder](../src/main/java/com/g37/supa/model/level/elements/builders)

#### Consequences

The use of the Builder Pattern in the current design allows the following benefits:

- We can construct objects step-by-step.
- We can reuse the same construction code when building various representations of products.
- Single Responsibility Principle. We can move the product creation code into one place in the program, making the code
  easier to support.

### Switching between Levels, Menus and Splash Menus

#### Problem in context

There was the need to switch between levels, menus and splash menus, each with their own respective controller and
viewer.  
Initially, each controller had its own loop to show the respective model. However, this led to duplicate code between
the controllers and scattered conditional logic.

#### The Pattern

We have applied the [**State** Pattern](https://refactoring.guru/design-patterns/state). This pattern allows us to keep
state-specific behaviors inside different subclasses that represent different states. We can switch to a different state
of the application by switching to another implementation (i.e., another subclass).  
This pattern enabled us to address the identified problems because it allowed us to keep the game states in a stack
located in the Game class, eliminating the need to use `switch` operators, and making the change in state much easier to
handle.

#### Implementation

The following diagram shows how the pattern’s roles were mapped to the application classes.  
![State Pattern UML](/docs/images/StatePatternUML.png)

The classes where this pattern was applied can be found in the following files:

- [State](../src/main/java/com/g37/supa/state/State.java)
- [LevelState](../src/main/java/com/g37/supa/state/LevelState.java)
- [MenuState](../src/main/java/com/g37/supa/state/MenuState.java)
- [SplashMenuState](../src/main/java/com/g37/supa/state/SplashMenuState.java)

#### Consequences

The use of the State Pattern in the current design allows the following benefits:

- Localizes and partitions behavior for different states.
- Makes state transitions explicit.

### Having different types of controllers and viewers

#### Problem in context

In the context of this project having one Model, one Viewer and one Controller wasn't enough. A level needed its own
controller and viewer and so did a menu. For example, levels and menus behave (and look) in a very different way, and
therefore couldn't have the same controller or viewer.  
Having established that, we still wanted to have a step structure that was intuitive and applicable to all aspects of
the game (both levels and menus). So we needed to know what were the viewer and controller of every element/model.

#### The Pattern

We have applied the [**Factory Method** Pattern](https://refactoring.guru/design-patterns/factory-method). This method
deals with the problem of creating objects without having to specify the exact class of the object that will be created.
This is done by creating objects by calling a factory method—either specified in an interface and implemented by child
classes, or implemented in a base class and optionally overridden by derived classes—rather than by calling a
constructor. In our case, we are able to have an abstract class State that glues together model, viewer and controller
of a given element. We can then, as an example, have a common step operation for all controllers. In practise, we create
specific MenuStates or LevelStates, which have the products MenuController and LevelController.

#### Implementation

The following diagram shows how the pattern’s roles were mapped to the application classes.  
![Factory Method Pattern UML](/docs/images/FactoryMethodPatternUML.png)

The classes where this pattern was applied can be found in the following files:

- [State](../src/main/java/com/g37/supa/state/State.java)
- [LevelState](../src/main/java/com/g37/supa/state/LevelState.java)
- [MenuState](../src/main/java/com/g37/supa/state/MenuState.java)
- [SplashMenuState](../src/main/java/com/g37/supa/state/SplashMenuState.java)
- [Controller](../src/main/java/com/g37/supa/controller/Controller.java)
- [LevelController](../src/main/java/com/g37/supa/controller/level/LevelController.java)
- [MenuController](../src/main/java/com/g37/supa/controller/menu/MenuController.java)
- [SplashMenuController](../src/main/java/com/g37/supa/controller/menu/SplashMenuController.java)

#### Consequences

The use of the Factory Method Pattern in the current design allows the following benefits:

- Single Responsibility Principle. We can move the product creation code into one place in the program, making the code
  easier to support.
- Open/Closed Principle. You can introduce new types of products into the program without breaking existing code.

### Running and refreshing the game and its states

#### Problem in context

There was a need to decouple the progression of game time from user input and processor speed, and being able to set a
number of frames per second, without changing the actual speed of the game.

#### The Pattern

We applied the [**Game Loop** Pattern](https://gameprogrammingpatterns.com/game-loop.html). This pattern runs
continuously during gameplay. Each turn of the loop, it processes (polls) user input without blocking, updates the game
state, and renders the game. It tracks the passage of time to control the rate of gameplay.

#### Implementation

The following diagram shows how the pattern’s roles were mapped to the application classes.  
![Game Loop Pattern UML](/docs/images/GameLoopPatternUML.png)

The classes where this pattern was applied can be found in the following files:

- [Game](../src/main/java/com/g37/supa/Game.java)
- [State](../src/main/java/com/g37/supa/state/State.java)

#### Consequences

-By using this pattern, we were able to easily separate the tasks of getting the user input, update the game (step) and
render it in defined time intervals.

### MVC

#### Problem in context

We had to separate the data, interface and control of the game for organization’s sake and to have a more **reusable,
modular and easy to implement code**. This architectural pattern complies with the **Single Responsability Principle**
assuring the separation of functions between classes.

#### The Pattern

The MVC architectural pattern is a way to separate all the code in three elements, Model, View and Control. The Model
does not have dependencies, the View depends on the Model, and the Controller depends on both the Viewer and Model.

The model only represents the data. The view displays the model data, and sends user actions to the controller. The
controller provides model data to the view, and interprets user actions.

#### Implementation

This architectural pattern is divided in the following packages:
- [Model](../src/main/java/com/g37/supa/model)
- [View](../src/main/java/com/g37/supa/viewer)
- [Controller](../src/main/java/com/g37/supa/controller)

#### Consequences

-Front-end and back-end can be done simultaneously, and relacted actions are grouped making the code more organized.  
-The program is easy to modify and to test because the three elements are isolated from each other.

## Known Code Smells And Refactoring Suggestions

### Object Orientation abusers

#### Switch statments

- There are a few instances of switch cases across some files, mainly when handling Key Actions. They are stored in an
  enum, so we end up using switch statements in a few controllers to indicate what to do for each action. When switch
  statements are used, however, the switch operator performs a really simple task.

### Dispensables

#### Comments

- We just have a few comments explaining key concepts and methods. Overall not an important issue in this project.

#### Lazy class

The classes listed below don't do much in the current state of the code, however we chose to keep them in order to make
the code easily adaptable to use with a different GUI.

- **LevelElementViewer**
- **MenuElementViewer**

#### Data class

- **Text**: This class just contains a string and it's color settings.
- **Position**: This class contains the position of the cell.
- **Size**: It's used to store the size (in number of cells of width and height) of an element.
- **LevelElement**: This class consists of a position and a TextImage, which together represent an element.

### Bloaters

#### Large class

- **Level**: This class stands out in size, due to having a large amount of similar methods. For instance, we have a
  couple functions associated for each Level Element type, like getters and setters, but they all together amount to an
  over-sized Level class.

## Testing

Test coverage report:  
![Test Coverage Report](/docs/images/CoverageReport.png)  
[Mutation test report](/docs/pitest_report/index.html)

## Self-evaluation

- José Ferreira: 50%
- Lucas Santos: 50%
