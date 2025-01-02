package games.shooter;

import GDK.engine.*;

import java.util.ArrayList;
import java.util.Random;

public class Bullet extends ScriptControl {
    private Vector2 dir = Vector2.zero();
    private Vector2 endPos = Vector2.zero();
    private boolean vecEndMoreThis;
    private boolean isExp;
    private int power;
    private final int POWER_START=3100;
    private final int POWER_END=3500;
    private double damage;

    public void init(Vector2 dir, Vector2 endPos, double damage){
        power = new Random().nextInt(POWER_START, POWER_END);
        this.dir.set(dir);
        this.endPos.set(endPos);
        this.damage = damage;
        this.vecEndMoreThis = endPos.x >= transform.getCenterPosition().x;
    }

    @Override
    public void update() {
        transform.move(dir.increaseVector(Time.deltaTime()), power);
        checkExp();
    }

    private boolean posMoreEnd(){
        return vecEndMoreThis ? transform.getCenterPosition().x > endPos.x : transform.getCenterPosition().x < endPos.x;
    }

    private void checkExp(){
        if (!isExp && posMoreEnd()){
            collisionCheck(collisionGameObjects);
            explosive();
        }
    }

    public void explosive(){
        isExp = true;
        GameObject gm = instantiate("explosive.prefab");
        gm.transform.setCenterPosition(transform.getCenterPosition());
        destroy(gameObject);
    }

    private void collisionCheck(ArrayList<GameObject> collisions){
        for (int i = 0; i < collisions.size(); i++) {
            GameObject collision = collisions.get(i);
            if (collision == null) return;
            Crock crock = (Crock)collision.getScript(Crock.class);
            if (crock != null){
                (crock).takeHealth(damage);
                explosive();
            }
        }
    }
}
