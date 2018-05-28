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

    // Define Global Variables
    private GraphicsContext gc;
    private Canvas canvas;
    private AnimateObjects animate;
    private double canvas_width;
    private double canvas_height;

    // Game 1 Variables
    private double x_p1;       // X Position of Player 1
    private double y_p1;       // Y Position of Player 1
    private Image player1;     // Player 1 Image
    private double velx_p1;    // X Velocity of Player 1
    private double vely_p1;    // Y Velocity of Player 1
    private double p1_width;   // Width of Player 1
    private double p1_height;  // Height of Player 1
    private Image enemy_g1;    // Enemy
    private double x_e1;       // X Position of Enemy
    private double y_e1;       // Y Position of Enemy
    private double change_x;     // New X Position of Enemy
    private double change_y;     // New Y Position of Enemy

    private boolean temp_bool;


    // Repeated actions
    public class AnimateObjects extends AnimationTimer {
        public void handle(long now)
        {
            gc.clearRect(0, 0, canvas_width, canvas_height);
            gc = canvas.getGraphicsContext2D();


            /* ----------------------------------------
               -----------------Game 1-----------------
               ---------------------------------------- */

            // Setting Background to light red
            gc.setFill(Color.rgb(242, 190, 190, .5));
            gc.fillRect(0, 0, 300, 300);

            // Creating Player 1
            player1 = new Image("game1/player.JPG");
            gc.drawImage(player1, x_p1, y_p1);

            // Setting the dimension variables of Player 1
            p1_width = player1.getWidth();
            p1_height = player1.getHeight();

            // Changing position through a constant velocity
            x_p1 += velx_p1;
            y_p1 += vely_p1;

            // Creating Enemy
            enemy_g1 = new Image("game1/5.png", 50, 50, true, true);


            // Changing Enemy position
            if (temp_bool) {
                change_x = (Math.random()) * (301 - enemy_g1.getWidth());
                change_y = (Math.random()) * (301 - enemy_g1.getHeight());
                while (change_x > x_p1 && change_x < x_p1 + p1_width) {
                    change_x = (Math.random()) * (301 - enemy_g1.getWidth());
                }
                while (change_y > y_p1 && change_y < y_p1 + p1_height) {
                    change_y = (Math.random()) * (301 - enemy_g1.getHeight());
                }
                x_e1 = change_x;
                y_e1 = change_y;

                temp_bool = false;
            }
            gc.drawImage(enemy_g1, x_e1, y_e1);


            // Bounds
            if (x_p1 < 0)                                // Left Bounds
                x_p1 = 0;
            if (x_p1 > (canvas_width / 2) - p1_width)    // Right Bounds
                x_p1 = (canvas_width / 2) - p1_width;
            if (y_p1 < 0)                                // Upper Bounds
                y_p1 = 0;
            if (y_p1 > (canvas_height / 2) - p1_height)  // Lower Bounds
                y_p1 = (canvas_height / 2) - p1_height;

            Rectangle2D hitbox_p1 = new Rectangle2D(x_p1, y_p1, 50, 50);


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
        canvas_width = canvas.getWidth();
        canvas_height = canvas.getHeight();

        //GAME 1
        x_p1 = 125;
        y_p1 = 125;

        // TEMP
        temp_bool = true;

        // Checks for Key Presses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W: vely_p1 = -2; break;  // UP
                    case A: velx_p1 = -2; break;  // LEFT
                    case S: vely_p1 =  2; break;  // DOWN
                    case D: velx_p1 =  2; break;  // RIGHT
                }
            }
        });

        // Checks for Key Releases
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W: vely_p1 = 0; break;  // UP
                    case A: velx_p1 = 0; break;  // LEFT
                    case S: vely_p1 = 0; break;  // DOWN
                    case D: velx_p1 = 0; break;  // RIGHT
                }
            }
        });

        // STANDARD CODE

        animate = new AnimateObjects();
        animate.start();

        stage.show();
    }


}
