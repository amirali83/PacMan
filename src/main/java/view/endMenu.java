package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class endMenu extends Application {

    public int score;
    public boolean win;

    public endMenu(int score, boolean win) {
        this.score = score;
        this.win = win;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane;
        if (!win)
            pane = fxmlLoader.load(startMenu.class.getResource("/FXML/endMenu.fxml"));
        else
            pane = fxmlLoader.load(startMenu.class.getResource("/FXML/winMenu.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("PACMAN");
        stage.show();
        endMenuController controller = new endMenuController();
        controller.setScore(Integer.toString(score));
        //controller.text.setText(Integer.toString(score));
        //endMenuController controller = fxmlLoader.getController();
        //controller.setScore(score);
        //controller.score.setText(Integer.toString(score));
    }

}
