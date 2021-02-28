package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start( Stage stage ) {
        Game game = new Game(stage);

        game.start();
    }

    public static void main( String[] args ) {
        System.out.println( "2048 Game!" );
        Application.launch();
    }
}
