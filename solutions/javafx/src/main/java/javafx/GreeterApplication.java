package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GreeterApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GreeterFrame.fxml"));

        Scene scene = new Scene(root, 500, 275);

        primaryStage.setTitle("GreeterJavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
