import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Facade pattern: Simplifies the rendering complexity of the game board.
 * Provides a cleaner interface for drawing game entities without exposing
 * the complex logic of individual draw methods.
 */
public class GameRenderer implements Commons {
    private final JPanel panel;
    private final Dimension dimension;

    public GameRenderer(JPanel panel, Dimension dimension) {
        this.panel = panel;
        this.dimension = dimension;
    }

    /**
     * Render all game entities (aliens, player, shots, bombs).
     * Facade method that consolidates all drawing logic.
     */
    public void renderGameplay(Graphics g, ArrayList<Alien> aliens, Player player,
                               Shot shot, String explosionPath) {
        clearScreen(g);
        drawGameBoundary(g);
        drawAliens(g, aliens);
        drawPlayer(g, player);
        drawShot(g, shot);
        drawBombs(g, aliens, explosionPath);
    }

    /**
     * Render the game over screen with a message.
     */
    public void renderGameOver(Graphics g, boolean hasWon, String message) {
        clearScreen(g);
        renderEndGameMessage(g, hasWon, message);
    }

    /**
     * Clear the game screen with black background.
     */
    private void clearScreen(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, dimension.width, dimension.height);
    }

    /**
     * Draw the ground boundary line.
     */
    private void drawGameBoundary(Graphics g) {
        g.setColor(Color.green);
        g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
    }

    /**
     * Draw all visible aliens and handle death animations.
     */
    private void drawAliens(Graphics g, ArrayList<Alien> aliens) {
        Iterator<Alien> it = aliens.iterator();
        while (it.hasNext()) {
            Alien alien = it.next();
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), panel);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    /**
     * Draw the player sprite and handle death animations.
     */
    private void drawPlayer(Graphics g, Player player) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), panel);
        }
        if (player.isDying()) {
            player.die();
        }
    }

    /**
     * Draw the player's shot.
     */
    private void drawShot(Graphics g, Shot shot) {
        if (shot.isVisible())
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), panel);
    }

    /**
     * Draw all active bombs from aliens.
     */
    private void drawBombs(Graphics g, ArrayList<Alien> aliens, String explosionPath) {
        Iterator<Alien> it = aliens.iterator();
        while (it.hasNext()) {
            Alien a = it.next();
            Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), panel);
            }
        }
    }

    /**
     * Render end-game message screen (win or lose).
     */
    private void renderEndGameMessage(Graphics g, boolean hasWon, String message) {
        GameOver gameend = new GameOver();
        Won vunnet = new Won();

        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
        if (hasWon) {
            g.drawImage(vunnet.getImage(), 0, 0, panel);
        } else {
            g.drawImage(gameend.getImage(), 0, 0, panel);
        }

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = panel.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);
    }

    /**
     * Sync display and dispose graphics.
     */
    public void finishRendering(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    // COMMENT: Facade pattern - simplifies Board's rendering complexity
}
