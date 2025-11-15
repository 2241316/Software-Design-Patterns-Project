import javax.swing.ImageIcon;

public class Bomb implements Cloneable {
    private boolean destroyed;
    private int x, y;
    private final String bombImg = "/img/explosion.png";
    private ImageIcon image;
    public Bomb(int x, int y) {
        setX(x);
        setY(y);
        setDestroyed(true);
            try {
                java.net.URL imgURL = this.getClass().getResource(bombImg);
                if (imgURL != null) {
                    image = new ImageIcon(imgURL);
                } else {
                    System.err.println("Warning: Could not find resource: " + bombImg);
                    // Create a placeholder 5x5 image
                    java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(5, 5, java.awt.image.BufferedImage.TYPE_INT_RGB);
                    image = new ImageIcon(bi);
                }
            } catch (Exception e) {
                System.err.println("Error loading image: " + bombImg);
                e.printStackTrace();
                java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(5, 5, java.awt.image.BufferedImage.TYPE_INT_RGB);
                image = new ImageIcon(bi);
            }
    }
    public boolean isDestroyed() { return destroyed; }
    public void setDestroyed(boolean status) { destroyed = status; }
    public void setX(int newX) { x = newX; }
    public void setY(int newY) { y = newY; }
    public int getX() { return x; }
    public int getY() { return y; }
    public ImageIcon getImage() { return image; }
    @Override
    public Bomb clone() {
        try {
            return (Bomb) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // COMMENT: Prototype pattern applied
}
