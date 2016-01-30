import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private BufferedImage image;
    private Point velocity = new Point();
    public Point position = new Point();

    //public static int ID = Main.rand.nextInt(10000);

    public Bullet(Point position, Point velocity){
        this.position.x = position.x;
        this.position.y = position.y;
        this.velocity.x = velocity.x;
        this.velocity.y = velocity.y;
        image = Assets.bullet;
    }

    public void render(Graphics g){
        g.drawImage(image, position.x, position.y, null);
    }

    public void tick(){
        position.y += velocity.y;
    }

}
