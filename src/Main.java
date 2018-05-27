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

public class Main extends Application implements EventHandler<InputEvent> {

    // Define global variables
    GraphicsContext gc;
    Canvas canvas;
    AnimateObjects animate;
    int x;
    int y;

    // Repeated actions
    public class AnimateObjects extends AnimationTimer {
        public void handle(long now)
        {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Rectangle2D rect1 = new Rectangle2D(400, 100, 100, 100);
            gc.fillRect(400, 100, 100, 100);
        }
    }

    public static void main(String[] args)
    {
        launch();
    }

    // Initial stage creation (not init)
    public void start(Stage stage)
    {
        stage.setTitle("MultiTask");
        Group root = new Group();
        canvas = new Canvas(800, 400);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        gc = canvas.getGraphicsContext2D();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, this);

        animate = new AnimateObjects();
        animate.start();

        stage.show();
    }

    public void handle(final InputEvent event)
    {
        // Checking for Key Logs
        if (event instanceof KeyEvent) {
            if (((KeyEvent)event).getCode() == KeyCode.LEFT)
                x-=1;
            if (((KeyEvent)event).getCode() == KeyCode.RIGHT)
                x+=1;
            if (((KeyEvent)event).getCode() == KeyCode.UP)
                y+=1;
            if (((KeyEvent)event).getCode() == KeyCode.DOWN)
                y-=1;
        }
    }



}
