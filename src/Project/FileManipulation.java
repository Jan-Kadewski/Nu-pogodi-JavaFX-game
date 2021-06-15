package Project;

import java.io.*;

public class FileManipulation {

    String name;
    int points;

    public FileManipulation() throws IOException {

        try {
            saveToHighRank();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void saveToHighRank() throws IOException {
        FileWriter writer = new FileWriter("highScore.txt", true);
        writer.write(name +" " + points+ " :" );
        writer.write("\r\n");   // write new line
        writer.close();


    }
    public static void readHighRankFromFile(){
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

