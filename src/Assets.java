import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int DIM = 32;

    public static BufferedImage grass, hay, tree, rock,
            dirt, water, diamond, sand,
            road1, road2, road3, road4;
    public static BufferedImage[] player;

    public static void init(){
        SpriteSheet background = new SpriteSheet(ImageLoader.loadImage("/textures/basicTileset.png"));
        SpriteSheet playerSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/textures/South_Park_Sprite_sheet.png"));

        player = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            player[i] = playerSpriteSheet.crop(i, 0, DIM);
        }

        //column    //row   //side length
        grass  = background.crop(0, 0, DIM);
        hay    = background.crop(1, 0, DIM);
        tree   = background.crop(2, 0, DIM);
        rock   = background.crop(3, 0, DIM);
        dirt   = background.crop(0, 1, DIM);
        water  = background.crop(1, 1, DIM);
        diamond= background.crop(2, 1, DIM);
        sand   = background.crop(3, 2, DIM);
        road1  = background.crop(0, 2, DIM);
        road2  = background.crop(1, 2, DIM);
        road3  = background.crop(0, 3, DIM);
        road4  = background.crop(1, 3, DIM);
    }
}
