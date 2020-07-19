package com.sudoku.gui;

import com.sudoku.data.FileData;
import com.sudoku.data.Style;
import com.sudoku.file.PathDriver;
import com.sudoku.user.UserInterface;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NumberForm {
    private UserInterface userInterface;
    private boolean[] numbers;
    private Stage window;

    public NumberForm(boolean[] numbers, UserInterface userInterface) {
        this.numbers = numbers;
        this.userInterface = userInterface;
        this.window = new Stage();
    }

    public void open() {
        Image icon = new Image(PathDriver.getInstance().getPath(FileData.MAIN_FORM_ICO));
        window.getIcons().add(icon);
        window.setScene(setScene());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Number choose");
        window.showAndWait();
    }

    private Scene setScene() {

        // main scene
        HBox root = new HBox();
        root.setStyle(Style.MAIN_VIEW_BLUE);

        Button btn1 = createButton(1);
        Button btn2 = createButton(2);
        Button btn3 = createButton(3);
        Button btn4 = createButton(4);
        Button btn5 = createButton(5);
        Button btn6 = createButton(6);
        Button btn7 = createButton(7);
        Button btn8 = createButton(8);
        Button btn9 = createButton(9);
        Button btnCancel = new Button("Delete");
        btnCancel.setStyle(Style.BTN_NUMBER_STYLE);
        btnCancel.setOnAction(e -> {userInterface.setValue(0); window.close();});

        root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnCancel);

        return new Scene(root);
    }

    private Button createButton(int number) {
        Button button = new Button(String.valueOf(number));
        button.setStyle(Style.BTN_NUMBER_STYLE);
        button.setOnAction(e -> {userInterface.setValue(number); window.close();});
        button.setVisible(!numbers[number]);
        return button;
    }
}