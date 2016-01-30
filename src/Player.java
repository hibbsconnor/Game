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
//        int xPos = (int)(x - handler.getGameCamera().getxOffset());
//        int yPos = (int)(y - handler.getGameCamera().getyOffset());
        g.drawImage(getCurrentAnimationFrame(), position.x, position.y, 32, 32, null);
    }

    public void move(){

    }

    private BufferedImage getCurrentAnimationFrame(){
        BufferedImage currentFrame;
//        if(xMove < 0){
//            currentFrame = animLeft.getCurrentFrame();
//        } else if(xMove > 0){
//            currentFrame = animRight.getCurrentFrame();
//        } else if(yMove < 0){
//            currentFrame = animUp.getCurrentFrame();
//        } else if(yMove > 0){
//            currentFrame = animDown.getCurrentFrame();
//        } else {
//            currentFrame = player_still;
//        }
        currentFrame = anim.getCurrentFrame();
        return currentFrame;
    }
}
