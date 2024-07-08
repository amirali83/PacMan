package view.animation;

import controller.applicationController;
import controller.gameController;
import javafx.scene.layout.Pane;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import model.Pacman;
import model.Point;
import view.endMenu;

import java.util.Random;

public class PacmanMovingAnimation extends Transition {
    private final Pane pane;
    private final Pacman pacman;
    private final double duration = 200;
    private int xindexb;
    private int yindexb;
    private boolean flag = false;

    public PacmanMovingAnimation(Pacman pacman, Pane pane) {
        this.pane = pane;
        this.pacman = pacman;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int ppoint = 100;

        if (pacman.game.grid[pacman.yindex][pacman.xindex] == 1) {
            for (Point point: pacman.game.points)
                if (point.xindex == pacman.xindex && point.yindex == pacman.yindex) {
                    if (point.esp)
                        ppoint = 400;
                    pane.getChildren().remove(point);
                    pacman.game.points.remove(point);
                    break;
                }
            pacman.game.grid[pacman.yindex][pacman.xindex] = 0;
            pacman.game.score += ppoint;
            ppoint = 100;
            pacman.game.gameLauncher.scoreText.setText("Score: " + pacman.game.score);
            if (pacman.game.points.isEmpty()) {
                gameController.stopAnimation(pacman.game);
                applicationController.getMediaPlayer().stop();
                try {
                    endMenu endMenu = new endMenu(pacman.game.score, true);
                    endMenu.start(applicationController.getStage());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        int num;
        if (v < 0.5) num = 1;
        else num = 2;

        if (num == 1)
            pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanC.png").toExternalForm())));
        else {
            if (pacman.move == 0) {
                pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanR.png").toExternalForm())));
            } else if (pacman.move == 1) {
                pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanD.png").toExternalForm())));
            } else if (pacman.move == 2) {
                pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanL.png").toExternalForm())));
            } else if (pacman.move == 3) {
                pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanU.png").toExternalForm())));
            }
        }
        if (!flag) {
            xindexb = pacman.xindex;
            yindexb = pacman.yindex;
            if (pacman.move == 0) {
                if (pacman.xindex < 29 && pacman.game.grid[pacman.yindex][pacman.xindex + 1] != -1)
                    pacman.xindex++;
            } else if (pacman.move == 1) {
                if (pacman.yindex < 19  && pacman.game.grid[pacman.yindex + 1][pacman.xindex] != -1)
                    pacman.yindex++;
            } else if (pacman.move == 2) {
                if (pacman.xindex > 0 && pacman.game.grid[pacman.yindex][pacman.xindex - 1] != -1)
                    pacman.xindex--;
            } else if (pacman.move == 3) {
                if (pacman.yindex > 0 && pacman.game.grid[pacman.yindex - 1][pacman.xindex] != -1)
                    pacman.yindex--;
            }
            flag = true;
        }
        if (v == 1)
            flag = false;

        pacman.setCenterY(10 + yindexb * 30 + (pacman.yindex - yindexb) * v * 30 + 15);
        pacman.setCenterX(10 + xindexb * 30 + (pacman.xindex - xindexb) * v * 30 + 15);
    }
}
