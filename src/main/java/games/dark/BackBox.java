package games.dark;

import GDK.engine.*;

public class BackBox extends ScriptControl {
    private final double VALUECLOSE = 400;
    public boolean isStartMove;
    public boolean isClose;

    @Override
    public void awake() {
        gameObject.setViewOrder(-0.7);
    }

    @Override
    public void update(){
        if (!transform.isTranslate && isStartMove){
            if (isClose)
                invoke(() -> GameManager.instance.reloadGame(), 1100);
            isStartMove = false;
        }
    }
    public void startMove(Vector2 vector2) {
        startMove(vector2, false);
    }
    public void startMove(Vector2 vector2, boolean isClose) {
        this.isClose = isClose;
        isStartMove = true;
        transform.translate(vector2.increaseVector(VALUECLOSE), vector2, 10, Config.TRANSLATE_BACK_SPEED);
    }
}
