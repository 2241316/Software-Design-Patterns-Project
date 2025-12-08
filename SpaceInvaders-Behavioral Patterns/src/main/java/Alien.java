public abstract class Alien extends Sprite implements Cloneable {
    private Bomb bomb;
    private final String alienImg = "/img/alien.png";

    public Alien(int x, int y) {
        // Initialize alien with image from cache
        setX(x);
        setY(y);
        setImage(new javax.swing.ImageIcon(getClass().getResource(alienImg)).getImage());
        bomb = new Bomb(x, y);
    }

    public Bomb getBomb() {
        return bomb;
    }

    // Prototype pattern - clone creates independent copies
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

    // Template Method - skeleton of movement algorithm
    public final void act(int direction) {
        // Delegates specific movement to subclass implementation
        performMove(direction);
    }

    // Abstract method for subclasses to implement movement behavior
    protected abstract void performMove(int direction);
}
