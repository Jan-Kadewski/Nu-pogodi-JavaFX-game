package Project;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class GameView extends Application {
    GameModel gameModel;
    Scene scene;
    Group root;

    public GameView() {
        gameModel = new GameModel();
    }

    @Override
    public void start(Stage stage) throws Exception {
        createRootGroupWithAddedElementsToScene();
        setCustomSettingsForMainMenuStage(stage);


    }


    public void setCustomSettingsForMainMenuStage(Stage stage) {
        KeyCombination kc = new KeyCodeCombination(KeyCode.Q,
                KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        Runnable rn = () -> {
            System.out.println("zadziałało");
        };
        scene.getAccelerators().put(kc,rn);
        stage.setTitle("Nu pogodi");
        stage.setScene(scene);
        stage.getIcons().add(new Image("wolf.png"));
        stage.show();
    }

    public void createRootGroupWithAddedElementsToScene() {
        root = new Group();
        scene = new Scene(root, 1500, 800);
//        scene.setFill(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE ,new Stop(0, Color.web("#81c483")),new Stop(1,Color.web("#fcc200"))));
    }

}
