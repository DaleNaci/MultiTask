import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Enemy2 {

    private Image image;        // Image
    private double x;           // X Position
    private double y;           // Y Position
    private double velx;        // X Velocity
    private double vely;        // Y Velocity
    private double width;       // Width
    private double height;      // Height
    private Rectangle2D hitbox; // Hitbox
    private boolean hit;        // Hit Delay
    private double framecount;  // Frame Count

    public Enemy2() {
        image = new Image("game2/enemy.png");
        x = 425;
        y = 100;
        velx = (Math.random() < .5 ? 3 : -3);   // Picks randomly, either 3 or -3
        vely = -3;
        width = image.getWidth();
        height = image.getHeight();
        hit = false;
        framecount = 0;
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
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public Rectangle2D getHitbox() { return hitbox; }
    public void setHitbox() { hitbox = new Rectangle2D(x, y, 41, 41); }
    public boolean getHit() { return hit; }
    public void setHit(boolean hit) { this.hit = hit; }
    public double getFramecount() { return framecount; }
    public void setFramecount(double framecount) { this.framecount = framecount; }

    // Move Methods
    public void moveX() { x += velx; }
    public void moveY() { y += vely; }

    public void reset() {
        x = 425;
        y = 100;
        vely = -3;
        velx = (Math.random() < .5 ? 3 : -3);   // Picks randomly, either 3 or -3
        hit = false;
        framecount = 0;
    }


}
