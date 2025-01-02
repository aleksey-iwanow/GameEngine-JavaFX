package games.dark;

import GDK.engine.*;

import java.util.ArrayList;

import static games.dark.Grid.gridToPos;

public class Player extends ObjectInGrid {
    public Vector2[] oldPosInGrid;
    private ArrayList<Body> bodys;

    @Override
    public void awake() {
        bodys = findAllWithScript(Body.class);
    }

    public void playerStartGame(){
        playerStartGame(Grid.getRandomPos());
    }
    public void playerStartGame(Vector2 pInGrid){
        oldPosInGrid = new Vector2[10];
        posInGrid = pInGrid;
        updatePos();
        newRoad();
    }

    @Override
    public void update() {
        if (transform.isTranslate || !GameManager.isStartGame)
            return;

        move();
    }

    private void addPosInOld(Vector2 posInGrid){
        for (int i = oldPosInGrid.length-1; i > 0; i--) {
            oldPosInGrid[i-1] = oldPosInGrid[i];
        }
        oldPosInGrid[oldPosInGrid.length -1] = posInGrid;
    }

    private void newRoad(){
        newRoad(posInGrid);
    }
    private void newRoad(Vector2 posInGrid) {
        Grid.createRoad(posInGrid, getEndOldPos());
    }

    private void updateBodys(double x){
        for (Body body: bodys) {
            body.turnInTheDir(x);
        }
    }

    public Vector2 getEndOldPos(){
        return oldPosInGrid[oldPosInGrid.length-1];
    }

    public Vector2 getEnd2OldPos(){
        return oldPosInGrid[oldPosInGrid.length-2];
    }


    private Vector2 getVecMove(){
        Vector2 vec = Vector2.zero();
        if (Input.getKey("W"))
            vec.y = -1;
        else if (Input.getKey("S"))
            vec.y = 1;
        else if (Input.getKey("A"))
            vec.x = -1;
        else if (Input.getKey("D"))
            vec.x = 1;

        return vec;
    }

    private void move() {
        Grid.checkCollisionCell(posInGrid);
        Vector2 vec = getVecMove();
        updateBodys(vec.x);
        Border check = Grid.checkBorder(posInGrid);

        switch (check){
            case Border.RIGHT ->{
                posInGrid.x -= Config.GRIDSIZE.x;
                transform.setPos(gridToPos(posInGrid));
                newRoad(posInGrid);
            }
            case Border.LEFT -> {
                posInGrid.x += Config.GRIDSIZE.x;
                transform.setPos(gridToPos(posInGrid));
                newRoad(posInGrid);
            }
            case Border.UP -> {
                posInGrid.y -= Config.GRIDSIZE.y;
                transform.setPos(gridToPos(posInGrid));
                newRoad(posInGrid);
            }
            case Border.DOWN -> {
                posInGrid.y += Config.GRIDSIZE.y;
                transform.setPos(gridToPos(posInGrid));
                newRoad(posInGrid);
            }
        }

        if (!vec.isZero()) {
            Vector2 newPos = posInGrid.addVector(vec);
            if (Grid.posBusy(Grid.convertWithBorderPos(newPos)))
            {
                addPosInOld(posInGrid);
                posInGrid = newPos;
                newRoad();
                transform.translate(vec.increaseVector(Config.CELLSIZE), vec, 10, Config.TRANSLATE_SPEED);

                GameManager.key.move();
            }
            GameManager.moveOthers();
        }
    }
}
