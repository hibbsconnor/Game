import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int DIM = 32;

    //Static Images
    public static BufferedImage background, asteroid, bullet;

    //Animation Arrays
    public static BufferedImage[] player;
    public static BufferedImage[] laser;

    public static void init(){
        //Assign Spritesheets to Animation Arrays
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/player.png"));
        SpriteSheet laserSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/laser.png"));
        //Initialize BufferedImage Arrays for Spritesheets
        player = new BufferedImage[4];
        laser = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            player[i] = playerSpriteSheet.crop(i, 0, DIM);
        }

        for(int i =0; i<4; i++){
            laser[i] = laserSpriteSheet.crop(i*(DIM*4),0,128,32);
        }



        //column    //row   //side length
        //grass  = background.crop(0, 0, DIM);
        asteroid = ImageLoader.loadImage("sprites/asteroid.png");
        background = ImageLoader.loadImage("backgrounds/space_background.png");
        bullet = ImageLoader.loadImage("sprites/bullet.png");
    }
}
