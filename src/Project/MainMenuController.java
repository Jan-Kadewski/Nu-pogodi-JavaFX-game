package Project;

import javafx.application.Platform;
import javafx.stage.Stage;

public class MainMenuController {
    MainMenu mainMenu;
    GameView gameView;
    public MainMenuController() {
        mainMenu = new MainMenu();
        showHighScore();
        exitProgram();
    }

    public void startGameAction(Stage stage) {
        mainMenu.startGame.setOnAction(event -> {
            if (event.getSource() == mainMenu.startGame) {
                System.out.println("działa");
                stage.close();
                gameView = new GameView();
                try {
                    gameView.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showHighScore() {
        mainMenu.highScore.setOnAction(event -> {
            if (event.getSource() == mainMenu.highScore) {
                //TODO: TUTAJ ZAIMPLEMENTOWAĆ CZYTANIE Z PLIKU RANKINGU
                System.out.println("Czytam ranking z pliku");
            }
        });
    }

    public void exitProgram() {
        mainMenu.exit.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == mainMenu.exit) {
                Platform.exit();
            }
        });
    }
}
