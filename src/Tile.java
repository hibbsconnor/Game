import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class to hold background tiles. Has basic collision ability with
 * isSolid() method. Override in extended tile to allow collision testing
 */
public class Tile {

    /*TO ADD NEW TILE
     * Add the color to ColorUtils.
     * Make a new class for the tile in the Tiles package
     * Add that tile to Tile.java below
     * Add the tile type as an int in Assets, and add the tile to the name search in Assets
     */
    public static Tile[] tiles = new Tile[256];
    //The following is how to create new tiles
    //public static Tile grassTile = new GrassTile(Assets.GRASS);

    //Class
    protected BufferedImage texture;
    protected final int id;
    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }
    //draw the tile with set width and height at point x,y
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    //is the tile solid?
    public boolean isSolid(){
        return false;
    }

    public int getID(){
        return id;
    }
}
