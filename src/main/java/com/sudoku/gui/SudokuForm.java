package com.sudoku.gui;

import com.sudoku.data.Data;
import com.sudoku.data.FileData;
import com.sudoku.data.Style;
import com.sudoku.file.PathDriver;
import com.sudoku.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

@Getter
public class SudokuForm {
    private final Game game;

    private Button btnResolve = new Button("Resolve");
    private Button btnExit = new Button("Exit");
    private final MenuItem menuNew = new MenuItem("New sudoku");
    private final MenuItem menuTemplates = new MenuItem("Templates");
    private final MenuItem menuRandom = new MenuItem("Random sudoku");
    private final Label lblStatus = new Label();

    public SudokuForm(Game game) {
        this.game = game;
    }

    public void open() {
        Stage window = new Stage();
        Image icon = new Image(PathDriver.getInstance().getPath(FileData.MAIN_FORM_ICO));
        window.getIcons().add(icon);
        window.setScene(setScene());
        window.initStyle(StageStyle.DECORATED);
        window.setTitle("Sudoku solver");
        window.show();
    }

    private Scene setScene() {

        // main scene
        BorderPane root  = new BorderPane();
        root.setPrefSize(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
        root.setStyle(Style.MAIN_VIEW_BLUE);

        // left, right
        VBox leftBox = new VBox();
        leftBox.setPrefWidth(Data.MARGIN);
        root.setLeft(leftBox);
        VBox rightBox = new VBox();
        rightBox.setPrefWidth(Data.MARGIN);
        root.setRight(rightBox);

        // center
        root.setCenter(createCenterContainer());

        // top
        root.setTop(createTopContainer());

        // scene and event for keyboard
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> game.getSudokuGraphic().enterKeyPressed(e));

        return scene;
    }

    private VBox createTopContainer() {
        VBox vBoxContainer = new VBox();
        vBoxContainer.setStyle(Style.TOP_BOX_STYLE);

        // Menu
        Menu gameMenu = new Menu("Game");
        SeparatorMenuItem[] sep = new SeparatorMenuItem[2];
        sep[0] = new SeparatorMenuItem();
        sep[1] = new SeparatorMenuItem();

        MenuItem menuExit = new MenuItem("Exit");
        gameMenu.getItems().addAll(menuNew, sep[0], menuTemplates, menuRandom, sep[1], menuExit);

        menuNew.setOnAction(e -> game.startGame());
        menuTemplates.setOnAction(e -> game.templateShow());
        menuRandom.setOnAction(e -> game.randomGame());
        menuExit.setOnAction(e -> System.exit(0));

        MenuBar menu = new MenuBar();
        menu.setStyle(Style.MAIN_MENU);
        menu.getMenus().addAll(gameMenu);

        vBoxContainer.getChildren().addAll(menu);

        return vBoxContainer;
    }

    private HBox createCenterContainer() {
        HBox mainBox = new HBox();
        mainBox.setAlignment(Pos.CENTER);

        btnExit.setStyle(Style.BTN_STYLE);
        btnExit.setOnAction(e -> System.exit(0));
        btnResolve.setStyle(Style.BTN_STYLE);
        btnResolve.setOnAction(e -> game.resolve());

        HBox exitBox = new HBox();
        exitBox.setAlignment(Pos.BASELINE_RIGHT);
        exitBox.getChildren().add(btnExit);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.add(game.getSudokuGraphic().getCanvas(), 0, 0, 2, 1);
        gridPane.add(btnResolve, 0, 1, 1,1);
        gridPane.add(exitBox, 1, 1, 1,1);
        gridPane.add(createLeftLegend(), 0, 2, 1, 1);
        gridPane.add(createRightLegend(), 1, 2, 1, 1);

        mainBox.getChildren().add(gridPane);

        return mainBox;
    }

    private VBox createRightLegend() {
        VBox bottomRight = new VBox();
        bottomRight.setStyle(Style.BOTTOM_RIGHT_BOX_STYLE);

        Label lblUser = new Label("User entered numbers");
        lblUser.setTextFill(Data.FONT_USER_COLOR);
        lblUser.setStyle(Style.LABEL_LEGEND_STYLE);

        Label lblSingle = new Label("Single algorithm numbers");
        lblSingle.setTextFill(Data.FONT_SINGLE_ALGORITHM_COLOR);
        lblSingle.setStyle(Style.LABEL_LEGEND_MIDDLE_STYLE);

        Label lblStandard = new Label("Standard algorithm numbers");
        lblStandard.setTextFill(Data.FONT_STANDARD_ALGORITHM_COLOR);
        lblStandard.setStyle(Style.LABEL_LEGEND_MIDDLE_STYLE);

        Label lblBack = new Label("BackTrack algorithm numbers");
        lblBack.setTextFill(Data.FONT_BACK_ALGORITHM_COLOR);
        lblBack.setStyle(Style.LABEL_LEGEND_MIDDLE_STYLE);

        bottomRight.getChildren().addAll(lblUser, lblSingle, lblStandard, lblBack);

        return bottomRight;
    }

    private VBox createLeftLegend() {
        VBox bottomLeft = new VBox();
        bottomLeft.setStyle(Style.BOTTOM_LEFT_BOX_STYLE);

        Label lblInfo = new Label("Status:");
        lblInfo.setTextFill(Color.BLACK);
        lblInfo.setStyle(Style.LABEL_LEGEND_STYLE);
        printStatus(Color.BLACK, "Ready");
        bottomLeft.getChildren().addAll(lblInfo, lblStatus);

        return bottomLeft;
    }

    private void printStatus(Color color, String message) {
        lblStatus.setTextFill(color);
        lblStatus.setStyle(Style.LABEL_LEGEND_STYLE);
        lblStatus.setText(message);
    }
}
