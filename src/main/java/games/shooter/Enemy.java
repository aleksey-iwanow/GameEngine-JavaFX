package games.shooter;

import GDK.engine.ScriptControl;
import GDK.engine.Time;
import GDK.engine.Vector2;

public class Enemy extends ScriptControl {
    public Vector2 dirVec = new Vector2(-1, 0);
    public double health=100;
    public double maxHealth=health;
    public HpBarController hpBar;
    public double speed=100;
    public GameManager gameManager;
    public boolean isDead;

    @Override
    public void start() {
        hpBar = instantiate("hpBar.prefab", gameObject).getScript(HpBarController.class);
        gameManager = find("gameManager").getScript(GameManager.class);
    }

    @Override
    public void update() {
        if (isDead) return;
        move();
    }

    public void move(){
        if (dirVec.isZero())
            return;
        transform.move(dirVec.increaseVector(Time.deltaTime()), speed);
    }

    public void takeHealth(double damage) {
        health -= damage;
        hpBar.updateValue(health/maxHealth);
    }

    public void dead(){
        destroy(hpBar);
        gameObject.collision = false;
        isDead=true;
        gameObject.animator.startAnimation("dead");
    }
}
