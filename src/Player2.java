import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;

public class Player2{

    private Image image;        // Image
    private double x;           // X Position
    private double y;           // Y Position
    private double velx;        // X Velocity
    private double vely;        // Y Velocity
    private double width;       // Width
    private double height;      // Height
    private Rectangle2D hitbox; // Hitbox

    public Player2()
    {
        super();
        image = new Image("game2/player2.png");
        x = 450 - (width / 2);
        y = 500;
    }


}
