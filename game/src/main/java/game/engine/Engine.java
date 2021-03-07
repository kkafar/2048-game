package game.engine;

import game.board.raw.*;
import game.util.Position;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Engine implements EventHandler<KeyEvent> {
    public Engine(Board board, Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, this);
        this.board = board;
        this.status = EngineStatus.NOT_STARTED;

    }

    public void start() {
        System.out.println("Engine: starting...");
        this.status = EngineStatus.AWAITING_FOR_INPUT;
    }

    public void stop() {
        System.out.println("Engine: stopped...");
        this.status = EngineStatus.STOPPED;
    }

    private void moveLeft() {
        int last;
        BoardTile prevTile, currentTile;

        for (int row = 0; row < Board.N_TILES_VER; ++row) {
            last = 0;
            for (int col = 1; col < Board.N_TILES_HOR; ++col) {
                if (!board.isEmpty(row, col)) {
                    currentTile = board.at(row, col);
                    if (!board.isEmpty(row, last)) {
                        prevTile = board.at(row, last);
                        if (currentTile.canMerge(prevTile)) {
                            currentTile.mergeWith(prevTile);
                        } else {
                            currentTile.moveTo(new Position(row, last + 1));
                        }
                        ++last;
                    } else {
                        currentTile.moveTo(new Position(row, last));
                    } 
                }
            }
        }
    }

    private void moveRight() {
        int last; 
        BoardTile prevTile, currentTile;

        for (int row = 0; row < Board.N_TILES_VER; ++row) {
            last = Board.N_TILES_HOR - 1;
            for (int col = last - 1; col >= 0; --col) {
                if (!board.isEmpty(row, col)) {
                    currentTile = board.at(row, col);
                    if (!board.isEmpty(row, last)) {
                        prevTile = board.at(row, last);
                        if (currentTile.canMerge(prevTile)) {
                            currentTile.mergeWith(prevTile);
                        } else {
                            currentTile.moveTo(new Position(row, last - 1));
                        }
                        --last;
                    } else {
                        currentTile.moveTo(new Position(row, last));
                    }
                }
            } 
        }
    }

    private void moveDown() {
        int last;
        BoardTile prevTile, currentTile;

        for (int col = 0; col < Board.N_TILES_HOR; ++col) {
            last = Board.N_TILES_VER - 1;
            for (int row = last - 1; row >= 0; --row) {
                if (!board.isEmpty(row, col)) {
                    currentTile = board.at(row, col);
                    if (!board.isEmpty(last, col)) {
                        prevTile = board.at(last, col);
                        if (currentTile.canMerge(prevTile)) {
                            currentTile.mergeWith(prevTile);
                        } else {
                            currentTile.moveTo(new Position(last - 1, col));
                        }
                        --last;
                    } else {
                        currentTile.moveTo(new Position(last, col));
                    }
                }
            }
        }
    }

    private void moveUp() {
        int last;
        BoardTile prevTile, currentTile;

        for (int col = 0; col < Board.N_TILES_HOR; ++col) {
            last = 0;
            for (int row = 1; row < Board.N_TILES_VER; ++row) {
                if (!board.isEmpty(row, col)) {
                    currentTile = board.at(row, col);
                    if (!board.isEmpty(last, col)) {
                        prevTile = board.at(last, col);
                        if (currentTile.canMerge(prevTile)) {
                            currentTile.mergeWith(prevTile);
                        } else {
                            currentTile.moveTo(new Position(last + 1, col));
                        }
                        ++last;
                    } else {
                        currentTile.moveTo(new Position(last, col));
                    }
                }
            }
        }
    }


    @Override
    public void handle(KeyEvent event) {
        System.out.println("Engine: notified on key pressed action...");
        if (status == EngineStatus.AWAITING_FOR_INPUT) {
            // status = EngineStatus.PROCESSING_INPUT;
            
            if (event.getCode() == KeyCode.LEFT) {
                System.out.println("\tLEFT key pressed...");
                moveLeft();                
            } else if (event.getCode() == KeyCode.UP) {
                System.out.println("\tUP key pressed...");
                moveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                System.out.println("\tDOWN key pressed...");
                moveDown();
            } else if (event.getCode() == KeyCode.RIGHT) {
                System.out.println("\tRIGHT key pressed...");
                moveRight();
            } else {
                System.out.println("\tNo known action for key with code " + event.getCode().toString());
                status = EngineStatus.AWAITING_FOR_INPUT;
            }
        } else {
            System.out.println("\tUser input not expected...");
        }
    }
    
    private EngineStatus status;
    private final Board board;
}
