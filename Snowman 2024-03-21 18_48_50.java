import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.Random;
/***********************************************************************************************************************
 * Answer to Question 1 for assignment 4 where a snowman is displayed above grass and can switch between day and night.
 * 
 * Author@ Samridhi Girdhar
 * Version@ v1
 * Date@26/02/2024
 ********************************************************************************************************************* */
public class Snowman extends Application 
{
    private boolean isDay = true; //to keep track of day/night

    public void start(Stage primaryStage) 
    {
        //Snowman body
        Ellipse base = new Ellipse(80, 210, 80, 60);
        base.setFill(Color.WHITE);

        Ellipse middle = new Ellipse(80, 130, 50, 40);
        middle.setFill(Color.WHITE);

        Circle head = new Circle(80, 70, 30);
        head.setFill(Color.WHITE);

        //Snowman face
        Circle rightEye = new Circle(70, 60, 5);
        Circle leftEye = new Circle(90, 60, 5);
        Polygon nose = new Polygon(70, 75, 100, 105, 85, 75);
        nose.setFill(Color.ORANGE);
        
        //Snowman tummy buttons
        Circle topButton = new Circle(80, 120, 6);
        topButton.setFill(Color.RED);
        Circle bottomButton = new Circle(80, 140, 6);
        bottomButton.setFill(Color.RED);

        //Snowman hands
        Line leftArm = new Line(110, 130, 160, 130);
        leftArm.setStrokeWidth(3);
        leftArm.setStroke(Color.BROWN);
        Line rightArm = new Line(50, 130, 0, 100);
        rightArm.setStrokeWidth(3);
        rightArm.setStroke(Color.BROWN);

        //Snowman hat
        Rectangle stovepipe = new Rectangle(60, 0, 40, 50);
        Rectangle brim = new Rectangle(50, 45, 60, 5);
        stovepipe.setFill(Color.PINK);
        brim.setFill(Color.PINK);
        Group hat = new Group(stovepipe, brim);
        hat.setTranslateX(10);
        hat.setRotate(15);

        //Setting up the snowman together in Group layout manager
        Group snowman = new Group(base, middle, head, leftEye, rightEye,
                nose,  topButton, bottomButton, leftArm, rightArm, hat);
        snowman.setTranslateX(170);
        snowman.setTranslateY(50);

        //Sun at day
        Circle sun = new Circle(50, 50, 30);
        sun.setFill(Color.GOLD);

        //Sky at day
        Rectangle sky = new Rectangle(0, 0, 500, 250);
        sky.setFill(Color.LIGHTBLUE);
        
        //Grass below snowman on the ground
        Group grass = new Group();
        for (int i = 230; i <= 350; i += 10) {
            for (int j = 0; j <= 500; j += 30) {
                Polygon grassTriangle = new Polygon(
                        j, i,                // Point 1 (x, y)
                        j + 15, i - 15,      // Point 2 (x, y)
                        j + 30, i           // Point 3 (x, y)
                );
                grassTriangle.setFill(Color.GREEN);
                grass.getChildren().add(grassTriangle);
            }
        }
        
        //Adding all elements together in group layout manager
        Group root = new Group();
        root.getChildren().addAll(sky, sun, grass, snowman);

        //Button to switch day to night and vice versa
        Button dayNightButton = new Button("Day/Night");
        dayNightButton.setLayoutX(210);
        dayNightButton.setLayoutY(320); 
        dayNightButton.setOnAction(event -> 
        {
            isDay = !isDay;
            if (isDay) 
            {
                //day time
                sky.setFill(Color.LIGHTBLUE);
                sun.setFill(Color.GOLD);
                root.getChildren().removeIf(node -> node instanceof Circle); 
                root.getChildren().add(sun);
                root.getChildren().add(dayNightButton);
            } 
            else 
            {
                //night time
                sun.setFill(Color.TRANSPARENT);
                addNightElements(root); 
                sky.setFill(Color.DARKBLUE); 
            }
        });
        root.getChildren().add(dayNightButton);
        
        //Setting up the scene and stage
        Scene myScene = new Scene(root, 500, 350, Color.DARKBLUE); 
        primaryStage.setTitle("Snowman");
        primaryStage.setScene(myScene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void addNightElements(Group root) 
    {
        //Moon at night 
        Circle moon = new Circle(450, 50, 30);
        moon.setFill(Color.WHITE);
        root.getChildren().add(moon);
 
        //Randomly generated snow
        Random myGen = new Random();
        for (int i = 0; i < 20; i++) 
        {
            Circle snow = new Circle(myGen.nextDouble() * 500, myGen.nextDouble() * 200, 2, Color.WHITE);
            root.getChildren().add(snow);
        }
    }
}
