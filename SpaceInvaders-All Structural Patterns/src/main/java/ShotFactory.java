public class ShotFactory {
    private Shot prototype;
    public ShotFactory(Shot prototype) {
        this.prototype = prototype;
    }
    public Shot createShot(int x, int y) {
        Shot shot = prototype.clone();
        shot.setX(x + 6);
        shot.setY(y - 1);
        return shot;
    }
    // COMMENT: Factory using Prototype
}
