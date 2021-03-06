package game.window;


import game.board.raw.Board;
import game.board.representation.BoardRepresentation;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameWindow {
    public GameWindow(Stage stage, Board board) {
        this.stage = stage;
        
        this.board = new BoardRepresentation(PXL_WIDTH, PXL_HEIGHT, board);
        
        this.scene = new Scene(this.board , PXL_WIDTH, PXL_HEIGHT);
        
        // this.scene.setOnKeyPressed(e -> {
            //     System.out.println("Key pressed: " + e.getCode());
            // });
            
        this.stage.setTitle(TITLE);
        this.stage.setScene(this.scene);

    }

    public void show() {
        stage.show();
    }

    private Scene scene;
    private final Stage stage;
    private final BoardRepresentation board;    


    private static int PXL_WIDTH = 1024; 
    private static int PXL_HEIGHT = 1024;

    private static String TITLE = "2048 GAME"; 
}
