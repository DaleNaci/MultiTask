import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Enemy3 {

    private Image image;        // Image
    private double x;           // X Position
    private double velx;        // X Velocity
    private double y;           // Y Position
    private double width;       // Width
    private double height;      // Height
    private Rectangle2D hitbox; // Hitbox

    public Enemy3() {
        image = new Image("game3/enemy.png");
        width = image.getWidth();
        height = image.getHeight();
        x = 580;
        y = 450 - height;
        velx = -2;
    }

    // Getters & Setters
    public Image getImage() { return image; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getVelX() { return velx; }
    public void setVelX(double velx) { this.velx = velx; }
    public Rectangle2D getHitbox() { return hitbox; }
    public void setHitbox() { hitbox = new Rectangle2D(x, y, width, height); }

    // Move Functions
    public void moveX() {
        x += velx;
    }
    public void place() {
        x = (int)(Math.random() * 500) + 600;
    }

    // Reset Function
    public void reset() {
        x = 580;
    }

}
