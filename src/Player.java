import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player {

    private Image image;        // Image
    private double x;           // X Position
    private double y;           // Y Position
    private double velx;        // X Velocity
    private double vely;        // Y Velocity
    private double width;       // Width
    private double height;      // Height
    private Rectangle2D hitbox; // Hitbox

    public Player()
    {
        width = image.getWidth();
        height = image.getHeight();
    }

    // Getters and Setters
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

}
