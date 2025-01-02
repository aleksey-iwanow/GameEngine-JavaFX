package game.snake;

import GDK.engine.*;

public class Tail extends ScriptControl {
    public Vector2 oldPos;

    public void SetOldPos(){
        oldPos = gameObject.transform.getPos();
    }
}
