import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
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

    // Define global variables
    GraphicsContext gc;
    Canvas canvas;
    AnimateObjects animate;
    int x_p1;
    int y_p1;
    Image player1;
    double velx_p1;
    double vely_p1;




    // Repeated actions
    public class AnimateObjects extends AnimationTimer {
        public void handle(long now)
        {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());



            // BOUNDS
            Rectangle2D bounds2 = new Rectangle2D(300, 0, 300, 300);
            gc.fillRect(300, 0, 300, 300);

            gc = canvas.getGraphicsContext2D();



            // GAME 1
            player1 = new Image("game1/player.JPG");
            gc.drawImage(player1, x_p1, y_p1);
            x_p1 += velx_p1;
            y_p1 += vely_p1;

            Rectangle2D hitbox_player1 = new Rectangle2D(x_p1, y_p1, 50, 50);


        }
    }

    public static void main(String[] args)
    {
        launch();
    }

    // Initial stage creation (not init)
    public void start(Stage stage)
    {
        // STANDARD CODE
        stage.setTitle("MultiTask");
        Group root = new Group();
        canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // NON-STANDARD CODE
        gc = canvas.getGraphicsContext2D();



        //GAME 1
        x_p1 = 0;
        y_p1 = 0;


        // Checks for Key Presses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W: vely_p1 = -3; break;
                    case A: velx_p1 = -3; break;
                    case S: vely_p1 =  3; break;
                    case D: velx_p1 =  3; break;
                }
            }
        });

        // Checks for Key Releases
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W: vely_p1 = 0; break;
                    case A: velx_p1 = 0; break;
                    case S: vely_p1 = 0; break;
                    case D: velx_p1 = 0; break;
                }
            }
        });

        // STANDARD CODE

        animate = new AnimateObjects();
        animate.start();

        stage.show();
    }


}
