import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import behavioral.state.*;

public class Board extends JPanel implements Runnable, Commons, GameBehavior {
    private static final long serialVersionUID = 1L;
    private Dimension d;
    private ArrayList<Alien> aliens;
    private Player player;
    private Shot shot;
    private int alienX = 150;
    private int alienY = 25;
    private int direction = -1;
    private int deaths = 0;
    private boolean ingame = true;
    private boolean havewon = true;
    private final String expl = "/img/explosion.png";
    private final String alienpix = "/img/alien.png";
    private String message = "Seu planeta nos pertence agora...";
    private Thread animator;
    private AlienFactory alienFactory;
    private ShotFactory shotFactory;
    private GameRenderer renderer;
    private StateManager stateManager;

    public Board() {
        // Setup input and display
        addKeyListener(new KeyboardHandler());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        setBackground(Color.black);

        // Initialize rendering facade
        renderer = new GameRenderer(this, d);
        
        // Initialize game state manager
        stateManager = new StateManager();
        stateManager.setState(new PlayingState(this));

        gameInit();
        setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    @Override
    public void cycle() {
        animationCycle();
    }

    @Override
    public void gameOverLogic() {
        gameOver();
    }

    public void gameInit() {
        // Initialize game entities using factories and prototypes
        aliens = new ArrayList<>();
        Alien prototypeAlien = new StraightAlien(alienX, alienY);
        prototypeAlien.setImage(ImageCache.getImage(alienpix));

        alienFactory = new AlienFactory(prototypeAlien);
        Shot prototypeShot = new Shot(0, 0);
        shotFactory = new ShotFactory(prototypeShot);

        // Create 4x6 grid of aliens
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = alienFactory.createAlien(alienX + 18 * j, alienY + 18 * i);
                aliens.add(alien);
            }
        }

        // Initialize player and start animation thread
        player = new Player();
        shot = shotFactory.createShot(player.getX(), player.getY());
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawAliens(Graphics g) {
        // Draw all visible aliens and handle death animations
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {
        // Render player and check for death state
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        if (player.isDying()) {
            player.die();
            havewon = false;
            ingame = false;
        }
    }

    public void drawShot(Graphics g) {
        if (shot.isVisible())
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
    }

    public void drawBombing(Graphics g) {
        // Draw bombs dropped by aliens
        for (Alien a : aliens) {
            Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public void paint(Graphics g) {
        // Main rendering - delegates to facade
        super.paint(g);
        if (ingame) {
            renderer.renderGameplay(g, aliens, player, shot, expl);
            // Check if player died from bomb collision during rendering
            if (player.isDying()) {
                player.die();
                havewon = false;
                ingame = false;
            }
        }
        renderer.finishRendering(g);
    }

    public void gameOver() {
        // Display game over screen
        Graphics g = this.getGraphics();
        renderer.renderGameOver(g, havewon, message);
        renderer.finishRendering(g);
    }

    public void animationCycle() {
        // Check win condition
        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            ingame = false;
            message = "Parabéns! Você salvou a galáxia!";
        }
        
        // Update player movement
        player.act();
        
        // Handle shot collision with aliens
        if (shot.isVisible()) {
            int shotX = shot.getX();
            int shotY = shot.getY();
            for (Alien alien : aliens) {
                int alienX = alien.getX();
                int alienY = alien.getY();
                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + ALIEN_HEIGHT)) {
                        alien.setImage(ImageCache.getImage(expl));
                        alien.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }
            // Move shot upward
            int y = shot.getY();
            y -= 8;
            if (y < 0)
                shot.die();
            else
                shot.setY(y);
        }
        
        // Handle alien movement and boundary checking
        for (Alien a1 : aliens) {
            int x = a1.getX();
            if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
                direction = -1;
                for (Alien a2 : aliens) {
                    a2.setY(a2.getY() + GO_DOWN);
                }
            }
            if (x <= BORDER_LEFT && direction != 1) {
                direction = 1;
                for (Alien a : aliens) {
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }
        
        // Update alien positions and check if they reached ground
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > GROUND - ALIEN_HEIGHT) {
                    havewon = false;
                    ingame = false;
                    message = "Aliens estão invadindo a galáxia!";
                }
                alien.act(direction);
            }
        }
        
        // Handle bomb logic for each alien
        Random generator = new Random();
        for (Alien a : aliens) {
            int shot = generator.nextInt(15);
            Bomb b = a.getBomb();
            // Randomly fire bombs
            if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {
                b.setDestroyed(false);
                b.setX(a.getX());
                b.setY(a.getY());
            }
            // Check bomb collision with player
            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();
            if (player.isVisible() && !b.isDestroyed()) {
                if (bombX >= (playerX) && bombX <= (playerX + PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + PLAYER_HEIGHT)) {
                    b.explode();
                    player.setImage(ImageCache.getImage("/img/explosion.png"));
                    player.setDying(true);
                    b.setDestroyed(true);
                }
            }
            // Move bomb downward
            if (!b.isDestroyed()) {
                b.setY(b.getY() + 1);
                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.explode();
                    b.setDestroyed(true);
                }
            }
        }
    }

    public void run() {
        // Main game loop with fixed frame rate
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();
            stateManager.update();

            // Control frame rate
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (sleep < 0)
                sleep = 1;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }

        // End game - switch to game over state
        stateManager.setState(new GameOverState(this));
        stateManager.update();
    }

    private class KeyboardHandler implements java.awt.event.KeyListener {
        public void keyTyped(KeyEvent e) {
            // Not used
        }

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            // Handle player input
            player.keyPressed(e);
            int x = player.getX();
            int y = player.getY();
            if (ingame) {
                int key = e.getKeyCode();
                // Fire shot on spacebar
                if (key == KeyEvent.VK_SPACE) {
                    if (!shot.isVisible())
                        shot = shotFactory.createShot(x, y);
                }
            }
        }
    }
}
