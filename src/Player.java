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

    private Main game;

    public Player(Main game, Point position, Point velocity){
        super(position, velocity);
        this.game = game;
        anim = new Animation(100, Assets.player);
    }

    private void getInput(){
        velocity = new Point(0, 0);

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

        if(game.getKeyInput().space && canFire){
            bullets.add(new Bullet(new Point((position.x+14),position.y), new Point(0, -15)));
            bullets.add(new Bullet(new Point((position.x+14),position.y), new Point(0, 15)));
            bullets.add(new Bullet(new Point((position.x+14),position.y), new Point(15, 0)));
            bullets.add(new Bullet(new Point((position.x+14),position.y), new Point(-15, 0)));
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
        //TODO Take this rendering out of Player and into main rendering loop
        for(Bullet b : bullets){
            b.render(g);
        }
        g.drawImage(getCurrentAnimationFrame(), position.x, position.y, 32, 32, null);
    }

    public void move(){
        if(position.x+velocity.x >0 && position.x+velocity.x < 990){
            position.x += velocity.x;
        }
        if(position.y+velocity.y>0 && position.y+velocity.y<990){
            position.y += velocity.y;
        }
    }

    //TODO Make getting animation frames more global rather than in Entity classes
    private BufferedImage getCurrentAnimationFrame() {
        return anim.getCurrentFrame();
    }
}
