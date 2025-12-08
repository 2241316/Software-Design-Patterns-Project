public class AlienFactory {
    private Alien prototype;

    public AlienFactory(Alien prototype) {
        this.prototype = prototype;
    }

    // -------------------------------------------------------------
    // DESIGN PATTERN: Factory Method (Creational)
    // -------------------------------------------------------------
    // This method encapsulates the creation logic for Aliens.
    // It abstracts the instantiation process (which uses cloning/prototype)
    // from the client code.
    public Alien createAlien(int x, int y) {
        Alien alien = prototype.clone();
        alien.setX(x);
        alien.setY(y);
        return alien;
    }
}
