package GDK.engine.components;

import GDK.engine.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import javax.swing.plaf.PanelUI;

public class Text extends Component {
    public String text;
    public double sizeFont;
    public Color color;
    public Label textLabel;
    public Font currentFont;


    public Text(GameObject gameObject, String[] args) {
        super(gameObject);
        text = args[0];
        sizeFont = Double.parseDouble(args[1]);
        color = Color.web(args[2]);

        textLabel = new Label();
        textLabel.setViewOrder(gameObject.getViewOrder());
        setFont("Arial", FontPosture.REGULAR, sizeFont);
        gameObject.nodes.add(textLabel);
    }

    public void setFont(int sizeFont){
        setFont(currentFont.getName(), FontPosture.REGULAR, currentFont.getSize());
    }

    public void setFont(String fontName, FontPosture fontPosture, double sizeFont){
        this.sizeFont = sizeFont;
        currentFont = Font.font(fontName, fontPosture, sizeFont);
        textLabel.setFont(currentFont);
    }

    @Override
    public void update() {
        textLabel.setText(text);

        textLabel.setLayoutX((gameObject.transform.position.x)*Screen.scale.x);
        textLabel.setLayoutY((gameObject.transform.position.y)*Screen.scale.y);
        textLabel.setRotate(gameObject.transform.getGlobalAngle());
    }
}
