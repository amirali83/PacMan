package view;

import controller.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Point;

import java.util.ArrayList;

public class startMenuController {
    public TextField username;
    public int mapNum;

    public void startGame(MouseEvent mouseEvent) {
        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username is invalid");
            alert.setContentText("Please enter a username");
            alert.showAndWait();
            return;
        }
        try {
            ArrayList<Point> points = new ArrayList<>();
            points.clear();
            applicationController.getMediaPlayer().stop();
            new GameLauncher(username.getText(), 3, 0, points, mapNum).start(applicationController.getStage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectMap1(MouseEvent mouseEvent) {
        mapNum = 1;
    }

    public void selectMap2(MouseEvent mouseEvent) {
        mapNum = 2;
    }
}
