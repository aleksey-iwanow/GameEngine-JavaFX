package games.dark;

import GDK.engine.*;
import GDK.engine.components.AudioSource;

public class Key extends Item implements Moveable {
    private Player player;
    private boolean spaceForMove;
    private AudioSource source;

    @Override
    public void awake() {
        source = new AudioSource("audio/pickUp.wav");
        player = GameManager.player;
        gameObject.animator.startAnimation("keyLoop");
    }

    public void update(){
        if (spaceForMove) return;
        try {
            if (posInGrid.equal(player.getEndOldPos())) {
                spaceForMove = true;
            }
        }
        catch (Exception ex){

        }
    }

    @Override
    public void move(){
        if (!spaceForMove) return;
        Vector2 dur = player.getEndOldPos().subtractVector(player.getEnd2OldPos());
        Vector2 offset = dur.increaseVector(Config.CELLSIZE);
        transform.translate(offset, dur, 10, Config.TRANSLATE_SPEED);
    }

    @Override
    public void pickUp(){
        super.pickUp();
        source.Play();
        Grid.getCell(this.posInGrid).setItem(null);
        transform.setSize(60);
        transform.move(10 ,10);
    }

    private double getSpace(){
        return (Config.CELLSIZE - transform.getSize().x) / 2;
    }

    @Override
    public Vector2 getPos(){
        return super.getPos().addVector(getSpace());
    }

    @Override
    public void setPos(){
        setPos(Grid.getRandomPos());
    }

    @Override
    public void setPos(Vector2 posInGrid){
        this.posInGrid = posInGrid;
        updatePosWithoutToGrid(getPos());

        Grid.getCell(this.posInGrid).setItem(this);
    }

    @Override
    public GameObject getGameObject() {
        return gameObject;
    }

}
