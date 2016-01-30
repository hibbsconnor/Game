import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private Animation anim;

    public Player(Main game, Point position, Point velocity){
        super(game, position, velocity);
        anim = new Animation(4, Assets.player);
    }

    private void getInput(){
//        xMove = 0;
//        yMove = 0;
//

        if(game.getKeyInput().up || game.getKeyInput().w){
            //yMove = -speed;
        }
        if(game.getKeyInput().down || game.getKeyInput().s){
           // yMove = speed;
        }
        if(game.getKeyInput().left || game.getKeyInput().a){
           // xMove = -speed;
        }
        if(game.getKeyInput().right || game.getKeyInput().d){
           // xMove = speed;
        }

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
