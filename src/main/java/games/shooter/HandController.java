package games.shooter;

import GDK.engine.*;
import GDK.engine.components.Transform;

public class HandController extends ScriptControl {
    Transform gun;
    Transform point;
    ShootController shootController;
    long delayTime=0;
    int reloadTime=500;

    @Override
    public void start() {
        gun = find("gun").transform;
        shootController = gun.gameObject.getScript(ShootController.class);
        point = find("pointShoot").transform;
    }

    @Override
    public void update() {
        rotateGun();
        if (Input.getMouseButton(0)){
            if (Time.current() > delayTime) {
                delayTime = Time.current()+reloadTime;
                shootController.shoot(point.position, gun.angle);
            }
        }
    }

    public void rotateGun(){
        Vector2 pos = gameObject.parent.transform.getCenterPosition();
        Vector2 posM = Input.mousePosition;
        double angle = Vector2.angleBetween(pos, posM);

        angle = Vector2.angleToDeg(angle);

        if (Vector2.distance(pos, posM) > 5) {
            transform.setAngle(angle);
            gun.setAngle(angle);
            point.setAngle(angle);
        }
    }
}
