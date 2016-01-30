import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int DIM = 32;

    public static BufferedImage background, asteroid;
    public static BufferedImage[] player;

    public static void init(){
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/sprites/player.png"));

        player = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            player[i] = playerSpriteSheet.crop(i, 0, DIM);
        }

        //column    //row   //side length
        //grass  = background.crop(0, 0, DIM);
        asteroid = ImageLoader.loadImage("sprites/asteroid.png");
        background = ImageLoader.loadImage("backgrounds/space_background.png");

    }
}
