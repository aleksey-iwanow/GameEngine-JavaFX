package game.snake;

import GDK.engine.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Snake extends ScriptControl {
    private Vector2 vec = Vector2.zero();
    public ArrayList<Tail> tails = new ArrayList<>();
    public final int MARGIN = 25;
    public int H_BORDER = Screen.height()-Screen.height()% MARGIN - MARGIN;
    public int W_BORDER = Screen.width()-Screen.width()% MARGIN - MARGIN;
    private Apple apple;
    private Line left, right, up, down;


    @Coroutine
    public void moveSnake(){

        Tail old = null;
        for (Tail cell: tails) {
            cell.SetOldPos();
            if (old != null){
                cell.gameObject.transform.position = old.oldPos;
            }
            old = cell;
        }
        gameObject.transform.move(vec.increaseVector(MARGIN),1);
        checkCollision();
    }

    public void checkCollision(){
        if (apple == null) return;
        if (gameObject.transform.position.x < MARGIN
                || gameObject.transform.position.y < MARGIN
                || gameObject.transform.position.x > W_BORDER-MARGIN
                || gameObject.transform.position.y > H_BORDER-MARGIN)
            reset();
        else if (apple.gameObject.collider.intersects(gameObject.collider)){
            destroy(apple);
            tails.add((Tail) instantiate("snake.prefab", tails.get(tails.size()-1).oldPos).getScript(Tail.class));
            spawnApple();
        }
    }

    public void reset(){
        for (int i = 1; i < tails.size(); i++) {
            destroy(tails.get(i).gameObject);
            tails.remove(i);
        }
        gameObject.transform.position = new Vector2((W_BORDER+MARGIN) / 2, (H_BORDER+MARGIN) / 2);
    }

    public void spawnApple() {
        System.out.println(Engine.gameObjects.size());
        apple = (Apple)instantiate("apple.prefab", getRandomPos()).getScript(Apple.class);
    }

    public Vector2 getRandomPos(){
        Vector2 pos = Screen.size()
                .subtractVector(MARGIN*2)
                .divideVector(MARGIN)
                .roundVector()
                .getRandomIntRange()
                .increaseVector(MARGIN);
        return pos;
    }

    @Override
    public void update(){
        System.out.println(gameObject.transform.position);
        H_BORDER = Screen.height()-Screen.height() % MARGIN - MARGIN;
        W_BORDER = Screen.width()-Screen.width() % MARGIN - MARGIN;
        left.setEndY(H_BORDER);

        right.setStartX(W_BORDER);
        right.setEndX(W_BORDER);
        right.setEndY(H_BORDER);

        down.setStartY(H_BORDER);
        down.setEndX(W_BORDER);
        down.setEndY(H_BORDER);

        up.setEndX(W_BORDER);

        if (Input.getKeyDown("W") && vec.y!=1)
            vec = new Vector2(0, -1);
        if (Input.getKeyDown("S") && vec.y!=-1)
            vec = new Vector2(0, 1);
        if (Input.getKeyDown("A") && vec.x!=1)
            vec = new Vector2(-1, 0);
        if (Input.getKeyDown("D") && vec.x!=-1)
            vec = new Vector2(1, 0);
    }

    @Override
    public void start() {
        left = Draw.line(MARGIN, MARGIN, MARGIN, MARGIN, 3, Color.PURPLE);
        up = Draw.line(MARGIN, MARGIN, MARGIN, MARGIN, 3, Color.PURPLE);
        right = Draw.line( MARGIN, MARGIN, MARGIN, MARGIN, 3, Color.PURPLE);
        down = Draw.line(MARGIN, MARGIN, MARGIN, MARGIN,3, Color.PURPLE);

        tails.add((Tail) gameObject.getScript(Tail.class));
        apple = (Apple) find("apple").getScript(Apple.class);
        startCoroutine(this::moveSnake, 100, false);
    }
}
