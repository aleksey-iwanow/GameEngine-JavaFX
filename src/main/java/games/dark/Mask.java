package games.dark;

import GDK.engine.*;

public class Mask extends ScriptControl {
    private double startSize;
    private double speed = 50;
    private double delay;

    @Override
    public void awake(){
        startSize = transform.size.x;
        gameObject.setViewOrder(-0.54);
        transform.setPositionRelativeParent(true);
        startCoroutine(this::updateSize, 100, true);
    }

    public void updateSize(){
        delay = speed;
        //transform.setSize(startSize+delay);
    }
}
