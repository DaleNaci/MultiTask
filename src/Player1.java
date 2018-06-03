import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player1 extends Player{

    private Image image;         // Image
    private double x;            // X Position
    private double y;            // Y Position
    private double velx;         // X Velocity
    private double vely;         // Y Velocity
    private double width;        // Width
    private double height;       // Height
    private Rectangle2D hitbox;  // Hitbox

    // Default constructor
    public Player1()
    {
        super();
        x = 125;
        y = 125;
        image = new Image("game1/player1.JPG");
        width = image.getWidth();
        height = image.getHeight();
    }

    // Getters & Setters
    public void setHitbox() {
        hitbox = new Rectangle2D(x, y, 50, 50);
    }

    // Moving methods
    public void moveX() {
        x += velx;
    }
    public void moveY() {
        y += vely;
    }

}
