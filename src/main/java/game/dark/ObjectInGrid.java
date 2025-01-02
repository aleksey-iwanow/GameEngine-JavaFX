package game.dark;

import GDK.engine.*;

import static game.dark.Grid.gridToPos;

public class ObjectInGrid extends ScriptControl {
    public Vector2 posInGrid;

    public void updatePos(Vector2 posInGrid){
        transform.setPos(gridToPos(posInGrid));
    }

    public void updatePosWithoutToGrid(Vector2 pos){
        transform.setPos(pos);
    }

    public void increasePosInGrid(Vector2 vec){
        posInGrid = posInGrid.addVector(vec);
    }

    public Vector2 getPos(){
        return posInGrid.increaseVector(Config.CELLSIZE);
    }

    public void updatePos(){
        updatePos(posInGrid);
    }
}
