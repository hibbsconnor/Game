import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected boolean isSolid = true; //entity solid by default
    protected Point position;
    protected Point velocity;
    protected Rectangle bounds;
    protected Main game;//collision bounds

    public Entity(Main game, Point position, Point velocity){
        this.game = game;
        this.position = position;
        this.velocity =  velocity;
    }

    public Entity(float xPosition, float yPosition, float xVelocity, float yVelocity){
        this.position.x = (int)xPosition;
        this.position.y = (int)yPosition;
        this.velocity.x = (int)xVelocity;
        this.velocity.y = (int)yVelocity;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

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
