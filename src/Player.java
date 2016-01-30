import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public Player(BufferedImage[] player, Point position, Point velocity){
        super(Assets.player, position, velocity);
    }

}
