import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Enemy1 {

    private Image image;         // Image
    private double x;            // X Position
    private double y;            // Y Position
    private double new_x;        // New X Position
    private double new_y;        // New Y Position
    private double width;        // Width
    private double height;       // Height
    private Rectangle2D hitbox;  // Hitbox

    public Enemy1() {
        image = new Image("game1/5.png", 50, 50, true, true);
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
    public double getNew_x() {
        return new_x;
    }
    public void setNew_x(double new_x) {
        this.new_x = new_x;
    }
    public double getNew_y() {
        return new_y;
    }
    public void setNew_y(double new_y) {
        this.new_y = new_y;
    }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public Rectangle2D getHitbox() {
        return hitbox;
    }
    public void setHitbox() {
        hitbox = new Rectangle2D(x, y, width, height);
    }



}
