package view;

import controller.applicationController;
import controller.gameController;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import model.*;
import model.Point;

import java.util.ArrayList;

public class GameLauncher extends Application {
    public final double WIDTH = 920;
    public final double HEIGHT = 670;
    public Pacman pacman;
    public Ghost []ghosts;
    public ArrayList<Wall> walls = new ArrayList<>();
    public final Game game;
    public Pane pane;
    public ArrayList<Point> points = new ArrayList<>();
    public Text scoreText;
    public Media media;
    MediaPlayer mediaPlayer;
    public int[][] grid = new int[20][30];

    public GameLauncher (String username, int lives, int score, ArrayList<Point> points, int mapNum) {
        game = new Game(username, this, lives, score, points, mapNum);

    }
    @Override
    public void start(Stage stage) throws Exception {

        pane = new Pane();
        setSize();
        creatPacman();
        creatGhosts();
        creatWalls();
        creatPoints();
        creatlives();
        creatScoreboard();
        pane.getChildren().add(pacman);
        for (int i = 0; i < ghosts.length; i++)
            pane.getChildren().add(ghosts[i]);
        for (int i = 0; i < walls.size(); i++)
            pane.getChildren().add(walls.get(i));

        media = new Media(getClass().getResource("/Media/Intermission.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        applicationController.setMediaPlayer(mediaPlayer);
        //mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        stage.setScene(new Scene(pane));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        pacman.requestFocus();

    }

    private void creatPoints() {
        if (game.lives == 3) {
            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 30; j++)
                    if (grid[i][j] == 0) {
                        grid[i][j] = 1;
                        points.add(new Point(game, j, i));
                        pane.getChildren().add(points.get(points.size() - 1));
                        game.getPoint(points.get(points.size() - 1));

                    }
        }
        else {
            points.clear();
            for (int i = 0; i < game.points.size(); i++) {
                grid[game.points.get(i).yindex][game.points.get(i).xindex] = 1;
                pane.getChildren().add(game.points.get(i));
                points.add(game.points.get(i));
            }
        }
        game.getGrid(grid);
    }

    private void creatScoreboard() {
        HBox hBox = new HBox();
        scoreText = new Text(360, 50, Integer.toString(game.score));
        scoreText.setFill(Color.OLIVE);
        scoreText.setFont(Font.font(35));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(scoreText);
        hBox.setLayoutX(25);
        hBox.setLayoutY(620);
        pane.getChildren().add(hBox);
    }

    private void creatlives() {
        for (int i = 0; i < game.lives; i++) {
            Circle circle = new Circle(WIDTH - 30 - 50 * i, 620 + 25, 20);
            circle.setFill(new ImagePattern(new Image(Pacman.class.getResource("/images/pacmanR.png").toExternalForm())));
            pane.getChildren().add(circle);
        }
    }

    private void creatWalls() {
        int num = game.mapNum;
        if (num == 1)
            creatMap1();
        else if (num == 2)
            creatMap2();
    }

    private void creatMap2() {
        for (int i = 0; i < 30; i++) {
            grid[0][i] = -1;
            grid[19][i] = -1;
        }
        for (int i = 0; i < 20; i++) {
            grid[i][0] = -1;
            grid[i][29] = -1;
        }

        grid[2][7] = grid[2][8] = grid[2][9] = grid[2][10] = grid[2][11] = grid[2][14] =
                grid[2][15] = grid[2][18] = grid[2][19] = grid[2][20] = grid[2][21] = -1;
        grid[3][3] = grid[3][4] = grid[3][5] = grid[3][10] = grid[3][11] = grid[3][14] =
                grid[3][15] = grid[3][18] = grid[3][19] = grid[3][24] = grid[3][25] = grid[3][26] = -1;
        grid[5][6] = grid[5][7] = grid[5][10] = grid[5][12] = grid[5][13] = grid[5][14] =
                grid[5][15] = grid[5][16] = grid[5][17] = grid[5][19] = grid[5][22] = grid[5][23] = -1;
        grid[6][6] = grid[6][7] = grid[6][10] = grid[6][19] = grid[6][22] = grid[6][23] = -1;
        grid[7][5] = grid[7][6] = grid[7][7] = grid[7][8] = grid[7][10] = grid[7][12] = grid[7][17] =
                grid[7][19] = grid[7][21] = grid[7][22] = grid[7][23] = grid[7][24] = -1;
        grid[8][3] = grid[8][10] = grid[8][12] = grid[8][17] = grid[8][19] = grid[8][26] = -1;
        grid[9][3] = grid[9][12] = grid[9][13] = grid[9][14] = grid[9][15] = grid[9][16] = grid[9][17] =
                grid[9][26] = -1;
        grid[10][3] = grid[10][4] = grid[10][5] = grid[10][6] = grid[10][9] = grid[10][20] = grid[10][23] =
                grid[10][24] = grid[10][25] = grid[10][26] = -1;
        grid[11][3] = grid[11][9] = grid[11][12] = grid[11][13] = grid[11][16] = grid[11][17] =
                grid[11][20] = grid[11][26] = -1;
        grid[12][3] = grid[12][5] = grid[12][6] = grid[12][7] = grid[12][8] = grid[12][9] =
                grid[12][12] = grid[12][13] = grid[12][16] = grid[12][17] = grid[12][20] = grid[12][21] =
                        grid[12][22] = grid[12][23] = grid[12][24] = grid[12][26] = -1;
        grid[14][3] = grid[14][7] = grid[14][8] = grid[14][9] = grid[14][10] = grid[14][12] = grid[14][13] =
                grid[14][14] = grid[14][15] = grid[14][16] = grid[14][17] = grid[14][19] =
                        grid[14][20] = grid[14][21] = grid[14][22] = grid[14][26] = -1;
        grid[15][3] = grid[15][10] = grid[15][14] = grid[15][15] = grid[15][19] = grid[15][26] = -1;
        grid[16][3] = grid[16][10] = grid[16][14] = grid[16][15] = grid[16][19] = grid[16][26] =
                grid[16][12] = grid[16][17] = -1;
        grid[17][3] = grid[17][4] = grid[17][5] = grid[17][6] = grid[17][10] = grid[17][29] = grid[17][23] =
                grid[17][24] = grid[17][25] = grid[17][26] = grid[17][14] = grid[17][15] =
                        grid[17][12] = grid[17][17] = -1;
        grid[18][12] = grid[18][17] = -1;

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 30; j++)
                if (grid[i][j] == -1) {
                    walls.add(new Wall(game, j, i));
                }

        game.getGrid(grid);
        pacman.xindex = 20;

    }

