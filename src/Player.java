import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Entity {

    private Animation anim;

    private int speed = 5;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Bullet> deadBullets = new ArrayList<>();

    public Player(Main game, Point position, Point velocity){
        super(game, position, velocity);
        anim = new Animation(100, Assets.player);
    }

    private void getInput(){
        velocity.y = 0;
        velocity.x = 0;


        if(game.getKeyInput().up || game.getKeyInput().w){
            velocity.y = -speed;
        }
        if(game.getKeyInput().down || game.getKeyInput().s){
           velocity.y = speed;
        }
        if(game.getKeyInput().left || game.getKeyInput().a){
           velocity.x = -speed;
        }
        if(game.getKeyInput().right || game.getKeyInput().d){
           velocity.x = speed;
        }

        if(game.getKeyInput().space){
            bullets.add(new Bullet(position));
        }

    }

    @Override
    public void tick(){
        anim.tick();
        ArrayList<Bullet> deadBullets= new ArrayList<>();
        for(Bullet b : bullets){
            b.tick();
            if(b.position.y < 50) deadBullets.add(b);
        }
        for(Bullet dead : deadBullets){
            bullets.remove(dead);
        }

        getInput();
        move();
    }

    @Override
    public void render(Graphics g){

        for(Bullet b : bullets){
            b.render(g);
        }
        g.drawImage(getCurrentAnimationFrame(), position.x, position.y, 32, 32, null);
    }

    public void move(){
        position.x += velocity.x;
        position.y += velocity.y;
    }

    private BufferedImage getCurrentAnimationFrame(){
        BufferedImage currentFrame;
        currentFrame = anim.getCurrentFrame();
        return currentFrame;
    }
}
