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

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class RunIt implements Runnable{

     private Circle circle;

    public RunIt( Circle circle) {
        this.circle = circle;
    }

   private double directionX = 2;
   private double directionY = 2;
    Circle c1 = Bridge.getHelloController().getCircle();
    Circle c2 = Bridge.getHelloController().getCircle2();

    public boolean checkCollision(){
        // dx = vertical distance between sphere and other sphere
        // dy = horizontal distance between sphere and other sphere
        double dx = c1.getLayoutX() - c2.getLayoutX();
        double dy = c1.getLayoutY() - c2.getLayoutY();
        // distance = distance between the centre of each sphere; Pythagoras' Theorem
        double distance = Math.sqrt((dy * dy) + (dx * dx));
        // return true if the distance between the spheres is lower than their radius, false if not
        return (distance <= (c1.getRadius() + c2.getRadius()));
    }

    @Override
    public void run() {
//it fixes the deadlock of the balls
            synchronized (c1) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    Bounds bounds = Bridge.getHelloController().getAnchorpane().getBoundsInLocal();

                    circle.setLayoutX(circle.getLayoutX() + directionX);
                    circle.setLayoutY(circle.getLayoutY() + directionY);

                    boolean rightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                    boolean leftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                    boolean bottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                    boolean topBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

                        if (rightBorder || leftBorder) {
                            directionX *= -1;
                        }

                        if (bottomBorder || topBorder) {
                            directionY *= -1;
                        }

                        if (checkCollision()){
                        directionX *= -1;
                        directionY *= -1;
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
