import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

/**
 * Created by mahonbd
 */
public class Asteroid extends Entity{

    private BufferedImage image = Assets.asteroid;
    public Asteroid(Main game, Point position, Point velocity){
        super(game, position, velocity);

    }

    public void tick(){
        position.x += velocity.x;
        position.y += velocity.y;

    }

    public void render(Graphics g){
        g.drawImage(image,position.x, position.y,null);
    }
}
