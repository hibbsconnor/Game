import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends Canvas implements Runnable {

    public static final int WIDTH = (int) 990;
    public static final int HEIGHT = (int) 990;

    private long timeSinceAsteroid = 0, lastTime = System.currentTimeMillis();

    public final String TITLE = "Game Title Here";

    private boolean running = false;
    private Thread thread;

    public Random rand = new Random();

    public KeyInput keyInput = new KeyInput();

    public static Player player;

    public static ArrayList<Asteroid> asteroids = new ArrayList<>();

    public static ArrayList<Asteroid> deadAsteroids = new ArrayList<>();

    private synchronized void start() {
        addKeyListener(keyInput);
        addMouseListener(new MouseInput());
        this.requestFocus();


        Assets.init();
        player = new Player(this, new Point((WIDTH - 16)/2,HEIGHT-200), new Point(0,0));//steven did this

        //Must be at end
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running) return;

        running = false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run(){
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;

            }
        }
        stop();
    }

    private void tick(){
        keyInput.tick();
        player.tick();
        generateAsteroids();
        deadAsteroids = new ArrayList<>();
        for(Asteroid a : asteroids){
            a.tick();
            if(a.position.y>HEIGHT/2){
                deadAsteroids.add(a);
            }
        }
        for(Asteroid a : deadAsteroids){
            asteroids.remove(a);
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs==null){
            createBufferStrategy(2);
            return;
        }

        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        /**
         * DRAW STUFF UNDER HERE
         * Like "g2d.(BufferedImage object, int x, int y, this)"
         */
        g2d.drawImage(Assets.background,0,0,null);
        for(Asteroid a: asteroids){
            a.render(g2d);
        }
        player.render(g2d);

        g2d.dispose();
        bs.show();
    }

    public static void main(String args[]){
        Main game = new Main();
        game.setSize(WIDTH, HEIGHT);
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        game.start();
    }

    /**
     * Gets the object responsibe for tracking key input
     * @return The Key Input tracker for the game
     */
    public KeyInput getKeyInput() {
        return keyInput;
    }

    public void generateAsteroids(){
        timeSinceAsteroid += (System.currentTimeMillis() - lastTime);
        if(timeSinceAsteroid > 400){ //add a new asteroid every 500 milliseconds
            asteroids.add(new Asteroid(this, new Point(rand.nextInt(WIDTH), rand.nextInt(HEIGHT/4)), new Point(0,rand.nextInt(6)+2)));
            timeSinceAsteroid = 0;
        }
        lastTime = System.currentTimeMillis();
    }
}
