import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity{

    private BufferedImage image;
    //private Point velocity = new Point();
    //public Point position = new Point();

    //public static int ID = Main.rand.nextInt(10000);

    public Bullet(Point position, Point velocity){
        super(position, velocity);
        image = Assets.bullet;
    }
    
    //render the object at its position
    public void render(Graphics g){
        g.drawImage(image, position.x, position.y, null);
    }
    
    //update the object position
    public void tick(){
        position.y += velocity.y;
        position.x += velocity.x;
    }

}
