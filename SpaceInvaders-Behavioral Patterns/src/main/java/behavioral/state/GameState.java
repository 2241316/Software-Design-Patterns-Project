package behavioral.state;

// State interface - different states implement this contract
public interface GameState {
    // Handle input in this state
    void handleInput();

    // Update game logic in this state
    void update();
}
