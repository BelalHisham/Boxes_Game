package Javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.inject.Inject;

import java.io.IOException;

public class GameApplication extends Application {

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage)  throws IOException {
        Parent root = fxmlLoader.load(getClass().getResource("/fxml/Opening.fxml"));
        stage.setTitle("Boxes Game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
