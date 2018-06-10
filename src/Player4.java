import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player4 {

    private Image image;
    private double x;
    private double y;
    private double vely;
    private double width;
    private double height;
    private Rectangle2D hitbox;

    public Player4() {
        image = new Image("game4/player.png");
        width = image.getWidth();
        height = image.getHeight();
        x = 30;
        y = 450 - (height / 2);
        vely = 0;
    }

    // Getters & Setters
    public Image getImage() { return image; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getVelY() { return vely; }
    public void setVelY(double vely) { this.vely = vely; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    public Rectangle2D getHitbox() { return hitbox; }
    public void setHitbox() { hitbox = new Rectangle2D(x, y, width, height * 2 / 3); }

    // Other Functions
    public void moveY() { y += vely; }

    // Reset Function
    public void reset() {
        y = 450 - (height / 2);
        vely = 0;
    }

}
