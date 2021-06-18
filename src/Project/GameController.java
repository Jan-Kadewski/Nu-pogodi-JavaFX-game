package Project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.function.Predicate;

public class GameController {
    GameView gameView;
    ResultDialogBox resultDialogBox;
    Runnable myRunnable;
    ImageView imageLeftDown;
    ImageView imageRightTop;
    ImageView imageRightDown;
    ImageView imageLeftTop;
    Image image;

    public GameController() {
        gameView = new GameView();
        rightTopButtonListener();
        rightDownButtonListener();
        leftTOpButtonListener();
        leftDownButtonListener();
        image = new Image("Project/StaticResources/Assets/TopRight");
        imageRightTop = new ImageView(image);
        resultDialogBox = new ResultDialogBox();
        generateEgs();
        moveByKeyboard();
        GameModel.points = 0;
        timer();
        Thread timer = new Thread(myRunnable);
        timer.start();
        gameView.gameModel.healthPoint = 4;
    }

    public void timer() {
        myRunnable = new Runnable() {
            boolean flag = false;

            public void run() {
                while (!flag) {
                    GameModel.seconds++;
                    System.out.println(GameModel.seconds);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (resultDialogBox != null) {
                    flag = true;
                }
            }
        };
    }

    public void rightTopButtonListener() {
        gameView.btnRightTop.setOnAction(e -> {
            if (e.getSource() == gameView.btnRightTop) {
                moveCharacterToRightTop();
                //TODO: TEN fragment przekleić, gdy jajko wpadnie do koszyka
                GameModel.points++;
                gameView.gameScore.setText(String.valueOf(GameModel.points));
            }
        });
    }

    public void leftTOpButtonListener() {
        gameView.btnLeftTop.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnLeftTop) {
                moveCharacterToLeftTop();
            }
        });
    }

    public void leftDownButtonListener() {
        gameView.btnLeftDown.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnLeftDown) {
                moveCharacterToLeftDown();
            }
        });
    }

    public void rightDownButtonListener() {
        gameView.btnRightDown.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnRightDown) {
                moveCharacterToRightDown();
            }
        });
    }

    public void moveCharacterToRightDown() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageLeftTop, imageRightDown);
        Image image = new Image("Project/StaticResources/Assets/downRight");
        imageRightDown = new ImageView(image);
        imageRightDown.setX(420);
        imageRightDown.setY(326);
        gameView.root.getChildren().add(imageRightDown);
    }

    public void moveCharacterToLeftDown() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftTop, imageRightDown, imageLeftDown);
        Image image = new Image("Project/StaticResources/Assets/downLeft");
        imageLeftDown = new ImageView(image);
        imageLeftDown.setX(325);
        imageLeftDown.setY(326);
        gameView.root.getChildren().add(imageLeftDown);
    }

    public void moveCharacterToLeftTop() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageRightDown, imageLeftTop);
        Image image = new Image("Project/StaticResources/Assets/topLeft");
        imageLeftTop = new ImageView(image);
        imageLeftTop.setX(305);
        imageLeftTop.setY(250);
        gameView.root.getChildren().add(imageLeftTop);
    }

    public void moveCharacterToRightTop() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageLeftTop, imageRightDown);
        gameView.root.getChildren().removeAll(imageLeftDown, imageLeftTop, imageRightDown, imageRightTop);
        imageRightTop.setX(440);
        imageRightTop.setY(250);
        gameView.root.getChildren().add(imageRightTop);
    }


    public void moveByKeyboard() {
        gameView.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        moveCharacterToRightTop();
                        break;
                    case DOWN:
                        moveCharacterToLeftTop();
                        break;
                    case LEFT:
                        moveCharacterToLeftDown();
                        break;
                    case RIGHT:
                        moveCharacterToRightDown();
                        break;
                }
            }
        });
    }


    public void generateEgs() {
        int randomNumber = 1;

        switch (randomNumber) {
            case 1:
                gameView.createEgg(790, 155, 620, 270, 620, 350, 5500);
                break;

            case 2:
                gameView.createEgg(200, 155, 370, 270, 370, 759, 5500);
                break;

            case 3:
                gameView.createEgg(200, 270, 370, 385, 370, 759, 5500);
                break;

            case 4:
                gameView.createEgg(790, 270, 620, 390, 650, 759, 5500);
                break;

        }

    }

}
