import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private Animation anim;

    public Player(Main game, Point position, Point velocity){
        super(game, position, velocity);
        bounds.x = 2; //create collision bounds with 2 pixel inset
        bounds.y = 2;
        bounds.width = 28;
        bounds.height = 28;
    }

    private void getInput(){
//        xMove = 0;
//        yMove = 0;
//
//        if(handler.getKeyManager().up || handler.getKeyManager().w){
//            yMove = -speed;
//        }
//        if(handler.getKeyManager().down || handler.getKeyManager().s){
//            yMove = speed;
//        }
//        if(handler.getKeyManager().left || handler.getKeyManager().a){
//            xMove = -speed;
//        }
//        if(handler.getKeyManager().right || handler.getKeyManager().d){
//            xMove = speed;
//        }

    }

    @Override
    public void tick(){
        anim.tick();

        getInput();
        move();
    }

    @Override
    public void render(Graphics g){
        g.drawImage(getCurrentAnimationFrame(), position.x, position.y, 32, 32, null);
    }

    public void move(){

    }

    private BufferedImage getCurrentAnimationFrame(){
        BufferedImage currentFrame;
        currentFrame = anim.getCurrentFrame();
        return currentFrame;
    }
}
