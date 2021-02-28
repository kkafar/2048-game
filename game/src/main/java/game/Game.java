package game;

import game.board.Board;
import game.engine.Engine;
import game.window.GameWindow;
import javafx.stage.Stage;

public class Game {
    public Game(Stage stage) {
        this.board = new Board();

        this.engine = new Engine(board);
        
        this.window = new GameWindow(stage, board);
    }

    public void start() {
        window.show();
        // engine.start();
    }



    private final GameWindow window;
    private final Board board;
    private final Engine engine;
}
