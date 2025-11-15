import javax.swing.ImageIcon;

public class Bomb implements Cloneable {
    private boolean destroyed;
    private int x, y;
    private final String bombImg = "/img/bomb.png";
    private final String explosionImg = "/img/explosion.png";
    private ImageIcon image;
    
    public Bomb(int x, int y) {
        setX(x);
        setY(y);
        setDestroyed(true);
        image = new ImageIcon(ImageCache.getInstance().getImage(bombImg));
    }
    
    public void explode() {
        // Switch to explosion image when bomb detonates
        image = new ImageIcon(ImageCache.getInstance().getImage(explosionImg));
    }
    
    public boolean isDestroyed() { return destroyed; }
    public void setDestroyed(boolean status) { 
        destroyed = status;
        // When bomb becomes active (destroyed=false), ensure it uses bomb image
        if (!destroyed) {
            image = new ImageIcon(ImageCache.getInstance().getImage(bombImg));
        }
    }
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
    // COMMENT: Flyweight pattern - shares images via ImageCache
}
