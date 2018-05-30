import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    // Define Global Variables
    private GraphicsContext gc;
    private Canvas canvas;
    private AnimateObjects animate;
    private double canvas_width;
    private double canvas_height;
    private boolean start;

    // Object Declaration
    private Player1 player1;
    private Enemy1 enemy1;

    // Timer
    private Timer timer = new Timer();
    private TimerTask task;
    private int seconds;


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
            gc.drawImage(player1.getImage(), player1.getX(), player1.getY());

            // Changing position through a constant velocity
            player1.moveX();
            player1.moveY();

            // Changing Enemy position
            if (start || player1.getHitbox().intersects(enemy1.getHitbox())) {
                enemy1.setNew_x((Math.random()) * (301 - enemy1.getWidth()));
                enemy1.setNew_y((Math.random()) * (301 - enemy1.getHeight()));
                while (enemy1.getNew_x() > (player1.getX() - 10) && (enemy1.getNew_x() - 10) < player1.getX() + player1.getWidth()) {
                    enemy1.setNew_x((Math.random()) * (301 - enemy1.getWidth()));
                }
                while (enemy1.getNew_y() > (player1.getY() - 10) && (enemy1.getNew_y() - 10) < player1.getVelY() + player1.getHeight()) {
                    enemy1.setNew_y((Math.random()) * (301 - enemy1.getHeight()));
                }
                enemy1.setX(enemy1.getNew_x());
                enemy1.setY(enemy1.getNew_y());
            }
            gc.drawImage(enemy1.getImage(), enemy1.getX(), enemy1.getY());
            enemy1.setHitbox();


            // Bounds
            if (player1.getX() < 0)                                          // Left Bounds
                player1.setX(0);
            if (player1.getX() > (canvas_width / 2) - player1.getWidth())    // Right Bounds
                player1.setX((canvas_width / 2) - player1.getWidth());
            if (player1.getY() < 0)                                          // Upper Bounds
                player1.setY(0);
            if (player1.getY() > (canvas_height / 2) - player1.getHeight())  // Lower Bounds
                player1.setY((canvas_height / 2) - player1.getHeight());

            player1.setHitbox();

            // Setting "start" variable to false
            start = false;

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

        // Setting Start variable to true
        start = true;

        // Object creation
        player1 = new Player1();
        enemy1 = new Enemy1();

        // Timer
        seconds = 0;
        task = new TimerTask() {
            int max_seconds = 5;

            public void run() {
                if (seconds <= max_seconds) {
                    System.out.println(seconds);
                    seconds++;
                }
                else
                    cancel();
            }
        };
        timer.schedule(task, 0, 1000);


        // Checks for Key Presses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W: player1.setVelY(-2); break; // UP
                    case A: player1.setVelX(-2); break; // LEFT
                    case S: player1.setVelY(2);  break; // DOWN
                    case D: player1.setVelX(2);  break; // RIGHT
                }
            }
        });

        // Checks for Key Releases
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W: player1.setVelY(0); break; // UP
                    case A: player1.setVelX(0); break; // LEFT
                    case S: player1.setVelY(0); break; // DOWN
                    case D: player1.setVelX(0); break; // RIGHT
                }
            }
        });

        // STANDARD CODE

        animate = new AnimateObjects();
        animate.start();

        stage.show();
    }


}
