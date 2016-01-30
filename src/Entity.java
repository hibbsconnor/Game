import java.awt.*;

public abstract class Entity {

    protected boolean isSolid = true; //entity solid by default
    protected Point position = new Point();
    protected float xVelocity, yVelocity;
    protected Rectangle bounds;
    protected Main game;//collision bounds

    public Entity(Main game, Point position, float xVelocity, float yVelocity){
        this.game = game;
        this.position = position;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public Entity(Main game, float xPosition, float yPosition, float xVelocity, float yVelocity){
        this.game = game;
        position.x = (int)xPosition;
        position.y = (int)yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

//    public Point getPosition() {
//        return position;
//    }
//
//    public void setPosition(Point position) {
//        this.position = position;
//    }
//
//    public Point getVelocity() {
//        return velocity;
//    }
//
//    public void setVelocity(Point velocity) {
//        this.velocity = velocity;
//    }
}
