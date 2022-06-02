package Package;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Package/startscreen.fxml"));
        Parent content = loader.load();
        ControllerStart contStart = loader.getController();
        contStart.setStage(stage);
        contStart.setStartScreen();

        Scene scene = new Scene(content, 570, 393);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
