import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Facade pattern - encapsulates rendering complexity
 */
public class GameRenderer implements Commons {
    private final JPanel panel;
    private final Dimension dimension;

    public GameRenderer(JPanel panel, Dimension dimension) {
        this.panel = panel;
        this.dimension = dimension;
    }

    // Render all game entities in sequence
    public void renderGameplay(Graphics g, ArrayList<Alien> aliens, Player player,
            Shot shot, String explosionPath) {
        clearScreen(g);
        drawGameBoundary(g);
        drawAliens(g, aliens);
        drawPlayer(g, player);
        drawShot(g, shot);
        drawBombs(g, aliens, explosionPath);
    }

    // Display end game message
    public void renderGameOver(Graphics g, boolean hasWon, String message) {
        clearScreen(g);
        renderEndGameMessage(g, hasWon, message);
    }

    // Clear background with black color
    private void clearScreen(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, dimension.width, dimension.height);
    }

    // Draw ground boundary line
    private void drawGameBoundary(Graphics g) {
        g.setColor(Color.green);
        g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
    }

    // Draw visible aliens and animate deaths
    private void drawAliens(Graphics g, ArrayList<Alien> aliens) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), panel);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    // Draw player sprite and handle death animation
    private void drawPlayer(Graphics g, Player player) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), panel);
        }
        if (player.isDying()) {
            player.die();
        }
    }

    // Draw player shot if visible
    private void drawShot(Graphics g, Shot shot) {
        if (shot.isVisible())
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), panel);
    }

    // Draw bombs dropped by aliens
    private void drawBombs(Graphics g, ArrayList<Alien> aliens, String explosionPath) {
        for (Alien a : aliens) {
            Bomb b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage().getImage(), b.getX(), b.getY(), panel);
            }
        }
    }

    // Render win/lose screen with message
    private void renderEndGameMessage(Graphics g, boolean hasWon, String message) {
        GameOver gameend = new GameOver();
        Won vunnet = new Won();

        // Draw background image based on result
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
        if (hasWon) {
            g.drawImage(vunnet.getImage(), 0, 0, panel);
        } else {
            g.drawImage(gameend.getImage(), 0, 0, panel);
        }

        // Draw message box
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        // Draw message text centered
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = panel.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);
    }

    // Sync display buffer and clean up graphics
    public void finishRendering(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
}
