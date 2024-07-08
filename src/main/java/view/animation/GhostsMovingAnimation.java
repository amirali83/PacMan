package view.animation;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Game;
import model.Ghost;
import model.Pacman;
import controller.*;
import view.GameLauncher;
import view.endMenu;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Random;

public class GhostsMovingAnimation extends Transition {
    private final Game game;
    private final Pane pane;
    private final Ghost ghost;
    private final Pacman pacman;
    private final int duration = 200;
    private boolean flag1 = false;
    private int xindexb;
    private int yindexb;

    public GhostsMovingAnimation(Game game, Pane pane, Ghost ghost, Pacman pacman) {
        this.game = game;
        this.pane = pane;
        this.ghost = ghost;
        this.pacman = pacman;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {

        if (!flag1) {
            xindexb = ghost.xindex;
            yindexb = ghost.yindex;
            boolean flag = false;
            if (ghost.move == 0 && ghost.xindex < 29 && game.grid[ghost.yindex][ghost.xindex + 1] != -1) {
                ghost.xindex++;
            } else if (ghost.move == 1 && ghost.yindex < 19 && game.grid[ghost.yindex + 1][ghost.xindex] != -1) {
                ghost.yindex++;
            } else if (ghost.move == 2 && ghost.xindex > 0 && game.grid[ghost.yindex][ghost.xindex - 1] != -1) {
                ghost.xindex--;
            } else if (ghost.move == 3 && ghost.yindex > 0 && game.grid[ghost.yindex - 1][ghost.xindex] != -1) {
                ghost.yindex--;
            } else
                flag = true;
            if (flag) {
                Random random = new Random();
                ghost.move = random.nextInt() % 4;
            }
            flag1 = true;
        }

        if (v == 1)
            flag1 = false;

        ghost.setX(10 + xindexb * 30 + (ghost.xindex - xindexb) * 30 * v);
        ghost.setY(10 + yindexb * 30 + (ghost.yindex - yindexb) * 30 * v);

        if (ghost.xindex == pacman.xindex && ghost.yindex == pacman.yindex) {

            gameController.stopAnimation(game);
            applicationController.getMediaPlayer().stop();

            System.out.println(game.lives);
            if (game.lives == 1) {

                try {
                    endMenu endMenu = new endMenu(game.score, false);
                    endMenu.start(applicationController.getStage());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                new GameLauncher(game.username, game.lives - 1, game.score, game.points, game.mapNum).start(applicationController.getStage());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
