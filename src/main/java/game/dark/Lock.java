package game.dark;

import GDK.engine.GameObject;
import GDK.engine.Vector2;
import lombok.Builder;

public class Lock extends Item{
    @Override
    public void update(){
        if (!GameManager.isStartGame)
            return;
        if (GameManager.key.pickUp && GameManager.player.posInGrid.equal(posInGrid)){
            GameManager.instance.newLevel();
        }
    }

    public void setPos(Vector2 posInGrid) {
        this.posInGrid = posInGrid;
        updatePosWithoutToGrid(getPos());

    }

}
