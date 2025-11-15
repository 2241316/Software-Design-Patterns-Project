public class AlienFactory {
    private Alien prototype;

    public AlienFactory(Alien prototype) {
        this.prototype = prototype;
    }

    public Alien createAlien(int x, int y) {
        Alien alien = prototype.clone();
        alien.setX(x);
        alien.setY(y);
        return alien;
    }

    // COMMENT: Factory using Prototype
}
