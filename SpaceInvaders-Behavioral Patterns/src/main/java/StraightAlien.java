
public class StraightAlien extends Alien {
    public StraightAlien(int x, int y) {
        super(x, y);
    }

    // Move straight horizontally
    @Override
    protected void performMove(int direction) {
        setX(getX() + direction);
    }
}