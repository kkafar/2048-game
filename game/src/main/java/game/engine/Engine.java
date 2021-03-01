package game.engine;

import game.board.Board;

public class Engine {
    public Engine(Board board) {
        this.board = board;
        this.status = EngineStatus.NOT_STARTED;
    }

    private EngineStatus status;
    private final Board board;
}
