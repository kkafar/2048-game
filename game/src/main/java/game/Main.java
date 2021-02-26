package game;

import game.window.GameWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start( Stage stage ) {
        GameWindow gameWindow = new GameWindow(stage);
        gameWindow.show();
    }

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        Application.launch();
    }
}
