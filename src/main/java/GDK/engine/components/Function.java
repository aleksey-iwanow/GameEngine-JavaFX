package GDK.engine.components;

import GDK.engine.Component;
import GDK.engine.Engine;
import GDK.engine.GameObject;
import GDK.engine.ScriptControl;

public class Function extends Component {
    public boolean update;
    public String className;
    public ScriptControl scControl;
    public Function(GameObject gameObject, String[] args){
        super(gameObject);
        update = Boolean.parseBoolean(args[0]);
        className = args[1];
    }

    public void active(){
        try {
            Class<?> clazz = Class.forName("game."+className);
            scControl = (ScriptControl) clazz.newInstance();
            scControl.init(gameObject, gameObject.transform);
            Engine.startThreadForObject(scControl);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
