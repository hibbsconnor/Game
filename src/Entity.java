import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private boolean isSolid = true; //entity solid by default
    private BufferedImage sprite;
    private Point position;
    private Point velocity;

    public Entity(BufferedImage sprite){
        this.sprite = sprite;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }
}
