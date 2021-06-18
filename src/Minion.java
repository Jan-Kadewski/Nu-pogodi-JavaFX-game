
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;

public class Minion extends Application implements Runnable{
    private Group root;
    private StackPane stackpane;
    private Scene mainbgscene;
    private ImageView mainbgv,heartIm1,heartIm2,heartIm3,characterIm,obsIm,obsIm2,obsIm3,obsIm4,obsIm5,obsIm6,obsIm7;
    private Image mainbg,heart1,character,obs,obs2,obs3,obs4,obs5,obs6,obs7;
    private VBox mainvbox,scoreVbox,characterVbox,obsVbox;
    private HBox heartHbox,scoreHbox;
    private Stage stageContainer;
    private Label scoreLabel;
    private Rectangle scoreBox;
    private Runnable r1;
    private int characterBoundary,life;
    private boolean flag1 =true;
    private double characterX,characterY;
    public static int score,lastScore;

    //For moving obstacle's start and end position
    private double obsStartX,obsStart2X,obsStart3X,obsStart4X,obsStart5X,obsStart6X,obsStart7X,obsEndX;
    public boolean gameRun;

    public Minion(){
        life = 4;
        characterX = 0.0;
        characterY = 450.0;
        obsStartX = 1100.0;
        obsStart2X = 1175.0;
        obsStart3X = 1150.0;
        obsStart4X = 800.0;
        obsStart5X = 1200.0;
        obsStart6X = 1000.0;
        obsStart7X = 700.0;
        obsEndX = -200.00;
        gameRun = true;
    }

    public void run() {
        while(gameRun){
            obsStartX -= 1;
            obsStart2X -= 1;
            obsStart3X -= 1;
            obsStart4X -= 1;
            obsStart5X -= 1;
            obsStart6X -= 1;
            obsStart7X -= 1;
            Platform.runLater(() -> {
                //Changing Position
                obsIm.setTranslateX(obsStartX);
                obsIm2.setTranslateX(obsStart2X);
                obsIm3.setTranslateX(obsStart3X);
                obsIm4.setTranslateX(obsStart4X);
                obsIm5.setTranslateX(obsStart5X);
                obsIm6.setTranslateX(obsStart6X);
                obsIm7.setTranslateX(obsStart7X);
                updatePoint();
                //If a obstacle reach end screen then again it will start
                if(obsStartX == obsEndX){
                    obsStartX = (double)Range(2500,1400);
                    obsIm.setVisible(true);
                    obsIm.setImage(obstacleSelect());
                    obsIm.setTranslateY(Range(200,100));
                }
                if(obsStart2X == obsEndX){
                    obsStart2X = (double)Range(2200,1800);
                    obsIm2.setVisible(true);
                    obsIm2.setImage(obstacleSelect());
                    obsIm2.setTranslateY(Range(380,280));
                }
                if(obsStart3X == obsEndX){
                    obsStart3X = (double)Range(2700,1500);
                    obsIm3.setVisible(true);
                    obsIm3.setImage(obstacleSelect());
                    obsIm3.setTranslateY(Range(530,400));
                }
                if(obsStart4X == obsEndX){
                    obsStart4X = (double)Range(1900,1800);
                    obsIm4.setVisible(true);
                    obsIm4.setImage(obstacleSelect());
                    obsIm4.setTranslateY(Range(200,100));
                }
                if(obsStart5X == obsEndX){
                    obsStart5X = (double)Range(2000,1700);
                    obsIm5.setVisible(true);
                    obsIm5.setImage(obstacleSelect());
                    obsIm5.setTranslateY(Range(380,280));
                }
                if(obsStart6X == obsEndX){
                    obsStart6X = (double)Range(1700,1500);
                    obsIm6.setVisible(true);
                    obsIm6.setImage(obstacleSelect());
                    obsIm6.setTranslateY(Range(530,400));
                }
                if(obsStart7X == obsEndX){
                    obsStart7X = (double)Range(1900,1400);
                    obsIm7.setVisible(true);
                    obsIm7.setImage(obstacleSelect());
                    obsIm7.setTranslateY(Range(380,280));
                }
            });
            try{
                Thread.sleep(10);
            }catch(Exception ex){}
        }
    }

