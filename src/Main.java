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

public class Main extends Application {

    // Define Global Variables
    private GraphicsContext gc;
    private Canvas canvas;
    private AnimateObjects animate;
    private double canvas_width;
    private double canvas_height;
    private boolean start;

    // Creating Player 1
    private Player1 player1;

    private Image enemy_g1;         // Enemy
    private double x_e1;            // X Position of Enemy
    private double y_e1;            // Y Position of Enemy
    private double change_x;        // New X Position of Enemy
    private double change_y;        // New Y Position of Enemy
    private Rectangle2D hitbox_p1;  // Hitbox of Player 1
    private Rectangle2D hitbox_e1;  // Hitbox of Enemy 1


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
            // System.out.println(player1.getVelY());

            // Creating Enemy
            enemy_g1 = new Image("game1/5.png", 50, 50, true, true);

            hitbox_e1 = new Rectangle2D(x_e1, y_e1, enemy_g1.getWidth(), enemy_g1.getHeight());

            // Changing Enemy position
            if (start || player1.getHitbox().intersects(hitbox_e1)) {
                change_x = (Math.random()) * (301 - enemy_g1.getWidth());
                change_y = (Math.random()) * (301 - enemy_g1.getHeight());
                while (change_x > (player1.getX() - 10) && (change_x - 10) < player1.getX() + player1.getWidth()) {
                    change_x = (Math.random()) * (301 - enemy_g1.getWidth());
                }
                while (change_y > (player1.getY() - 10) && (change_y - 10) < player1.getVelY() + player1.getHeight()) {
                    change_y = (Math.random()) * (301 - enemy_g1.getHeight());
                }
                x_e1 = change_x;
                y_e1 = change_y;
            }
            gc.drawImage(enemy_g1, x_e1, y_e1);
            hitbox_e1 = new Rectangle2D(x_e1, y_e1, enemy_g1.getWidth(), enemy_g1.getHeight());


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

        //Object creation
        player1 = new Player1();

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
