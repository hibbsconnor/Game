import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private BufferedImage image;
    private Point velocity = new Point(0, 15);
    private Point position;

    public Bullet(Point position){
        this.position = position;
        image = Assets.bullet;
    }

    public void render(Graphics g){
        g.drawImage(image, position.x, position.y, null);
    }

    public void tick(){
        position.y += velocity.y;
    }
}
