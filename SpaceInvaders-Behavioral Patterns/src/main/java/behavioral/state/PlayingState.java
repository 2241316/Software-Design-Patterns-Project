package behavioral.state;

public class PlayingState implements GameState {
    private GameBehavior behavior;

    public PlayingState(GameBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void handleInput() {
        // Input handling remains in Board for now due to restrictions
    }

    @Override
    public void update() {
        behavior.cycle();
    }
}
