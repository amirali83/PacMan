package controller;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.Pacman;

public class gameController {
    public static final double speed = 5;
    public static void moveUp(Pacman pacman, double HEIGHT) {
        //pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanU.png").toExternalForm())));
//        if (pacman.yindex > 0 && pacman.game.grid[pacman.yindex - 1][pacman.xindex] != -1)
//            pacman.yindex--;
//        pacman.setCenterY(10 + pacman.yindex * 30 + 15);
        pacman.move = 3;
    }

    public static void moveLeft(Pacman pacman, double WIDTH) {
        //pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanL.png").toExternalForm())));
//        if (pacman.xindex > 0 && pacman.game.grid[pacman.yindex][pacman.xindex - 1] != -1)
//            pacman.xindex--;
//        pacman.setCenterX(10 + pacman.xindex * 30 + 15);
        pacman.move = 2;
    }

    public static void moveRight(Pacman pacman, double WIDTH) {
        //pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanR.png").toExternalForm())));
//        if (pacman.xindex < 29 && pacman.game.grid[pacman.yindex][pacman.xindex + 1] != -1)
//            pacman.xindex++;
//        pacman.setCenterX(10 + pacman.xindex * 30 + 15);
        pacman.move = 0;
    }

    public static void moveDown(Pacman pacman, double HEIGHT) {
        //pacman.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanD.png").toExternalForm())));
//        if (pacman.yindex < 19  && pacman.game.grid[pacman.yindex + 1][pacman.xindex] != -1)
//            pacman.yindex++;
//        pacman.setCenterY(10 + pacman.yindex * 30 + 15);
        pacman.move = 1;
    }

    public static void stopAnimation(Game game) {
        for (Transition animation: game.animations)
            animation.stop();
    }
}
