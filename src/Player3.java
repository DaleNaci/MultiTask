import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player3 {

    private Image image;        // Image
    private double x;           // X Position
    private double y;           // Y Position
    private double velx;        // X Velocity
    private double vely;        // Y Velocity
    private double accelx;      // X Acceleration
    private double accely;      // Y Acceleration
    private double width;       // Width
    private double height;      // Height
    private Rectangle2D hitbox; // Hitbox

    public Player3() {
        image = new Image("game3/player.png");
        width = image.getWidth();
        height = image.getHeight();
        x = 450;
        y = 400 - height;
        velx = 0;
        vely = 0;
        accelx = 0;
        accely = 1;

    }

    // Getters & Setters
    public Image getImage() { return image; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getVelX() { return velx; }
    public void setVelX(double velx) { this.velx= velx; }
    public double getVelY() { return vely; }
    public void setVelY(double vely) { this.vely = vely; }
    public double getAccelX() { return accelx; }
    public void setAccelX(double accelx) { this.accelx = accelx; }
    public double getAccelY() { return accely; }
    public void setAccelY(double accely) { this.accely = accely; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public Rectangle2D getHitbox() { return hitbox; }
    public void setHitbox() { hitbox = new Rectangle2D(x, y, 41, 41); }

    // Move Functions
    public void moveX() {
        x += velx;
    }
    public void moveY() {
        y += vely;
    }
    public void changeVelX() {
        velx += accelx;
    }
    public void changeVelY() {
        vely += accely;
    }


}
