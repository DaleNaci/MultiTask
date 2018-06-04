import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player1 {

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
        x = 125;
        y = 125;
        image = new Image("game1/player1.JPG");
        width = image.getWidth();
        height = image.getHeight();
    }

    // Getters & Setters
    public Image getImage() {
        return image;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getVelX() {
        return velx;
    }
    public void setVelX(double velx) {
        this.velx = velx;
    }
    public double getVelY() {
        return vely;
    }
    public void setVelY(double vely) {
        this.vely = vely;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public Rectangle2D getHitbox() { return hitbox; }
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
