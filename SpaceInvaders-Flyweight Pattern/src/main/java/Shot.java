import javax.swing.ImageIcon;

public class Shot extends Sprite implements Cloneable {
    private final String shotImg = "/img/shot.png";
    private final int HSPACE = 6;
    private final int VSPACE = 1;
    public Shot() {
        setImage(ImageCache.getInstance().getImage(shotImg));
    }
    public Shot(int x, int y) {
        this();
        setX(x + HSPACE);
        setY(y - VSPACE);
    }
    @Override
    public Shot clone() {
        try {
            return (Shot) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // COMMENT: Prototype pattern applied
}
