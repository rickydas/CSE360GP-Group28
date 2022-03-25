//Pablo's Main
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));





        primaryStage.setTitle("Home Page");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
