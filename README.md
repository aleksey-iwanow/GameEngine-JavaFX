# How to use?
Open the Config.java file and change the settings of the path to your game project.
``` java
public static final String PATH_PROJECT = "src\\main\\java\\games\\dark\\"; // путь до проекта
public static final String NAME_SCENE = PATH_PROJECT+"res/main.scene"; // путь до файла сцены 
```
Main.java - script to launch the game

# How to create project?
Every project should have at least this basic structure ->
- main.scene
- Game.java

### What is main.scene?
This is a file that includes a list of game objects.

#### Example (comments must be removed when creating):
``` Java
test1            // name
None             // local image path 
100,100          // position x,y
100,100          // size w, y
0                // angle (degrees)
1                // opacity
False            // enable collision
True             // Add to list of objects (basically if you use this object then it is always True)
Color box^,^string=color^,^#03AB0C$Function^,^update^;^function^,^True^;^package.YourFunc       // Components (more about that later)
None             // parents name
~
test2
None
100,100
100,100
0
1
False
True
Color box^,^string=color^,^#03AB0C
None
```

#### Components?
These are some features of the object.
Basic components are stored in the components folder.
At the moment there are ->
![sdsd](https://github.com/aleksey-iwanow/GameEngine-JavaFX/blob/master/screenshots_/img.png)

## How to write code?
``` Java
package games.snake;

import GDK.engine.*;

public class Test extends ScriptControl {
    @Override
    public void start() {
      // actions that occur at startup
    }

    @Override
    public void update() {
      // actions that happen every frame
    }
}
```
