package model;

import javafx.animation.Transition;
import view.GameLauncher;

import java.util.ArrayList;

public class Game {
    public final double WIDTH = 920;
    public final double HEIGHT = 670;
    public int[][] grid = new int[20][30];
    public String username;
    public GameLauncher gameLauncher;
    public int score = 0;
    public int lives;
    public int mapNum;
    public ArrayList<Point> points = new ArrayList<>();
    public ArrayList<Transition> animations = new ArrayList<>();
    public Game(String username, GameLauncher gameLauncher, int lives, int score, ArrayList<Point> points, int mapNum) {
        this.username = username;
        this.gameLauncher = gameLauncher;
        this.lives = lives;
        this.score = score;
        this.points.addAll(points);
        this.mapNum = mapNum;
    }
    public void getGrid(int [][] grid) {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 30; j++)
                this.grid[i][j] = grid[i][j];
    }
    public void getPoint(Point point) {
        points.add(point);
    }
}
