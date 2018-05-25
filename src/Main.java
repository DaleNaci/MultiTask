import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;

public class Main extends Application {

    Canvas canvas;
    AnimateObjects animate;

    public class AnimateObjects extends AnimationTimer {
        public void handle(long now)
        {

        }
    }

    public static void main(String[] args) { launch(); }

    public void start(Stage stage)
    {
        stage.setTitle("MultiTask");
        Group root = new Group();
        canvas = new Canvas(800, 400);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        animate = new AnimateObjects();
        animate.start();

        stage.show();
    }



}
