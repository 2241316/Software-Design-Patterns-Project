package behavioral.state;

public class GameOverState implements GameState {
    private GameBehavior behavior;

    public GameOverState(GameBehavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public void handleInput() {
        // Restart logic could go here
    }

    @Override
    public void update() {
        behavior.gameOverLogic();
    }
}
