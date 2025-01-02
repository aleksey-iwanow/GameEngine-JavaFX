package games.dark;

import GDK.engine.*;

import java.util.ArrayList;

public class Enemy extends ObjectInGrid implements Moveable {

    public ArrayList<Eye> eyes = new ArrayList<>();

    @Override
    public void awake() {
        for (int i = 0; i < Tools.randomValue(9, 12); i++){
            Eye eye = instantiate("enemyEye.prefab", gameObject).getScript(Eye.class);
            debug(i);
            eyes.add(eye);
        }
    }

    @Override
    public void update() {
        if (posInGrid.equal(GameManager.player.posInGrid)){
            GameManager.instance.loseGame();
        }
    }

    @Override
    public Vector2 getPos(){
        return super.getPos().subtractVector(10);
    }

    @Override
    public void setPos(){
        setPos(Grid.getRandomPos());
    }

    @Override
    public void setPos(Vector2 posInGrid) {
        this.posInGrid = posInGrid;
        updatePosWithoutToGrid(getPos());
    }

    @Override
    public GameObject getGameObject() {
        return gameObject;
    }

    private Vector2 generateRandomVector(){
        var rand = new Vector2(
                Tools.randomValue(-1, 2),
                Tools.randomValue(-1, 2));
        if (rand.isZero())
            rand = generateRandomVector();
        var posNew = posInGrid.addVector(rand);
        if (Grid.checkBorder(posNew) == Border.ALLGOOD){
            return rand;
        }
        return generateRandomVector();
    }

    public Vector2 vectorMove(){
        Vector2 dir = GameManager.player.posInGrid.subtractVector(posInGrid);
        Vector2 vec;
        if (dir.isOneZeroAbs() && Grid.checkBorder(dir) == Border.ALLGOOD){
            vec = dir;
        }
        else
            vec = generateRandomVector();
        posInGrid = posInGrid.addVector(vec);

        return vec;
    }

    @Override
    public void move() {
        Vector2 vec = vectorMove();
        transform.translate(Grid.gridToPos(vec), vec, 10, Config.TRANSLATE_SPEED);
    }
}