    public void start(Stage stage){
        //score
        score = 0;
        scoreBox = new Rectangle(20,20,150,20);
        scoreHbox = new HBox();
        scoreHbox.setPadding(new Insets(10,10,10,1030));
        scoreBox.setFill(Color.rgb(218, 119, 255, 0.7));
        scoreHbox.getChildren().addAll(scoreBox);
        scoreLabel = new Label("Score: " + String.valueOf(score));
        scoreLabel.setTextFill(Color.rgb(0, 0, 0, 1.0));
        scoreVbox = new VBox();
        scoreVbox.setPadding(new Insets(10,10,10,1050));
        scoreVbox.getChildren().add(scoreLabel);

        stage.setTitle("Minion");
        root = new Group();
        stackpane = new StackPane();
        mainbgscene = new Scene(root,1200,650);

        //All types of Image loading
        mainbgv = new ImageView();
        mainbg = new Image("minion/image/level1.jpeg");
        mainbgv.setImage(mainbg);
        mainvbox = new VBox();
        mainvbox.getChildren().add(mainbgv);
        heart();
        character();
        obstacle();
        stackpane.getChildren().addAll(mainvbox,heartHbox,scoreHbox,scoreVbox,characterVbox,obsVbox);
        root.getChildren().add(stackpane);

        mainbgscene.addEventFilter(KeyEvent.KEY_PRESSED,e->{
            if(flag1){
                flag1 = false;
                Thread t1 = new Thread(this);
                t1.start();
            }
            //Character Move in up
        /*if(e.getCode()==KeyCode.UP){
            Runnable r2 = new Runnable(){
                public void run(){
                for(int i =0;i<30;++i){
                    characterY -= 1;
                    characterIm.setTranslateY(characterY);
                    try{

                    }catch(Exception e){System.out.println("Exception handled "+e);}
                }
                }
            };
            Thread t2 = new Thread(r2);
            t2.start();
            t2.interrupt();
        }*/

            if(e.getCode()==KeyCode.UP){
                TranslateTransition translateTransUp =
                        new TranslateTransition(Duration.millis(600), characterIm);
                translateTransUp.setFromY(characterY);
                characterY -= 90.0;
                characterBoundary = characterBound(characterX,characterY);
                if(characterBoundary == 1){
                    translateTransUp.setToY(characterY);
                    translateTransUp.play();
                }
                else
                    characterY += 90.0;
            }
            if(e.getCode()==KeyCode.RIGHT){
                TranslateTransition translateTransition =
                        new TranslateTransition(Duration.millis(600), characterIm);
                translateTransition.setFromX(characterX);
                characterX += 100.0;
                characterBoundary = characterBound(characterX,characterY);
                if(characterBoundary==1){
                    translateTransition.setToX(characterX);
                    translateTransition.play();
                }
                else
                    characterX -= 100.0;
            }
            if(e.getCode()==KeyCode.DOWN){
                TranslateTransition translateTransition =
                        new TranslateTransition(Duration.millis(600), characterIm);
                translateTransition.setFromY(characterY);
                characterY += 90.0;
                characterBoundary = characterBound(characterX,characterY);
                if(characterBoundary==1){
                    translateTransition.setToY(characterY);
                    translateTransition.play();
                }
                else
                    characterY -= 90.0;
            }
            if(e.getCode()==KeyCode.LEFT){
                TranslateTransition translateTransition =
                        new TranslateTransition(Duration.millis(600), characterIm);
                translateTransition.setFromX(characterX);
                characterX -= 100.0;
                characterBoundary = characterBound(characterX,characterY);
                if(characterBoundary==1){
                    translateTransition.setToX(characterX);
                    translateTransition.play();
                }
                else
                    characterX += 100.0;
            }

        });
        stageContainer = stage;
        stage.setScene(mainbgscene);
        stage.show();
    }

    //Character choose
    public void character(){
        characterIm = new ImageView();
        character = new Image("minion/image/minion.png");
        characterIm.setImage(character);
        characterIm.setTranslateX(characterX);
        characterIm.setTranslateY(characterY);
        characterVbox = new VBox();
        characterVbox.getChildren().add(characterIm);
    }
    //Select character Boundary
    public int characterBound(double characterX,double characterY){
        if(characterX <= 1100.0 && characterX >= 0.0 && characterY <= 450.0 && characterY >= 0.0)
            return 1;
        else
            return 0;
    }

    //Random range
    public int Range(int max,int min){
        int range = Math.abs(max - min) + 1;
        return (int)(Math.random() * range) + (min <= max ? min : max);
    }

