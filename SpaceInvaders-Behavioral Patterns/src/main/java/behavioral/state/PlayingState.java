package behavioral.state;

// Playing state - active game loop
public class PlayingState implements GameState {
    private GameBehavior behavior;

    public PlayingState(GameBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void handleInput() {
        // Input handling handled separately
    }

    // Run game animation cycle
    @Override
    public void update() {
        behavior.cycle();
    }
}
