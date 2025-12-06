public abstract class Alien extends Sprite implements Cloneable {
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
        // PATTERN: Prototype - Returns a copy of the alien
        try {
            Alien cloned = (Alien) super.clone();
            cloned.bomb = bomb.clone();
            return cloned;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // PATTERN: Template Method - Defines the skeleton of the algorithm (act)
    public final void act(int direction) {
        // Common step: update internal state or prepare

        // Delegated step: specific movement logic implemented by subclasses
        performMove(direction);

        // Any other common steps could go here
    }

    // Abstract method to be implemented by subclasses
    protected abstract void performMove(int direction);
}
