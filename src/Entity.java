import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private boolean isSolid = true; //entity solid by default
    private BufferedImage sprite;
    private BufferedImage[] spriteArray;
    private Point position;
    private Point velocity;

    public Entity(BufferedImage sprite, Point position, Point velocity){
        this.sprite = sprite;
        this.position = position;
        this.velocity =  velocity;
    }

    public Entity(BufferedImage[] spriteArray, Point position, Point velocity){
        this.spriteArray = spriteArray;
        this.position = position;
        this.velocity =  velocity;
    }

    public Entity(BufferedImage sprite, float xPosition, float yPosition, float xVelocity, float yVelocity){
        this.sprite = sprite;
        this.position.x = (int)xPosition;
        this.position.y = (int)yPosition;
        this.velocity.x = (int)xVelocity;
        this.velocity.y = (int)yVelocity;
    }

    public Entity(BufferedImage[] spriteArray, float xPosition, float yPosition, float xVelocity, float yVelocity){
        this.spriteArray = spriteArray;
        this.position.x = (int)xPosition;
        this.position.y = (int)yPosition;
        this.velocity.x = (int)xVelocity;
        this.velocity.y = (int)yVelocity;
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

    public BufferedImage getSprite() {
        return sprite;
    }

    public BufferedImage[] getSpriteArray() {
        return spriteArray;
    }
}
