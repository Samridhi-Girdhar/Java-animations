import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
/***********************************************************************************************************************
 * Answer to Question 2 for assignment 4 where a girl is displayed riding bicycle in day time with animation.
 * 
 * Author@ Samridhi Girdhar
 * Version@ v1
 * Date@26/02/2024
 ********************************************************************************************************************* */
public class AnimatedScene extends Application 
{
    @Override
    public void start(Stage primaryStage) 
    {
        Group root = new Group();
        Scene myScene = new Scene(root, 800, 550, Color.LIGHTBLUE);

        // Sky
        Rectangle sky = new Rectangle(800, 300, Color.LIGHTBLUE);

        // Ground
        Rectangle ground = new Rectangle(800, 150, Color.LIGHTGREEN);
        ground.setY(400);

        // Road
        Rectangle road = new Rectangle(800, 50, Color.GRAY);
        road.setY(425);

        // Sun
        Circle sun = new Circle(100, 100, 50, Color.GOLD);

        // Clouds
        ImageView cloud1 = new ImageView(new Image(getClass().getResourceAsStream("cloud.png")));
        cloud1.setFitWidth(200);
        cloud1.setFitHeight(100);
        cloud1.setX(600);
        cloud1.setY(20);

        ImageView cloud2 = new ImageView(new Image(getClass().getResourceAsStream("cloud.png")));
        cloud2.setFitWidth(200);
        cloud2.setFitHeight(100);
        cloud2.setX(700);
        cloud2.setY(80);

        // Girl on Bicycle
        ImageView girlOnBicycle = new ImageView(new Image(getClass().getResourceAsStream("girlonbicycle.png")));
        girlOnBicycle.setFitWidth(100);
        girlOnBicycle.setFitHeight(200);
        girlOnBicycle.setX(0);
        girlOnBicycle.setY(250);

        // Huts
        ImageView hut1 = new ImageView(new Image(getClass().getResourceAsStream("hut.png")));
        hut1.setFitWidth(150);
        hut1.setFitHeight(150);
        hut1.setX(30);
        hut1.setY(275);

        ImageView hut2 = new ImageView(new Image(getClass().getResourceAsStream("hut.png")));
        hut2.setFitWidth(150);
        hut2.setFitHeight(150);
        hut2.setX(150);
        hut2.setY(275);

        // Tree
        ImageView tree = new ImageView(new Image(getClass().getResourceAsStream("tree.png")));
        tree.setFitWidth(200);
        tree.setFitHeight(250);
        tree.setX(600);
        tree.setY(250);

        // Kid
        ImageView kid = new ImageView(new Image(getClass().getResourceAsStream("kid.png")));
        kid.setFitWidth(50);
        kid.setFitHeight(80);
        kid.setX(300);
        kid.setY(325);

        // Animation for Girl on Bicycle
        Timeline cyclingAnimation = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(girlOnBicycle.translateXProperty(), 0),
                        new KeyValue(girlOnBicycle.scaleXProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(girlOnBicycle.translateXProperty(), 700),
                        new KeyValue(girlOnBicycle.scaleXProperty(), 1)));
        cyclingAnimation.setCycleCount(Timeline.INDEFINITE);
        cyclingAnimation.play();

        // Animation for Clouds
        Timeline cloudAnimation = new Timeline(
        new KeyFrame(Duration.ZERO,
        new KeyValue(cloud1.translateXProperty(), 50), 
        new KeyValue(cloud2.translateXProperty(), 50)  
        ),
        new KeyFrame(Duration.seconds(15),
        new KeyValue(cloud1.translateXProperty(), -600), 
        new KeyValue(cloud2.translateXProperty(), -600)  
        ));
        cloudAnimation.setCycleCount(Timeline.INDEFINITE);
        cloudAnimation.play();

        // Animation for Kid waving
        Timeline kidAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(kid.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(kid.rotateProperty(), -15)),
                new KeyFrame(Duration.seconds(4), new KeyValue(kid.rotateProperty(), 0)),
                new KeyFrame(Duration.seconds(6), new KeyValue(kid.rotateProperty(), -15)),
                new KeyFrame(Duration.seconds(8), new KeyValue(kid.rotateProperty(), 0))
        );
        kidAnimation.setCycleCount(Timeline.INDEFINITE);
        kidAnimation.setAutoReverse(false);
        kidAnimation.play();

        //Adding all elements
        root.getChildren().addAll(sky, ground, road, sun, cloud1, cloud2, hut1, hut2, kid, girlOnBicycle, tree);

        //Setting stage and scene
        primaryStage.setTitle("Favorite Activity");
        primaryStage.setScene(myScene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
