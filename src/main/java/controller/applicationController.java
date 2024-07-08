package controller;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class applicationController {
    private static Stage stage;
    private static MediaPlayer mediaPlayer;

    public static void setStage(Stage stage) {
        applicationController.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        applicationController.mediaPlayer = mediaPlayer;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
