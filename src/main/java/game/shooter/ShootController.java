package game.shooter;

import GDK.engine.*;

import java.util.Random;

public class ShootController extends ScriptControl {

    public void shoot(Vector2 posSpawn, double angle){
        gameObject.animator.startAnimation("shoot");
        for (int i = 0; i < Config.bulletsCount; i++) {
            Random random = new Random();
            createBullet(posSpawn, random.nextDouble(angle-Config.spreadAngle,angle+Config.spreadAngle));
        }
    }

    public void createBullet(Vector2 posSpawn, double angle){
        double angleRad = Vector2.angleToRad(angle);

        Vector2 vectorMove = new Vector2(Math.cos(angleRad), Math.sin(angleRad));
        GameObject inst = instantiate("bullet.prefab", angle);
        inst.transform.setCenterPosition(posSpawn);
        Bullet bullet = (Bullet) inst.getScript(Bullet.class);
        Vector2 endPos = getEndPos(posSpawn, vectorMove);
        bullet.init(vectorMove, endPos, Config.damage);
    }

    public Vector2 getEndPos(Vector2 posSpawn, Vector2 vectorMove){
        double len = Vector2.distance(posSpawn, Input.mousePosition)
                + new Random().nextDouble(-Config.spreadPos, Config.spreadPos);
        return posSpawn.addVector(vectorMove.increaseVector(len));
    }

}
