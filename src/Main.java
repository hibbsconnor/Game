import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = (int) 800;
    public static final int HEIGHT = (int) 600;

    public final String TITLE = "Game Title Here";

    private boolean running = false;
    private Thread thread;

    private synchronized void start() {
    }
}
