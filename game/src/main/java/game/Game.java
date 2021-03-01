package game;

import game.board.Board;
import game.board.BoardTile;
import game.engine.Engine;
import game.util.Position;
import game.window.GameWindow;
import javafx.stage.Stage;

public class Game {
    public Game(Stage stage) {
        this.board = new Board();

        this.engine = new Engine(board);
        
        this.window = new GameWindow(stage, board);

        Position position = new Position(0, 0);
        BoardTile tile = new BoardTile(128, position);
        BoardTile tile2 = new BoardTile(2048, new Position(3, 3));

        this.board.place(tile);
        this.board.place(tile2);
    }
    
    public void start() {
        window.show();
        // engine.start();
    }



    private final GameWindow window;
    private final Board board;
    private final Engine engine;
}
