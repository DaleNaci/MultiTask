import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.*;
import javafx.scene.input.*;

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
    private Player2 player2;
    private Enemy2 enemy2;


    // Repeated actions
    public class AnimateObjects extends AnimationTimer {
        public void handle(long now)
        {
            // Clear and set Graphics Context
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
                while (enemy1.getNew_y() > (player1.getY() - 10) && (enemy1.getNew_y() - 10) < player1.getY() + player1.getHeight()) {
                    enemy1.setNew_y((Math.random()) * (301 - enemy1.getHeight()));
                }
                enemy1.setX(enemy1.getNew_x());
                enemy1.setY(enemy1.getNew_y());
                enemy1.setCountdown(6);
                enemy1.setFramecount(0);
            }

            // Change the image of Enemy every second
            if (enemy1.getFramecount() % 60 == 0) {
                enemy1.changeImage();
            }
            enemy1.setFramecount(enemy1.getFramecount() + 1);

            // Draw Enemy and Create the Hitbox
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

            // Create Hitbox
            player1.setHitbox();



            /* ----------------------------------------
               -----------------Game 2-----------------
               ---------------------------------------- */



            // Setting Background to light red
            gc.setFill(Color.rgb(179, 194, 219, .5));
            gc.fillRect(300, 0, 300, 300);

            // Draw Player 2 and Create the Hitbox
            gc.drawImage(player2.getImage(), player2.getX(), player2.getY());
            player2.setHitbox();

            // Moving with a constant velocity
            player2.moveX();

            // Bounds
            if (player2.getX() < (canvas_width / 2))                    // Left Bounds
                player2.setX(canvas_width / 2);
            if (player2.getX() > (canvas_width - player2.getWidth()))   // Right Bounds
                player2.setX(canvas_width - player2.getWidth());

            // Draw Enemy 2 and Create the Hitbox
            gc.drawImage(enemy2.getImage(), enemy2.getX(), enemy2.getY());
            enemy2.setHitbox();

            // Bounds
            if (enemy2.getX() < (canvas_width / 2))
                enemy2.setVelX(enemy2.getVelX() * -1);
            if (enemy2.getX() > (canvas_width - enemy2.getWidth()))
                enemy2.setVelX(enemy2.getVelX() * -1);
            if (enemy2.getY() < 0)
                enemy2.setVelY(enemy2.getVelY() * -1);
            if (enemy2.getY() > ((canvas_height / 2) - enemy2.getHeight()))
                enemy2.setVelY(enemy2.getVelY() * -1);

            // Move
            enemy2.moveX();
            enemy2.moveY();

            // Changes direction when hitting Player 2
            if (player2.getHitbox().intersects(enemy2.getHitbox()) && !enemy2.getHit()) {
                enemy2.setVelX((Math.random() * 3) + 1);
                enemy2.setVelY(enemy2.getVelY() * -1);
                enemy2.setHit(true);
            }

            // Add one to framecount
            if (enemy2.getHit())
                enemy2.setFramecount(enemy2.getFramecount() + 1);

            // Checking to reset framecount
            if (enemy2.getFramecount() >= 20) {
                enemy2.setHit(false);
                enemy2.setFramecount(0);
            }



            /* ----------------------------------------
               -----------------Game 3-----------------
               ---------------------------------------- */







            /* ---------------------------------------
               -------------Miscellaneous-------------
               --------------------------------------- */

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

        // Canvas Definitions
        gc = canvas.getGraphicsContext2D();
        canvas_width = canvas.getWidth();
        canvas_height = canvas.getHeight();

        // Setting Start variable to true
        start = true;

        // Object creation
        player1 = new Player1();
        enemy1 = new Enemy1();
        player2 = new Player2();
        enemy2 = new Enemy2();

        // Checks for Key Presses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W:     player1.setVelY(-2); break; // UP P1
                    case A:     player1.setVelX(-2); break; // LEFT P1
                    case S:     player1.setVelY(2);  break; // DOWN P1
                    case D:     player1.setVelX(2);  break; // RIGHT P1
                    //PLAYER 2
                    case LEFT:  player2.setVelX(-2); break; // LEFT P2
                    case RIGHT: player2.setVelX(2);  break; // RIGHT P2
                }
            }
        });

        // Checks for Key Releases
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    //PLAYER 1
                    case W:     player1.setVelY(0); break; // UP P1
                    case A:     player1.setVelX(0); break; // LEFT P1
                    case S:     player1.setVelY(0); break; // DOWN P1
                    case D:     player1.setVelX(0); break; // RIGHT P1
                    //PLAYER 2
                    case LEFT:  player2.setVelX(0); break; // LEFT P2
                    case RIGHT: player2.setVelX(0); break; // RIGHT P2

                }
            }
        });

        // STANDARD CODE
        animate = new AnimateObjects();
        animate.start();
        stage.show();
    }


}