    private void creatMap1() {
        for (int i = 0; i < 30; i++) {
            grid[0][i] = -1;
            grid[19][i] = -1;
        }
        grid[1][0] = grid[2][0] = grid[3][0] = grid[4][0] = -1;
        grid[4][1] = grid[5][1] = grid[6][1] = grid[7][1] = grid[8][1] = -1;
        grid[8][0] = -1;
        grid[10][0] = grid[10][1] = grid[10][2] = -1;
        grid[8][3] = grid[9][3] = grid[10][3] = grid[11][3] = grid[12][3] = -1;
        grid[12][0] = -1;
        grid[12][1] = grid[13][1] = grid[14][1] = -1;
        grid[14][0] = grid[15][0] = grid[16][0] = grid[17][0] = grid[18][0] = -1;
        grid[3][3] = grid[4][3] = grid[5][3] = -1;
        grid[3][4] = grid[4][4] = grid[5][4] = -1;
        grid[14][3] = grid[15][3] = grid[16][3] = -1;
        grid[14][4] = grid[15][4] = grid[16][4] = -1;
        grid[3][6] = grid[4][6] = grid[5][6] = grid[6][6] = grid[7][6] = -1;
        grid[11][5] = grid[11][6] = grid[11][7] = grid[11][8] = -1;
        grid[4][8] = grid[5][8] = grid[6][8] = grid[7][8] = grid[8][8] = grid[9][8] = -1;
        grid[9][7] = -1;
        grid[14][6] = grid[15][6] = grid[16][6] = -1;
        grid[14][8] = grid[15][8] = grid[16][8] = -1;
        grid[14][7] = grid[16][7] = -1;
        grid[2][9] = grid[2][10] = grid[2][11] = -1;
        grid[3][11] = grid[4][11] = -1;
        grid[6][10] = grid[7][10] = grid[8][10] = grid[9][10] = grid[10][10] = grid[11][10] = grid[12][10] = grid[13][10] = -1;
        grid[11][11] = grid[11][12] = -1;
        grid[15][11] = grid[16][11] = grid[17][11] = -1;
        grid[15][12] = -1;
        grid[8][12] = grid[8][13] = grid[8][14] = grid[8][15] = grid[8][16] = grid[8][17] = -1;
        grid[6][12] = grid[7][12] = -1;
        //grid[6][13] = -1;
        grid[6][17] = grid[7][17] = -1;
        //grid[6][16] = -1;
        grid[4][13] = grid[4][14] = grid[4][15] = grid[4][16] = -1;
        grid[2][14] = grid[2][15] = -1;
        grid[3][14] = grid[3][15] = -1;
        grid[10][14] = grid[10][15] = -1;
        grid[11][14] = grid[11][15] = -1;
        grid[12][14] = grid[12][15] = -1;
        grid[13][12] = grid[13][13] = grid[13][14] = grid[13][15] = grid[13][16] = grid[13][17] = -1;
        grid[17][13] = grid[17][14] = grid[17][15] = grid[17][16] = -1;
        grid[6][19] = grid[7][19] = grid[8][19] = grid[9][19] = grid[10][19] = grid[11][19] = grid[12][19] = grid[13][19] = -1;
        grid[11][17] = grid[11][18] = -1;
        grid[15][17] = grid[15][18] = -1;
        grid[16][18] = grid[17][18] = -1;
        grid[2][18] = grid[2][19] = grid[2][20] = -1;
        grid[3][18] = grid[4][18] = -1;
        grid[4][21] = grid[5][21] = grid[6][21] = grid[7][21] = grid[8][21] = grid[9][21] = -1;
        grid[9][22] = -1;
        grid[11][21] = grid[11][22] = grid[11][23] = grid[11][24] = -1;
        grid[14][21] = grid[15][21] = grid[16][21] = -1;
        grid[14][23] = grid[15][23] = grid[16][23] = -1;
        grid[14][22] = grid[16][22] = -1;
        grid[3][23] = grid[4][23] = grid[5][23] = grid[6][23] = grid[7][23] = -1;
        grid[3][25] = grid[4][25] = grid[5][25] = -1;
        grid[3][26] = grid[4][26] = grid[5][26] = -1;
        grid[8][26] = grid[9][26] = grid[10][26] = grid[11][26] = grid[12][26] = -1;
        grid[14][25] = grid[15][25] = grid[16][25] = -1;
        grid[14][26] = grid[15][26] = grid[16][26] = -1;
        grid[10][27] = grid[10][28] = grid[10][29] = -1;
        grid[1][29] = grid[2][29] = grid[3][29] = grid[4][29] = -1;
        grid[4][28] = grid[5][28] = grid[6][28] = grid[7][28] = grid[8][28] = -1;
        grid[8][29] = -1;
        grid[12][29] = -1;
        grid[12][28] = grid[13][28] = grid[14][28] = -1;
        grid[14][29] = grid[15][29] = grid[16][29] = grid[17][29] = grid[18][29] = -1;

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 30; j++)
                if (grid[i][j] == -1) {
                    walls.add(new Wall(game, j, i));
                }
        //not wall but wasted
        grid[5][0] = grid[6][0] = grid[7][0] = -1;
        grid[13][0] = -1;
        grid[15][7] = -1;
        grid[15][22] = -1;
        grid[5][29] = grid[6][29] = grid[7][29] = -1;
        grid[13][29] = -1;

        game.getGrid(grid);
    }

    private void creatGhosts() {
        ghosts = new Ghost[4];
        for (int i = 0; i < ghosts.length; i++) {
            int x, y;
            if (i == 0) {y = 7; x = 13;}
            else if (i == 1) {y = 7; x = 14;}
            else if (i == 2) {y = 7; x = 15;}
            else {y = 7; x = 16;}
            ghosts[i] = new Ghost(pane, x, y, game, i + 1, pacman);
        }
    }

    private void creatPacman() {
        pacman = new Pacman(pane, game);
        pacman.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) {
                gameController.moveUp(pacman, HEIGHT);
            } else if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) {
                gameController.moveLeft(pacman, WIDTH);
            } else if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) {
                gameController.moveRight(pacman, WIDTH);
            } else if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) {
                gameController.moveDown(pacman, HEIGHT);
            }
        });
    }

    private void setSize() {
        pane.setMaxWidth(WIDTH);
        pane.setMaxHeight(HEIGHT);
        pane.setMinWidth(WIDTH);
        pane.setMinHeight(HEIGHT);
    }

}
