import java.awt.image.BufferedImage;

/**
 * A class used to crop sprite sheets and tile sets into individual pictures for games
 * @author Connor Hibbs
 * @version 2016_0122
 */
public class SpriteSheet {

    /**
     * The tileset being used
     */
    private BufferedImage sheet;

    /**
     * Constructor - take the sheet and save it as a member variable
     * @param sheet The tileset or sprite sheet being used
     */
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /**
     * Crops the sprite sheet into one sprite, specified by coordinates (pixels) and width and height
     * @param x The x coordinate of the sprite (pixels)
     * @param y The y coordinate ot the sprite (pixels)
     * @param width Width of the sprite
     * @param height Height of the sprite
     * @return A cropped sub-image of the tileset
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }

    /**
     * Crops the sprite sheet into one sprite, specified by position in sheet and size of sprite
     * @param column The column the picture appears in
     * @param row The row the picture appears in
     * @param dim The side length of the square picture
     * @return A cropped sub-image of the tileset
     */
    public BufferedImage crop(int column, int row, int dim){
        return sheet.getSubimage(column * dim, row * dim, dim, dim);
    }
}
