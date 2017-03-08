import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Asteroid extends Entity{

    private BufferedImage image = Assets.asteroid;
    
    //initialize with asteroid's position
    public Asteroid(Point position, Point velocity){
        super(position, velocity);
    }
    
    //refreshes the current asteroid object
    public void tick(){
        position.x += velocity.x;
        position.y += velocity.y;
        //System.out.println("X: " + xVelocity + " - Y: " + yVelocity);
        //Check if the player collides with this object, and if so, game over
        if(Collisions.rectCollision(new Rectangle(Main.player.position.x, Main.player.position.y, 32, 32),
                                    new Rectangle(position.x, position.y, 64, 64))){
            JOptionPane.showMessageDialog(null, "You are lose! Your score is: " + Main.score);
            System.exit(1);
        }
        //check if bullet hit this object, if so, destroy this object
        for(Bullet b : Main.player.bullets) {
            if (Collisions.rectCollision(new Rectangle(b.position.x, b.position.y, 4, 8),
                    new Rectangle(position.x, position.y, 64, 64))) {
                Main.deadAsteroids.add(this);
                Main.player.deadBullets.add(b);
                Main.score ++;
            }
        }
        //remove bullets
        for(Bullet b : Main.player.deadBullets){
            Main.player.bullets.remove(b);
        }
    }
    //render the asteroid image
    public void render(Graphics g){
        g.drawImage(image,position.x, position.y,null);
    }

}
