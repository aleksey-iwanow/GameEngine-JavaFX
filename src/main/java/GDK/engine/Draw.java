package GDK.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Draw {
    public static Line line(double startX, double startY, double endX, double endY){
        return line(new Vector2(startX, startY), new Vector2(endX, endY), 3, Color.BLACK);
    }

    public static Line line(double startX, double startY, double endX, double endY, double width, Color color){
        return line(new Vector2(startX, startY), new Vector2(endX, endY), width, color);
    }

    public static Line line(Vector2 start, Vector2 end, double width, Color color){
        Line ln = new Line(start.x, start.y , end.x, end.y);
        ln.setFill(color);
        ln.setStrokeWidth(width);
        Main.addWidget(ln);
        return ln;
    }

    public static Vector2 getScale(){
        return new Vector2(Main.groupWidgets.getScaleX(), Main.groupWidgets.getScaleY());
    }
}
