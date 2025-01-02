package GDK.engine.components;

import GDK.engine.*;
import javafx.scene.image.*;
import javafx.scene.text.Text;

import java.io.File;

public class ImageComponent extends Component {
    public String pathImage;
    public double opacity;
    public ImageView imageView;
    Image imageDefault;
    public ImageComponent(GameObject gameObject, String pathImage, String opacity){
        super(gameObject);
        this.pathImage = Config.PATH_PROJECT + pathImage;
        this.opacity = Double.parseDouble(opacity);
        imageDefault = createImage(this.pathImage);
        imageView = new ImageView(imageDefault);
        imageView.setSmooth(false);
        imageView.setOpacity(this.opacity);
        imageView.setCache(true);

        gameObject.nodes.add(imageView);
    }

    public static Image createImage(String pathImage){
        return new Image(
                new File(pathImage).toURI().toString());
    }

    public void setImageView(String pathImage){
        this.pathImage = Config.PATH_PROJECT + pathImage;
        setImageView(createImage(this.pathImage));
    }

    public void setImageView(Image image){
        imageView.setImage(image);
    }

    public void setToDefault(){
        setImageView(imageDefault);
    }

    @Override
    public void update() {
        imageView.setLayoutX((gameObject.transform.position.x)*Screen.scale.x);
        imageView.setLayoutY((gameObject.transform.position.y)*Screen.scale.y);
        imageView.setRotate(gameObject.transform.getGlobalAngle());
        imageView.setFitWidth((gameObject.transform.size.x)*Screen.scale.x);
        imageView.setFitHeight((gameObject.transform.size.y)*Screen.scale.y);
    }
}