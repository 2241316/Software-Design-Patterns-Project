import javax.swing.ImageIcon;

public class Won extends Sprite implements Commons, Cloneable {
    private final String wonImg = "/img/won.jpg";
    private int width;

    public Won() {
        java.awt.Image img = ImageCache.getInstance().getImage(wonImg);
        width = img.getWidth(null);
        setImage(img);
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
