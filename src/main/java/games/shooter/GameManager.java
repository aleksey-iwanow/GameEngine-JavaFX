package games.shooter;

import GDK.engine.*;
import GDK.engine.components.*;

import java.util.Random;

public class GameManager extends ScriptControl {
    private Text textField;
    private Vector2 posSpawn1;
    private Vector2 posSpawn2;

    @Override
    public void start() {
        posSpawn1 = find("posSpawn1").transform.position;
        posSpawn2 = find("posSpawn2").transform.position;
        textField = find("text").getComponent(Text.class);
        startCoroutine(this::spawnEnemy, 2000, true);
    }

    public void spawnEnemy(){
        instantiate("crock.prefab",
                new Vector2(posSpawn1.x, new Random().nextDouble(posSpawn1.y, posSpawn2.y)));
    }


    @Override
    public void update() {
        textField.text = Time.getFps()+"|fps";
    }
}
