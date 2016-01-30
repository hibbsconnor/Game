import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.JOptionPane;

/**
 * Created by mahonbd
 */
public class Asteroid extends Entity{

    private BufferedImage image = Assets.asteroid;

    public Asteroid(Main game, Point position, float xVelocity, float yVelocity){
        super(game, position.x, position.y, xVelocity, yVelocity);
    }

    public void tick(){
        position.x += xVelocity;
        position.y += yVelocity;
        //System.out.println("X: " + xVelocity + " - Y: " + yVelocity);
        //OKAY
        if(Collisions.rectCollision(new Rectangle(Main.player.position.x, Main.player.position.y, 32, 32),
                                    new Rectangle(position.x, position.y, 64, 64))){
            JOptionPane.showMessageDialog(null, "You are lose! Your score is: " + Main.score);
            System.exit(1);
        }
        for(Bullet b : Main.player.bullets) {
            if (Collisions.rectCollision(new Rectangle(b.position.x, b.position.y, 4, 8),
                    new Rectangle(position.x, position.y, 64, 64))) {
                Main.deadAsteroids.add(this);
                Main.player.deadBullets.add(b);
                Main.score ++;
            }
        }

        for(Bullet b : Main.player.deadBullets){
            Main.player.bullets.remove(b);
        }
    }

    public void render(Graphics g){
        g.drawImage(image,position.x, position.y,null);
    }

}
