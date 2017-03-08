import java.awt.*;

//entity superclass for all game objects
public abstract class Entity {

    protected boolean isSolid = true; //entity solid by default
    protected Point position = new Point();
    protected Point velocity = new Point();
    protected Rectangle bounds;
    protected Main game;//collision bounds

    public Entity(Point position, Point velocity){
        this.position = position;
        this.velocity = velocity;
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
