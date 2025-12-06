package behavioral.state;

public class StateManager {
    private GameState currentState;

    public void setState(GameState state) {
        this.currentState = state;
    }

    public GameState getState() {
        return currentState;
    }

    public void update() {
        if (currentState != null) {
            currentState.update();
        }
    }

    public void handleInput() {
        if (currentState != null) {
            currentState.handleInput();
        }
    }
}
