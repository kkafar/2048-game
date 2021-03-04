package game;

import game.board.raw.*;
import game.engine.Engine;
import game.util.Position;
import game.window.GameWindow;
import javafx.stage.Stage;

public class Game {
    public Game(Stage stage) {
        this.board = new Board();

        this.engine = new Engine(board, stage);
        
        this.window = new GameWindow(stage, board);

        int value = 2;
        int counter = 0;
        for (int i = 0; i < Board.N_TILES_VER; ++i) {
            for (int j = 0; j < Board.N_TILES_HOR && counter < BoardTile.VALID_VALUES.size(); ++j) {
                this.board.place(new BoardTile(value, new Position(i, j)));
                counter++;
                value *= 2;
            }
        }
    }
    
    public void start() {
        window.show();
        engine.start();
    }



    private final GameWindow window;
    private final Board board;
    private final Engine engine;
}
