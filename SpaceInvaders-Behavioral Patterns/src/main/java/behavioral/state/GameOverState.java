package behavioral.state;

// Game over state - display results
public class GameOverState implements GameState {
    private GameBehavior behavior;

    public GameOverState(GameBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void handleInput() {
        // Could implement restart logic here
    }

    // Show game over screen
    @Override
    public void update() {
        behavior.gameOverLogic();
    }
}
