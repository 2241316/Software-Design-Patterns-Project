import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {
    private final int STARTY = 400;
    private final int STARTX = 270;
    private final String playerImg = "/img/craft.png";
    private int width;
    public Player() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImg));
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        setX(STARTX);
        setY(STARTY);
    }
    public void act() {
        x += dx;
        if (x <= 2) {
            x = 2;
        }
        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) dx = -2;
        if (key == KeyEvent.VK_RIGHT) dx = 2;
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) dx = 0;
    }
}
