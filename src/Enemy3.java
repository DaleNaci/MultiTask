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
    }

}
