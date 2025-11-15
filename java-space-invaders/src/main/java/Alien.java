import javax.swing.ImageIcon;

public class Alien extends Sprite implements Cloneable {
    private Bomb bomb;
    private final String alienImg = "/img/alien.png";

    public Alien(int x, int y) {
        setX(x);
        setY(y);
            try {
                java.net.URL imgURL = this.getClass().getResource(alienImg);
                if (imgURL != null) {
                    ImageIcon ii = new ImageIcon(imgURL);
                    setImage(ii.getImage());
                } else {
                    System.err.println("Warning: Could not find resource: " + alienImg);
                    java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(12, 12, java.awt.image.BufferedImage.TYPE_INT_RGB);
                    setImage(bi);
                }
            } catch (Exception e) {
                System.err.println("Error loading image: " + alienImg);
                e.printStackTrace();
                java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(12, 12, java.awt.image.BufferedImage.TYPE_INT_RGB);
                setImage(bi);
            }
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
