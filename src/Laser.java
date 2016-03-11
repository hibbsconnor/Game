import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

/**
 * Created by mahonbd on 3/10/2016.
 */
public class Laser extends Entity {

    private Animation anim;
    public void tick(){
        position.x += velocity.x;
        position.y += velocity.y;
        //System.out.println("X: " + xVelocity + " - Y: " + yVelocity);
        //TODO Change to setting player to killed and put that logic in Player
        if(Collisions.rectCollision(new Rectangle(Main.player.position.x, Main.player.position.y, 32, 32),
                new Rectangle(position.x, position.y, 128, 32))){
            JOptionPane.showMessageDialog(null, "You are lose! Your score is: " + Main.score);
            System.exit(1);
        }
    }

    public void render(Graphics g){
        g.drawImage(getCurrentAnimationFrame(), position.x, position.y, 128, 32, null);
    }

    private BufferedImage getCurrentAnimationFrame() {
        return anim.getCurrentFrame();
    }

    public Laser(Point position, Point velocity){
        super(position, velocity);
        anim = new Animation(50, Assets.laser);
    }
}
