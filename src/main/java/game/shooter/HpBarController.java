package game.shooter;

import GDK.engine.*;

public class HpBarController extends ScriptControl {

    private final Vector2 startSize=Vector2.zero();

    public void start() {
        startSize.set(transform.size);
        transform.setPositionRelativeParent(true);
    }

    public void updateValue(double value){
        if (value<0)
            value = 0;
        transform.size.x = startSize.x*value;
    }
}
