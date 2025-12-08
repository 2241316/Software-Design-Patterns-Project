public abstract class Alien extends Sprite implements Cloneable {
    private Bomb bomb;
    private final String alienImg = "/img/alien.png";

    public Alien(int x, int y) {
        setX(x);
        setY(y);
        setImage(new javax.swing.ImageIcon(getClass().getResource(alienImg)).getImage());
        bomb = new Bomb(x, y);
    }

    public Bomb getBomb() {
        return bomb;
    }

    // -------------------------------------------------------------
    // DESIGN PATTERN: Prototype (Creational)
    // -------------------------------------------------------------
    // This class implements the Prototype pattern by overriding the
    // clone() method. This allows creating new Alien instances by
    // copying an existing one (the prototype) rather than creating
    // them from scratch, which is efficient for managing many similar objects.
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

    // -------------------------------------------------------------
    // DESIGN PATTERN: Template Method (Behavioral)
    // -------------------------------------------------------------
    // The 'act' method defines the skeleton of the algorithm.
    // It enforces a sequence of steps (like checking visibility, etc.)
    // while deferring the specific movement logic 'performMove' to subclasses.
    public final void act(int direction) {
        // Common step: update internal state or prepare

        // Delegated step: specific movement logic implemented by subclasses
        performMove(direction);

        // Any other common steps could go here
    }

    // Abstract method to be implemented by subclasses
    protected abstract void performMove(int direction);
}
