import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player3 {

    private Image image;        // Image
    private double x;           // X Position
    private double y;           // Y Position
    private double vely;        // Y Velocity
    private double accely;      // Y Acceleration
    private double width;       // Width
    private double height;      // Height
    private Rectangle2D hitbox; // Hitbox
    private boolean canJump;    // Ability to jump

    public Player3() {
        image = new Image("game3/player.png");
        width = image.getWidth();
        height = image.getHeight();
        x = 350 - (width / 2);
        y = 450 - height;
        vely = 0;
        accely = .1;
        canJump = true;
    }

    // Getters & Setters
    public Image getImage() { return image; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getVelY() { return vely; }
    public void setVelY(double vely) { this.vely = vely; }
    public double getAccelY() { return accely; }
    public void setAccelY(double accely) { this.accely = accely; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public Rectangle2D getHitbox() { return hitbox; }
    public void setHitbox() { hitbox = new Rectangle2D(x, y, width, height); }
    public boolean getCanJump() { return canJump; }
    public void setCanJump(boolean canJump) { this.canJump = canJump; }

    // Move Functions
    public void moveY() {
        y += vely;
    }
    public void changeVelY() {
        vely += accely;
    }
    public void jump() {
        if (canJump)
            vely = -4;
        canJump = false;
    }
    public void reset() {
        x = 350 - (width / 2);
        y = 450 - height;
        vely = 0;
        canJump = true;
    }


}
