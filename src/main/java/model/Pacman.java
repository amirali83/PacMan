package model;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import view.animation.PacmanMovingAnimation;

public class Pacman extends Circle {
    public final double Radius = 15;
    public final Game game;
    public int move;
    public int xindex;
    public int yindex;
    public PacmanMovingAnimation movingAnimation;

    public Pacman(Pane pane, Game game) {
        super(10 + 15 * 30 + 15, 10 + 15 * 30 + 15, 15);
        xindex = 15;
        yindex = 15;
        this.game = game;
        //setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanR.png").toExternalForm())));
        move = 4;
        movingAnimation = new PacmanMovingAnimation(this, pane);
        game.animations.add(movingAnimation);
        movingAnimation.play();
    }
}
