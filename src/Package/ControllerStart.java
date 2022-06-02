package Package;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ControllerStart {

    public ComboBox<Integer> comboBoxXCells;
    public ComboBox<Integer> comboBoxYCells;
    public ComboBox<Integer> comboBoxWinCase;

    private Stage stage;
    private GameField gameField;
    private Graphics userInterface;

    public void setStartScreen() {
        ObservableList<Integer> listXY = FXCollections.observableArrayList(3,4,5,6,7,8,9);
        ObservableList<Integer> listWinCase = FXCollections.observableArrayList(32, 64, 128, 256, 512, 1024, 2048);
        comboBoxXCells.setItems(listXY);
        comboBoxYCells.setItems(listXY);
        comboBoxWinCase.setItems(listWinCase);
    }

    @FXML
    public void startGame() throws Exception {
        int winCase = (comboBoxWinCase.getValue() == null)? 2048 : comboBoxWinCase.getValue();
        int countX = (comboBoxXCells.getValue() == null )? 4 : comboBoxXCells.getValue();
        int countY = (comboBoxYCells.getValue() == null )? 4 : comboBoxYCells.getValue();

        gameField = new GameField(countX, countY, winCase);
        gameField.newGame();
        userInterface = new Graphics(gameField);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Package/gameinterface.fxml"));
        Parent content = loader.load();
        Controller controller = loader.getController();

        controller.setGameField(gameField);
        controller.setGraphics(userInterface);

        Scene scene = new Scene(content, 570, 393);
        stage.setScene(scene);
        stage.setResizable(false);
        content.requestFocus();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}