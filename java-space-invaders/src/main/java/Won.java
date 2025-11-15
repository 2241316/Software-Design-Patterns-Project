import javax.swing.ImageIcon;

public class Won extends Sprite implements Commons, Cloneable {
    private final String wonImg = "/img/won.jpg";
    private int width;

    public Won() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(wonImg));
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
        setX(0);
        setY(0);
    }

    @Override
    public Won clone() {
        try {
            return (Won) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
}
