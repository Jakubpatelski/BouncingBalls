package com.example.bouncingballs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController  implements Initializable  {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Circle circle;
    @FXML
    private Circle circle2;
    private RunIt runIt;
    private RunIt rutIt2;
    private Thread thread;
    private Thread thread2 ;
    public AnchorPane getAnchorpane() {
        return anchorpane;
    }
    public Circle getCircle() {
        return circle;
    }
    public Circle getCircle2() {
        return circle2;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bridge.setHelloController(this);
        runIt = new RunIt(circle);
        rutIt2 = new RunIt(circle2);
        thread = new Thread(runIt);
        thread2 = new Thread(rutIt2);
        thread.start();
        thread2.start();
    }

}