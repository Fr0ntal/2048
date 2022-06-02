package Package;


import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

public class Controller {

    private GameField gameField;
    private Graphics userInterface;
    public FlowPane flowPane;

    public void setImagesFlowPane() {
        flowPane.getChildren().clear();
        ImageView[][] images = userInterface.getImages();
        for (int y = 0; y < gameField.getCountCellsY(); y++) {
            for (int x = 0; x < gameField.getCountCellsX(); x++) {
                flowPane.getChildren().add(images[x][y]);
            }
        }
    }

    @FXML
    public void keyboardPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W: {
                up();
                break;
            }
            case A: {
                left();
                break;
            }
            case S: {
                down();
                break;
            }
            case D: {
                right();
            }
        }
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void right() {
        gameField.right();
        userInterface.updateUI();
        checkWin();

    }

    @FXML
    public void up() {
        gameField.up();
        userInterface.updateUI();
        checkWin();

    }

    @FXML
    public void left() {
        gameField.left();
        userInterface.updateUI();
        checkWin();

    }

    @FXML
    public void down() {
        gameField.down();
        userInterface.updateUI();
        checkWin();
    }


    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void setGraphics(Graphics userInterface) {
        this.userInterface = userInterface;
        setImagesFlowPane();
    }

    private void checkWin() {
        if (gameField.isWinEndOfGame()) {
            userInterface.setWinAlert();
        }
    }
}
