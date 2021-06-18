package Project;

import javax.swing.text.html.ListView;
import java.io.*;

public class FileManipulation {

    String name;
    int points;
    ListView rankingView;

    public FileManipulation() throws IOException {
        rankingView = new ListView(null);
//
//        try {
//            saveToHighRank();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public  void saveToHighRank() throws IOException {
        FileWriter writer = new FileWriter("highScore.txt", true);
        writer.write(name +" " + points+ " :" );
        writer.write("\r\n");   // write new line
        writer.close();


    }
    public  void readHighRankFromFile(){
        try {
            FileReader reader = new FileReader("highScore.txt");
            int character;

            while ((character = reader.read()) != -1) {

                System.out.print((char) character);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }

