import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private BufferedImage image;
    private Point velocity = new Point(0, -5);
    public Point position = new Point();

    //public static int ID = Main.rand.nextInt(10000);

    public Bullet(Point position){
        this.position.x = position.x;
        this.position.y = position.y;
        image = Assets.bullet;
    }

    public void render(Graphics g){
        g.drawImage(image, position.x, position.y, null);
    }

    public void tick(){
        position.y += velocity.y;
    }

}
