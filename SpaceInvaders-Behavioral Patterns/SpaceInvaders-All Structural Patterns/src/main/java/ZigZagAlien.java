
public class ZigZagAlien extends Alien {
    public ZigZagAlien(int x, int y) {
        super(x, y);
    }

    @Override
    protected void performMove(int direction) {
        setX(getX() + direction);

        // ZigZag logic
        int phase = (getX() / 10) % 2;
        if (phase == 0) {
            setY(getY() + 1);
        } else {
            setY(getY() - 1);
        }
    }
}
