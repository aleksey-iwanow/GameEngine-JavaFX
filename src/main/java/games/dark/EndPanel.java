package games.dark;

import GDK.engine.ScriptControl;

public class EndPanel extends ScriptControl {
    public void awake(){
        instantiate("snoop.prefab");
        instantiate("textend.prefab");
    }
}
