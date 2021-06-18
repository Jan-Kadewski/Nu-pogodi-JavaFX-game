package Project;

import Project.StaticResources.Labels;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene;
    MainMenuController mainMenuController;
    Group root;
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainMenuController = new MainMenuController();
        mainMenuController.startGameAction(primaryStage);
        mainMenuController.showHighScore(primaryStage);
        mainMenuController.backToMenuFromHighScore(primaryStage);
        createRootGroupWithAddedElementsToScene();
        setCustomSettingsForMainMenuStage(primaryStage);
        primaryStage.setResizable(false);
    }

    public  void setCustomSettingsForMainMenuStage(Stage stage){
                stage.setTitle("Nu pogodi");
                stage.setScene(scene);
                stage.getIcons().add(new Image("Project/StaticResources/Assets/wolf.png"));
                stage.show();
    }

    public void createRootGroupWithAddedElementsToScene(){
        root = new Group();
        root.getChildren().addAll(mainMenuController.mainMenu.root);
        scene = new Scene(root,600,400);
        scene.setFill(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE ,new Stop(0,Color.web("#81c483")),new Stop(1,Color.web("#fcc200"))));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
