package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Point extends Circle {
    public int xindex;
    public int yindex;
    public final Game game;
    public boolean esp;

    public Point(Game game, int xindex, int yindex) {
        super(xindex * 30 + 10 + 15, yindex * 30 + 10 + 15, 5);
        this.game = game;
        this.xindex = xindex;
        this.yindex = yindex;
        Random r = new Random();
        if (Math.abs(r.nextInt()) % 30 == 1) {
            setFill(Color.RED);
            esp = true;
        }
        else {
            setFill(Color.ORANGE);
            esp = false;
        }
    }
}
