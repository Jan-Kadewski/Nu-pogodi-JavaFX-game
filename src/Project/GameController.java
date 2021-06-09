package Project;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class GameController {
    GameView gameView;
    ResultDialogBox resultDialogBox;
    public GameController(){
        gameView = new GameView();
        setHealthPoint();
        setHealthPoint();
        if(GameModel.getHealthPoint() ==0){
                resultDialogBox = new ResultDialogBox();
        }
    }

    public static void setHealthPoint(){
        GameModel.setHealthPoint(4);
    }


}
