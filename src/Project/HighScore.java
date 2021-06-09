package Project;


import javafx.scene.control.ListView;

public class HighScore {
    ListView listview;
    public HighScore(){
        listview = new ListView();
        listview.getItems().add("item1");
        listview.getItems().add("item2");
        listview.getItems().add("item3");
        listview.getItems().add("item4");
    }


}
