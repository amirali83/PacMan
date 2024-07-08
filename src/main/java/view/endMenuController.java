package view;

import controller.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class endMenuController {
    public TextArea text = new TextArea();
    public Label score = new Label();

//    public endMenuController() {
//        score.setText(Integer.toString(endMenu.getScore()));
//    }

    public void startOver(MouseEvent mouseEvent) {
        try {
            new startMenu().start(applicationController.getStage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Quit(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void setScore(String score) {
        //this.score.setText(score);
        //this.text.setText(score);
        //System.out.println(score);
        //System.out.println("tr");
        System.out.println("your score is: " + score);
    }


}
