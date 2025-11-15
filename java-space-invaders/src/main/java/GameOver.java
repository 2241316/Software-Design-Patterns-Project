import javax.swing.ImageIcon;

public class GameOver extends Sprite implements Commons, Cloneable {
    private final String gameOverImg = "/img/gameover.png";
    private int width;

    public GameOver() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(gameOverImg));
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        setX(0);
        setY(0);
    }

    @Override
    public GameOver clone() {
        try {
            return (GameOver) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
}
