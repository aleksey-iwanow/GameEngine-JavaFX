package game.dark;

import GDK.engine.*;

public class Body extends ScriptControl {
    @Override
    public void awake() {
        transform.setPositionRelativeParent(true);
    }

    public void turnInTheDir(double x){
        if (x == 0) return;
        gameObject.image.imageView.setScaleX(x);
    }
}
