package GDK.engine;
import java.util.HashMap;
import java.util.Map;
import java.awt.MouseInfo;

public class Input {
    public static final Map<String, boolean[]> pressedKeys = new HashMap<>();
    public static final Map<String, Boolean> MOUSES = new HashMap<>();
    public static final String[] MOUSE_NAMES = new String[]{"PRIMARY", "MIDDLE", "SECONDARY"};
    public static boolean isClear;
    public static Vector2 mousePosition;
    public static Vector2 mousePositionInt;
    public static String currentKey="Null";
    public static boolean getKeyDown(String keyCode) {
        boolean result = pressedKeys.getOrDefault(keyCode, new boolean[]{false, false, false})[0];
        return result;
    }

    public static boolean getKey(String keyCode) {
        boolean[] values = pressedKeys.getOrDefault(keyCode, new boolean[]{false, false, false});
        boolean result = false;
        if (values[0] && !values[1]){
            pressedKeys.put(keyCode, new boolean[]{true, true, values[2]});
            result = true;
        }
        return result;
    }

    public static boolean getMouseButton(int index){
        boolean value = MOUSES.getOrDefault(MOUSE_NAMES[index], false);
        return value;
    }

    public static void updateMousePosition(double[] posStage){
        var mousePosPointer = MouseInfo.getPointerInfo().getLocation();
        Input.mousePosition = new Vector2((mousePosPointer.getX() - posStage[0]) / Screen.scale.x - Config.SPACEX,
                (mousePosPointer.getY() - posStage[1])  / Screen.scale.y - Config.SPACEY);
        Input.mousePositionInt = mousePosition.roundVector();
    }

    public static void clearReleasedKeys(){
        if (isClear) return;
        isClear = true;
        for (var code: pressedKeys.keySet()) {
            boolean[] values = Input.pressedKeys.getOrDefault(code, new boolean[]{false, false, false});
            Input.pressedKeys.put(code, new boolean[]{values[0], values[1], false});
        }
    }

    public static boolean getKeyUp(String keyCode) {
        boolean[] values = pressedKeys.getOrDefault(keyCode, new boolean[]{false, false, false});
        return values[2];
    }
}