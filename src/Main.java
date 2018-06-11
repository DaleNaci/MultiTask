import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private double game_width;
    private double game_height;
    private boolean start;
    private Integer score;
    private double framecount;
    private boolean lose;

    // Object Declaration For Game
    private Player1 player1;
    private Enemy1 enemy1;
    private Player2 player2;
    private Enemy2 enemy2;
    private Player3 player3;
    private Enemy3 enemy3;
    private Player4 player4;
    private Enemy4 enemy4;

    // Miscellaneous Objects
    private Text scoreboard;
    private Button restart;


    // Repeated actions
    public class AnimateObjects extends AnimationTimer {
        public void handle(long now)
        {
            // Clear and set Graphics Context
            gc.clearRect(0, 0, game_width, game_height);
            gc = canvas.getGraphicsContext2D();


            /* ----------------------------------------
               -----------------Game 1-----------------
               ---------------------------------------- */

            // Setting Background to light red
            gc.setFill(Color.rgb(242, 190, 190, .5));
            gc.fillRect(0, 0, 300, 300);

            // Creating Player 1
            gc.drawImage(player1.getImage(), player1.getX(), player1.getY());

            if (!lose) {
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
                    try {
                        enemy1.changeImage();
                    } catch (IllegalArgumentException e) {
                        lose = true;
                    }
                }
                enemy1.setFramecount(enemy1.getFramecount() + 1);
            }

            // Draw Enemy and Create the Hitbox
            gc.drawImage(enemy1.getImage(), enemy1.getX(), enemy1.getY());
            enemy1.setHitbox();

            // Bounds
            if (player1.getX() < 0)                                          // Left Bounds
                player1.setX(0);
            if (player1.getX() > (game_width / 2) - player1.getWidth())    // Right Bounds
                player1.setX((game_width / 2) - player1.getWidth());
            if (player1.getY() < 0)                                          // Upper Bounds
                player1.setY(0);
            if (player1.getY() > (game_height / 2) - player1.getHeight())  // Lower Bounds
                player1.setY((game_height / 2) - player1.getHeight());

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

            if (!lose) {
                // Moving with a constant velocity
                player2.moveX();
            }

            // Bounds for Player 2
            if (player2.getX() < (game_width / 2))                    // Left Bounds
                player2.setX(game_width / 2);
            if (player2.getX() > (game_width - player2.getWidth()))   // Right Bounds
                player2.setX(game_width - player2.getWidth());

            // Draw Enemy 2 and Create the Hitbox
            gc.drawImage(enemy2.getImage(), enemy2.getX(), enemy2.getY());
            enemy2.setHitbox();

            // Bounds for Enemy 2
            if (enemy2.getX() < (game_width / 2))                  // Left Wall
                enemy2.setVelX(enemy2.getVelX() * -1);
            if (enemy2.getX() > (game_width - enemy2.getWidth()))  // Right Wall
                enemy2.setVelX(enemy2.getVelX() * -1);
            if (enemy2.getY() < 0)                                 // Top Wall
                enemy2.setVelY(enemy2.getVelY() * -1);

            // Game Over
            if (enemy2.getY() > ((game_height / 2) - enemy2.getHeight())) {
                lose = true;
            }

            if (!lose) {
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
            }



            /* ----------------------------------------
               -----------------Game 3-----------------
               ---------------------------------------- */


            // Setting Background to light purple
            gc.setFill(Color.rgb(210, 165, 226, .5));
            gc.fillRect(300, 300, 300, 300);

            // Draw Player 3 and create hitbox
            gc.drawImage(player3.getImage(), player3.getX(), player3.getY());
            player3.setHitbox();

            if (!lose) {
                // Moving and Jumping
                if (player3.getY() <= 450 - player3.getHeight() || player3.getVelY() != 0)
                    player3.moveY();
                if (player3.getY() <= 450 - player3.getHeight())
                    player3.changeVelY();
                else {
                    player3.setVelY(0);
                    player3.setCanJump(true);
                }
            }

            // Draw Enemy 3 and create hitbox
            gc.drawImage(enemy3.getImage(), enemy3.getX(), enemy3.getY());
            enemy3.setHitbox();

            if (!lose) {
                // Move Enemy 3
                enemy3.moveX();

                // Place Enemy 3
                if (enemy3.getX() <= 300)
                    enemy3.place();
            }

            // Game Over Code
            if (player3.getHitbox().intersects(enemy3.getHitbox())) {
                lose = true;
            }



            /* ----------------------------------------
               -----------------Game 4-----------------
               ---------------------------------------- */


            // Setting Background to light green
            gc.setFill(Color.rgb(188, 226, 197, .5));
            gc.fillRect(0, 300, 300, 300);

            // Draw Player 4 and create hitbox
            gc.drawImage(player4.getImage(), player4.getX(), player4.getY());
            player4.setHitbox();

            if (!lose) {
                // Move Player 4
                player4.moveY();
            }

            // Bounds
            if (player4.getY() <= 300)
                player4.setY(300);
            if (player4.getY() >= 600 - player4.getHeight())
                player4.setY(600 - player4.getHeight());

            // Draw Enemy 4 and create hitbox
            if (!enemy4.getHit())
                gc.drawImage(enemy4.getImage(), enemy4.getX(), enemy4.getY());
            enemy4.setHitbox();

            if (!lose) {
                // Move Enemy 4
                enemy4.moveX();

                // Change Hit to true if Enemy 4 is hit
                if (player4.getHitbox().intersects(enemy4.getHitbox()))
                    enemy4.setHit(true);

                // After a while, bring Enemy 4 back
                if (enemy4.getX() <= -1 * (Math.random() * 500 + 200)) {
                    enemy4.place();
                    enemy4.setHit(false);
                }
            }

            // Game Over Code
            if (enemy4.getX() <= 0 && !enemy4.getHit()) {
                lose = true;
            }

            /* ----------------------------------------
               -----------------Footer-----------------
               ---------------------------------------- */

            // Setting Background to light gray
            gc.setFill(Color.rgb(230, 230, 230, .5));
            gc.fillRect(0, 601, 600, 39);

            // Black Line
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 600, 600, 1);

            if (!lose) {
                // Keeping Score
                framecount++;
                if (framecount % 60 == 0)
                    score++;

                scoreboard.setText(score.toString());
                scoreboard.setX(10);
                scoreboard.setY(629);
            }


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
        canvas = new Canvas(600, 640);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Canvas Definitions
        gc = canvas.getGraphicsContext2D();
        game_width = 600;
        game_height = 600;

        // Setting Start variable to true
        start = true;

        // Setting Score to 0
        score = 0;

        // Setting Lose variable to false
        lose = false;

        // Object Creation for Game
        player1 = new Player1();
        enemy1 = new Enemy1();
        player2 = new Player2();
        enemy2 = new Enemy2();
        player3 = new Player3();
        enemy3 = new Enemy3();
        player4 = new Player4();
        enemy4 = new Enemy4();

        // Object Creation for Score
        scoreboard = new Text();

        scoreboard.setText("Hello World");
        scoreboard.setX(10);
        scoreboard.setY(615);
        scoreboard.setFont(new Font(24));
        root.getChildren().add(scoreboard);


        // Draw the ground for Game 3
        Line line = new Line(300, 450, 600, 450);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(4);
        root.getChildren().addAll(line);


        // Checks for Key Presses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    // PLAYER 1
                    case W:     player1.setVelY(-2);   break; // UP P1
                    case A:     player1.setVelX(-2);   break; // LEFT P1
                    case S:     player1.setVelY(2);    break; // DOWN P1
                    case D:     player1.setVelX(2);    break; // RIGHT P1
                    // PLAYER 2
                    case LEFT:  player2.setVelX(-2);   break; // LEFT P2
                    case RIGHT: player2.setVelX(2);    break; // RIGHT P2
                    // PLAYER 3
                    case SPACE: player3.jump();        break; // JUMP P3
                    // PLAYER 4
                    case UP:    player4.setVelY(-2.5); break; // UP P4
                    case DOWN:  player4.setVelY(2.5);  break; // DOWN P4
                    case R:     restart();             break; // RESTART
                }
            }
        });

        // Checks for Key Releases
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                switch ((event).getCode()) {
                    // PLAYER 1
                    case W:     player1.setVelY(0); break; // UP P1
                    case A:     player1.setVelX(0); break; // LEFT P1
                    case S:     player1.setVelY(0); break; // DOWN P1
                    case D:     player1.setVelX(0); break; // RIGHT P1
                    // PLAYER 2
                    case LEFT:  player2.setVelX(0); break; // LEFT P2
                    case RIGHT: player2.setVelX(0); break; // RIGHT P2
                    // PLAYER 4
                    case UP:    player4.setVelY(0); break; // UP P4
                    case DOWN:  player4.setVelY(0); break; // DOWN P4
                }
            }
        });

        // STANDARD CODE
        animate = new AnimateObjects();
        animate.start();
        stage.show();
    }

    public void restart() {
        player1.reset();
        enemy1.reset();
        player2.reset();
        enemy2.reset();
        player3.reset();
        enemy3.reset();
        player4.reset();
        enemy4.reset();
        score = 0;
        framecount = 0;
        lose = false;
    }

}