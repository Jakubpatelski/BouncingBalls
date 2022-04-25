package com.example.bouncingballs;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RunIt implements Runnable{


    private Circle circle;

    public RunIt( Circle circle) {
        this.circle = circle;
    }




    @Override
    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            double deltaX = 2;
            double deltaY = 2;
            @Override
            public void handle(ActionEvent event) {
                try {
                    Bounds bounds = Bridge.getHelloController().getAnchorpane().getBoundsInLocal();
                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY() + deltaY);

                    boolean rightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                    boolean leftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                    boolean bottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                    boolean topBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

                    if (rightBorder || leftBorder) {
                        deltaX *= -1;
                    }
                    if (bottomBorder || topBorder) {
                        deltaY *= -1;
                    }


                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }





}
