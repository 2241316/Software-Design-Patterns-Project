import javax.swing.ImageIcon;

public class Shot extends Sprite implements Cloneable {
    private final String shotImg = "/img/shot.png";
    private final int HSPACE = 6;
    private final int VSPACE = 1;
    public Shot() {
            try {
                java.net.URL imgURL = this.getClass().getResource(shotImg);
                if (imgURL != null) {
                    ImageIcon ii = new ImageIcon(imgURL);
                    setImage(ii.getImage());
                } else {
                    System.err.println("Warning: Could not find resource: " + shotImg);
                    java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(1, 10, java.awt.image.BufferedImage.TYPE_INT_RGB);
                    setImage(bi);
                }
            } catch (Exception e) {
                System.err.println("Error loading image: " + shotImg);
                e.printStackTrace();
                java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(1, 10, java.awt.image.BufferedImage.TYPE_INT_RGB);
                setImage(bi);
            }
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
