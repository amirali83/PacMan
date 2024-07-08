package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle {
    public final double WIDTH = 30;
    public final double HEIGHT = 30;
    public final Game game;
    public int xindex;
    public int yindex;
    public Wall(Game game, int xindex, int yindex) {
        super(10 + xindex * 30, 10 + yindex * 30, 30, 30);
        this.game = game;
        this.xindex = xindex;
        this.yindex = yindex;
        setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/Wall.png").toExternalForm())));
    }
}
