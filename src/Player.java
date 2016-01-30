import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Entity {

    private Animation anim;

    private int speed = 5;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Bullet> deadBullets = new ArrayList<>();

    private long timeSinceLastFire = 0, lastTime = System.currentTimeMillis();
    private boolean canFire = true;

    public Player(Main game, Point position, Point velocity){
        super(game, position.x, position.y, velocity.x, velocity.y);
        anim = new Animation(100, Assets.player);
    }

    private void getInput(){
        yVelocity = 0;
        xVelocity = 0;


        if(game.getKeyInput().up || game.getKeyInput().w){
            yVelocity = -speed;
        }
        if(game.getKeyInput().down || game.getKeyInput().s){
           yVelocity = speed;
        }
        if(game.getKeyInput().left || game.getKeyInput().a){
           xVelocity = -speed;
        }
        if(game.getKeyInput().right || game.getKeyInput().d){
           xVelocity = speed;
        }

        if(game.getKeyInput().space && canFire){
            bullets.add(new Bullet(new Point((position.x+14),position.y), new Point(0, -15)));
            canFire = false;
            timeSinceLastFire = 0;
        }

    }

    @Override
    public void tick(){
        anim.tick();
        //Cool
        timeSinceLastFire += (System.currentTimeMillis() - lastTime);
        if(timeSinceLastFire > 150){
            canFire = true;
        }
        lastTime = System.currentTimeMillis();

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
        if(position.x+xVelocity >0 && position.x+xVelocity < 990){
            position.x += xVelocity;
        }
        if(position.y+yVelocity>0 && position.y+yVelocity<990){
            position.y += yVelocity;
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        BufferedImage currentFrame;
        currentFrame = anim.getCurrentFrame();
        return currentFrame;
    }
}
