import java.awt.Point;
import java.awt.Rectangle;

/**
 * Created by mahonbd
 */
public class Collisions {
	//return if two rectangles overlap
    public static boolean rectCollision(Rectangle r1, Rectangle r2) {
        return !(r2.x > r1.x + r1.width || r2.y > r1.y + r1.height || r2.x + r2.width < r1.x || r2.y + r2.height < r1.y);
    }
    //return if a point overlaps with a rectangle
    public static boolean pointRectCollision(Point p, Rectangle rect){
        return p.x>=rect.x&&p.x<=rect.x+rect.width&&p.y>=rect.y&&p.y<=rect.y+rect.height;
    }
}
