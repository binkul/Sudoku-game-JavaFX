package com.sudoku.gui;

import com.sudoku.data.FileData;
import com.sudoku.file.PathDriver;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumberForm {

    public static String show(int number) {
        String[] res = new String[1];
        res[0] = "";
        List<String> choices = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "Remove"));
        String init = number != 0 ? String.valueOf(number) : "Remove";

        ChoiceDialog<String> dialog = new ChoiceDialog<>(init, choices);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(PathDriver.getInstance().getPath(FileData.MAIN_FORM_ICO)));
        ImageView icon = new ImageView(new Image(PathDriver.getInstance().getPath(FileData.SUDOKU_FORM_ICO)));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        dialog.setGraphic(icon);

        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Choose a number");
        dialog.setContentText("Number:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(letter -> res[0] = letter);

        return res[0];
    }
}
