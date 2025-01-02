package game.dark;

import GDK.engine.*;

public class Button extends ScriptControl {

    @Override
    public void onHoverOnce(){
        gameObject.image.setImageView("img/buttonHover.png");
    }

    @Override
    public void onHover(){
        if (Input.getMouseButton(0)){
            GameManager.instance.startGame();
        }
    }

    @Override
    public void onHoverExit(){
        gameObject.image.setImageView("img/button.png");
    }
}
