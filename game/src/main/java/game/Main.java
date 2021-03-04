package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init() {
        System.out.println("Initializing resources...");
    }


    @Override
    public void start( Stage stage ) {
        Game game = new Game(stage);

        game.start();
    }

    @Override
    public void stop() {
        System.out.println("Stopping application...");
    }

    public static void main( String[] args ) {
        System.out.println( "2048 Game!" );
        Application.launch();
    }
}