    //Generating obstacle initially
    public void obstacle(){
        int a = Range(200,100);
        obsIm = new ImageView();
        obs = obstacleSelect();
        obsIm.setImage(obs);
        obsIm.setTranslateX(obsStartX);
        obsIm.setTranslateY(a);

        int b = Range(200,100);
        obsIm4 = new ImageView();
        obs4 = obstacleSelect();
        obsIm4.setImage(obs4);
        obsIm4.setTranslateX(obsStart4X);
        obsIm4.setTranslateY(a);

        int c = Range(380,280);
        obsIm2 = new ImageView();
        obs2 = obstacleSelect();
        obsIm2.setImage(obs2);
        obsIm2.setTranslateX(obsStart2X);
        obsIm2.setTranslateY(c);

        int d = Range(380,280);
        obsIm5 = new ImageView();
        obs5 = obstacleSelect();
        obsIm5.setImage(obs5);
        obsIm5.setTranslateX(obsStart5X);
        obsIm5.setTranslateY(d);

        int e = Range(380,280);
        obsIm7 = new ImageView();
        obs7 = obstacleSelect();
        obsIm7.setImage(obs7);
        obsIm7.setTranslateX(obsStart7X);
        obsIm7.setTranslateY(e);

        int f = Range(530,400);
        obsIm3 = new ImageView();
        obs3 = obstacleSelect();
        obsIm3.setImage(obs3);
        obsIm3.setTranslateX(obsStart3X);
        obsIm3.setTranslateY(f);

        int g = Range(530,400);
        obsIm6 = new ImageView();
        obs6 = obstacleSelect();
        obsIm6.setImage(obs6);
        obsIm6.setTranslateX(obsStart6X);
        obsIm6.setTranslateY(g);

        obsVbox = new VBox();
        obsVbox.getChildren().addAll(obsIm,obsIm2,obsIm3,obsIm4,obsIm5,obsIm6,obsIm7);
    }
    //Select obstacle Randomly
    public Image obstacleSelect(){
        String obsName;
        int choice = Range(0,6);//7 types of obstacle
        switch (choice) {
            case 0:
                obsName = "minion/image/obs1.png";
                break;
            case 1:
                obsName = "minion/image/obs2.png";
                break;
            case 2:
                obsName = "minion/image/obs3.png";
                break;
            case 3:
                obsName = "minion/image/obs4.png";
                break;
            case 4:
                obsName = "minion/image/obs5.png";
                break;
            case 5:
                obsName = "minion/image/obs6.png";
                break;
            default:
                obsName = "minion/image/obs7.png";
                break;
        }
        Image image = new Image(obsName);
        return image;
    }

    //Showing heart
    public void heart(){
        heart1 = new Image("minion/image/heart.png");

        heartIm1 = new ImageView();
        heartIm1.setImage(heart1);

        heartIm2 = new ImageView();
        heartIm2.setImage(heart1);

        heartIm3 = new ImageView();
        heartIm3.setImage(heart1);

        heartHbox = new HBox();
        heartHbox.setPadding(new Insets(0,0,0,15));
        heartHbox.getChildren().addAll(heartIm1,heartIm2,heartIm3);
    }
    public void updatePoint(){
        //obsStartX==characterX&&characterIm.getY()==obsIm.getY()
        if(obsIm.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm.setVisible(false);
            life -= 1;
            lifeUpdate();
            score+=10;
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
        if(obsIm2.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm2.setVisible(false);
            life -= 1;
            lifeUpdate();
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
        if(obsIm3.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm3.setVisible(false);
            score += 20;
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
        if(obsIm4.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm4.setVisible(false);
            score += 30;
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
        if(obsIm5.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm5.setVisible(false);
            score += 50;
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
        if(obsIm6.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm6.setVisible(false);
            score += 40;
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
        if(obsIm7.getBoundsInParent().intersects(characterIm.getBoundsInParent())){
            obsIm7.setVisible(false);
            score += 40;
            Platform.runLater(()->{
                scoreLabel.setText("Score: " + String.valueOf(score));
            });
        }
    }
    public void lifeUpdate(){
        switch (life) {
            case 3:
                heartIm3.setVisible(false);
                break;
            case 2:
                heartIm2.setVisible(false);
                break;
            case 1:
                heartIm1.setVisible(false);
                break;
            default:
        }
    }

    public int getScore(){
        return score;
    }
}