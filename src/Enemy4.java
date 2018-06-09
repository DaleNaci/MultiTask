import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Enemy4 {

    private Image image;
    private double x;
    private double y;
    private double velx;
    private double width;
    private double height;
    private Rectangle2D hitbox;

    public Enemy4() {
        image = new Image("game4/enemy.png");
        width = image.getWidth();
        height = image.getHeight();
        x = 250;
        y = 450 - (height / 2);
        velx = 0;
    }

    public Image getImage() { return image; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getVelX() { return velx; }
    public void setVelX(double velx) { this.velx = velx; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    public Rectangle2D getHitbox() { return hitbox; }
    public void setHitbox() { hitbox = new Rectangle2D(x, y, width, height); }

}
