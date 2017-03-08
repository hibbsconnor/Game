import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main extends Canvas implements Runnable {
    public static JFrame frame;
    public static final int WIDTH = (int) 990;
    public static final int HEIGHT = (int) 990;

    private long timeSinceObstacle = 0, lastTime = System.currentTimeMillis();

    //Display Score
    public static int score = 0;
    public final String title = "Score: " + score;

    private boolean running = false;
    private Thread thread;

    public Random rand = new Random();

    public KeyInput keyInput = new KeyInput();

    public static Player player;

    public static ArrayList<Entity> asteroids = new ArrayList<>();

    public static ArrayList<Entity> deadAsteroids = new ArrayList<>();
    
    //start the game, listen for mouse and key inputs
    private synchronized void start() {
        addKeyListener(keyInput);
        addMouseListener(new MouseInput());
        this.requestFocus();

        //initialize images and create the player
        Assets.init();
        player = new Player(this, new Point((WIDTH/2)-16,(HEIGHT/2)-16), new Point(0,0));

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
    //while thread runs
    public void run(){
        //update the time and frames, refresh everything
    	long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        //update the clock
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
                //System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;

            }
        }
        stop();
    }
    //what to do during updates, updates player, keys, asteroid, bullets per tick
    private void tick(){
        keyInput.tick();
        player.tick();
        generateObstacles();
        deadAsteroids = new ArrayList<>();
        for(Entity a : asteroids){
            a.tick();
            if(!Collisions.rectCollision(new Rectangle(-200,-200,1390,1390),
                                        new Rectangle(a.position.x, a.position.y, 64, 64))){
                deadAsteroids.add(a);
            }
        }
        for(Entity a : deadAsteroids){
            asteroids.remove(a);
        }
        frame.setTitle("Score: " + score);
    }
    //draw bugger and background
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
        for(Entity a: asteroids){
            a.render(g2d);
        }
        player.render(g2d);

        g2d.dispose();
        bs.show();
    }
    
    //main to run the game, create the window and frame
    public static void main(String args[]){
        Main game = new Main();
        game.setSize(WIDTH, HEIGHT);
        frame = new JFrame(game.title);
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
    //create random asteroids to populate the game and keep creating them
    public void generateObstacles(){
        timeSinceObstacle += (System.currentTimeMillis() - lastTime);
        if(timeSinceObstacle > 500){ //add a new asteroid every 500 milliseconds
            int xPos = rand.nextInt(WIDTH);
            int yPos = rand.nextInt(HEIGHT);
            Point location = new Point();
            float xVelocity, yVelocity;
            float speed = (rand.nextFloat() * 5) + 3;

            switch(rand.nextInt(4)){ //randomly generate a side to spawn on. 0-4: top, right, left, bottom
                case 0:
                    location.x = xPos;
                    location.y = -64;//-64
                    break;
                case 1:
                    location.x = WIDTH + 64; // +64
                    location.y = yPos;
                    break;
                case 2:
                    location.x = xPos;
                    location.y = HEIGHT + 64; //+64
                    break;
                case 3:
                    location.x = -64; //-64
                    location.y = yPos;
                    break;
            }
            int w = location.x - player.position.x;
            int h = location.y - player.position.y;
            double theta = java.lang.Math.atan2(w, h);

            xVelocity = -(float)(speed * Math.sin(theta));
            yVelocity = -(float)(speed * Math.cos(theta));

            int laserX = -128;
            int laserY = rand.nextInt(HEIGHT);
            Point laserPosition = new Point(-128, rand.nextInt(HEIGHT));

            asteroids.add(new Laser(laserPosition, new Point(10, 0)));
            asteroids.add(new Asteroid(location, new Point((int)xVelocity, (int)yVelocity)));
            timeSinceObstacle = 0;
        }
        lastTime = System.currentTimeMillis();
    }

    public double r2d(double radians){
        return radians * 180 / Math.PI;
    }
}
