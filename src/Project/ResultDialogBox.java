package Project;

import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class ResultDialogBox {

    public ResultDialogBox() {
        initDialogBox();
    }

    public static void initDialogBox() {
        Dialog dialog = new TextInputDialog();
        dialog.setTitle("Game over");
        dialog.setHeaderText("Your score is:" + " " + " Enter your name");
        Optional<String> result = dialog.showAndWait();
        String entered = "none";
        if (result.isPresent()) {
            entered = result.get();
        }
    }
}
