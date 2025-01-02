package games.shooter;

import GDK.engine.*;

public class Crock extends Enemy {
    Vector2 vecStopping;
    Vector2 posStopping1;

    @Override
    public void start() {
        super.start();
        hpBar.transform.localPosition.set(new Vector2(0, -15));
        gameObject.animator.startAnimation("walk");
        posStopping1 = find("pos_1_house_for_croke").transform.position;
        Vector2 posStopping2 = find("pos_2_house_for_croke").transform.position;
        vecStopping = posStopping2.subtractVector(posStopping1);
    }

    @Override
    public void update() {
        super.update();
        CheckDistance();
    }

    private void CheckDistance(){
        if (!isPointAboveLineStopping(gameObject.transform.getCenterPosition())){
            dirVec.zeroing();
        }
    }

    public boolean isPointAboveLineStopping(Vector2 vec) {
        double a = vecStopping.y / vecStopping.x;
        return vec.y > a * (vec.x - posStopping1.x) + posStopping1.y;
    }

    public void takeHealth(double damage) {
        super.takeHealth(damage);
        if (health <= 0){
            dead();
        }
    }
}
