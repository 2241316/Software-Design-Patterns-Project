import javax.swing.ImageIcon;

public class Alien extends Sprite implements Cloneable {
    private Bomb bomb;
    private final String alienImg = "/img/alien.png";

    public Alien(int x, int y) {
        setX(x);
        setY(y);
        setImage(ImageCache.getInstance().getImage(alienImg));
        bomb = new Bomb(x, y);
    }
    public Bomb getBomb() {
        return bomb;
    }
    @Override
    public Alien clone() {
        try {
            Alien cloned = (Alien) super.clone();
            cloned.bomb = bomb.clone();
            return cloned;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // COMMENT: Prototype pattern applied
    public void act(int direction) {
        setX(getX() + direction);
    }
}
