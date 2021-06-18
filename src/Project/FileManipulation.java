package Project;

import javax.swing.text.html.ListView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManipulation {

    String name;
    int points;
    ListView rankingView;

    public FileManipulation() throws IOException {
//
//        try {
//            saveToHighRank();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void saveToHighRank() throws IOException {
        FileWriter writer = new FileWriter("highScore.txt", true);
//        writer.write("\r\n");   // write new line
//        writer.close();

        BufferedWriter out = new BufferedWriter(writer);
        out.write(name + " " + GameModel.points + " :");
        out.newLine();
        out.close();
    }

    public List<String> readHighRankFromFile() {
        try {
            // open file to read
            Scanner scanner = new Scanner(new File("highScore.txt"));
            List<String> lines = new ArrayList<>();
            // read until end of file (EOF)
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            // close the scanner
            scanner.close();
            return lines;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

