package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <h1>Battleship Game</h1>
 *
 * @author Dologlou Dimitrios
 * @version 2.0
 * @since 2020-1-8
 */
public class Main extends Application {

    /**
     * Main method that calls the starting screen of the game and loads the FXML file
     * @param primaryStage the primary window where the app will run and the nodes will be based on
     * @throws Exception in case of a wrong amount of ships later on.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Start.fxml"));
        primaryStage.setTitle("MediaLab Battleship");
        primaryStage.centerOnScreen();
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
