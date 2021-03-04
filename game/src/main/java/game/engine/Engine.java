package game.engine;

import game.board.Board;
import game.board.BoardTile;
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

        
    private void mergeTiles(BoardTile tile1, BoardTile tile2) {
        System.out.println("Engine: merging tiles: " + tile1.getPosition().toString() + " --> " + tile2.getPosition().toString());
        
    }


    @Override
    public void handle(KeyEvent event) {
        System.out.println("Engine: notified on key pressed action...");
        if (status == EngineStatus.AWAITING_FOR_INPUT) {
            status = EngineStatus.PROCESSING_INPUT;
            
            if (event.getCode() == KeyCode.LEFT) {
                System.out.println("\tLEFT key pressed...");
                
            } else if (event.getCode() == KeyCode.UP) {
                System.out.println("\tUP key pressed...");

            } else if (event.getCode() == KeyCode.DOWN) {
                System.out.println("\tDOWN key pressed...");

            } else if (event.getCode() == KeyCode.RIGHT) {
                System.out.println("\tRIGHT key pressed...");

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
