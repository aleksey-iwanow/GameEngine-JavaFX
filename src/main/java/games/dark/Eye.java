package games.dark;

import GDK.engine.*;

public class Eye extends ScriptControl {
    @Override
    public void awake() {
        transform.setPositionRelativeParent(true);

        transform.setSize(new Vector2(20, Tools.randomValue(20, 35)));
        startCoroutine(this::setRandomPos, Tools.randomValue(900, 1800), true);
    }

    private void setRandomPos(){
        Vector2 randPos = new Vector2(
                Tools.randomValue(0, Config.CELLSIZE),
                Tools.randomValue(0, Config.CELLSIZE));

        transform.localPosition.set(randPos);
    }
}
