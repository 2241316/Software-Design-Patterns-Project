public class ZigZagAlien extends Alien {
    public ZigZagAlien(int x, int y) {
        super(x, y);
    }

    // Move in zigzag pattern - horizontal and vertical combined
    @Override
    protected void performMove(int direction) {
        // Horizontal movement
        setX(getX() + direction);

        // Vertical zigzag pattern
        int phase = (getX() / 10) % 2;
        if (phase == 0) {
            setY(getY() + 1);
        } else {
            setY(getY() - 1);
        }
    }
}
