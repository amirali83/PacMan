package model;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.GameLauncher;
import view.animation.GhostsMovingAnimation;

import java.util.Random;

public class Ghost extends Rectangle {
    public final double WIDTH = 30;
    public final double HEIGHT = 30;
    public final Pacman pacman;
    public final Game game;
    public int move;
    public int xindex;
    public int yindex;
    public GhostsMovingAnimation movingAnimation;

    public Ghost(Pane pane, int x, int y, Game game, int num, Pacman pacman) {
        super(10 + x * 30, 10 + y * 30, 30, 30);
        xindex = x;
        yindex = y;
        this.game = game;
        this.pacman = pacman;
        setFill(new ImagePattern(new Image(Ghost.class.getResource("/Images/ghost" + Integer.toString(num) + ".png").toExternalForm())));
        Random random = new Random();
        move = random.nextInt() % 4;
        movingAnimation = new GhostsMovingAnimation(game, pane, this, pacman);
        game.animations.add(movingAnimation);
        movingAnimation.play();
    }

}
