package games.dark;

import GDK.engine.GameObject;
import GDK.engine.Vector2;

public interface Moveable {
    void move();
    void setPos();

    void setPos(Vector2 posInGrid);

    GameObject getGameObject();
}
