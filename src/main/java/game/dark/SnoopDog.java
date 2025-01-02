package game.dark;

import GDK.engine.ScriptControl;
import GDK.engine.components.Animation;

public class SnoopDog extends ScriptControl {
    @Override
    public void awake(){
        int countFrames = 58;
        String[] paths = new String[countFrames];
        for (int i = 0; i < countFrames; i++)
            paths[i] = "img/frames/frame_"+ i +"_delay-0.04s.gif";
        Animation anim = new Animation(gameObject, paths, true, true, false, 40, "snoop");
        gameObject.animator.animations.add(anim);

    }
}
