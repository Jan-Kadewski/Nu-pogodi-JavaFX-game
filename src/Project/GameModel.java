package Project;

public class GameModel {
    public static int score;
    public static int healthPoint;
    public static int timer;

    public static int getHealthPoint() {
        return healthPoint;
    }
    public static void setHealthPoint(int healthPoint) {
        GameModel.healthPoint = healthPoint;
    }


}
