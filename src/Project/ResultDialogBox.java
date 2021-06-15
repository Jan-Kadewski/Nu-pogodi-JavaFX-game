package Project;

import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

public class ResultDialogBox {
    FileManipulation fileManipulation;
    public ResultDialogBox() {

        try {
            fileManipulation = new FileManipulation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void initDialogBox() {

        Dialog dialog = new TextInputDialog();
        dialog.setTitle("Game over");
        dialog.setHeaderText("Your score is:" + " " + " Enter your name");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            fileManipulation.name = result.get();
            try {
                fileManipulation.saveToHighRank();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
