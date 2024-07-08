package view;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class startMenu extends Application {
    applicationController controller = new applicationController();
    Media media;
    MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(startMenu.class.getResource("/FXML/startMenu.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("PACMAN");

        media = new Media(getClass().getResource("/Media/Game Start.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        controller.setStage(stage);
        controller.setMediaPlayer(mediaPlayer);
        stage.show();

    }


}