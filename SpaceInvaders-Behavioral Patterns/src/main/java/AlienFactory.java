public class AlienFactory {
    private Alien prototype;

    public AlienFactory(Alien prototype) {
        this.prototype = prototype;
    }

    // Factory Method - creates alien copies from prototype
    // Encapsulates cloning logic from client code
    public Alien createAlien(int x, int y) {
        Alien alien = prototype.clone();
        alien.setX(x);
        alien.setY(y);
        return alien;
    }
}
