import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.Node;

/*****************************************
* Dice animation for the battleship game
* 
* @author Adham Tawfik
* @version 1.0
****************************************/
public class DiceAnimation extends Application
{
    Rectangle dice = new Rectangle(100,100,150,150);
    Circle onlyOne = new Circle(175,175,20);
    
    Circle firstTwo = new Circle(135,140,15);
    Circle secondTwo = new Circle(215,210,15);
    
    Circle firstThree = new Circle(175,175,15);
    Circle secondThree = new Circle(215,210,15);
    Circle thirdThree = new Circle(135,140,15);
    
    Circle firstFour = new Circle(215,210,15);
    Circle secondFour = new Circle(215,140,15);
    Circle thirdFour = new Circle(135,210,15);
    Circle fourthFour = new Circle(135,140,15);

    Circle firstFive = new Circle(175,175,15);
    Circle secondFive = new Circle(215,140,15);
    Circle thirdFive = new Circle(135,210,15);
    Circle fourthFive = new Circle(135,140,15);
    Circle fifthFive = new Circle(215,210,15);
    
    Circle firstSix = new Circle(215,220,15);
    Circle secondSix = new Circle(215,130,15);
    Circle thirdSix = new Circle(215,175,15);
    Circle fourthSix = new Circle(135,130,15);
    Circle fifthSix = new Circle(135,175,15);
    Circle sixthSix = new Circle(135,220,15);
    
    
    Group root = new Group(); 
    Group one = new Group();
    Group two = new Group();
    Group three = new Group();
    Group four = new Group();
    Group five = new Group();
    Group six = new Group();
    Group diceWithNum = new Group();
    
    
    public void start(Stage stage)
    {
        
        
        root.getChildren().add(one);
        diceWithNum.getChildren().addAll(dice,root);
        Scene scene = new Scene(diceWithNum, 500,300);
        stage.setTitle("Dice roll animation");
        stage.setScene(scene);
        // Show the Stage (window)
        stage.show();
    }

    /************************************************
     * Plays the animation and ends up on the number 
     * specified in the parameter
     ************************************************/
    public void rollDice(int n)
    {
        Timeline timeline = new Timeline();
    
        one.getChildren().add(onlyOne);
        two.getChildren().addAll(firstTwo,secondTwo);
        three.getChildren().addAll(firstThree,secondThree,thirdThree);
        four.getChildren().addAll(firstFour,secondFour,thirdFour,fourthFour);
        five.getChildren().addAll(firstFive,secondFive,thirdFive,fourthFive,fifthFive);
        six.getChildren().addAll(firstSix,secondSix,thirdSix,fourthSix,fifthSix,sixthSix);
        
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(0), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(one);            
                }),
            new KeyFrame(Duration.seconds(0.2), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(two);     
                }),
            new KeyFrame(Duration.seconds(0.4), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(three);     
                }),
            new KeyFrame(Duration.seconds(0.6), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(four);     
                }),
            new KeyFrame(Duration.seconds(0.8), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(five);     
                }),
             new KeyFrame(Duration.seconds(1), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(six);     
                }), 
             new KeyFrame(Duration.seconds(1.2), event ->
                {
                    root.getChildren().clear();
                    root.getChildren().add(getGroup(n));   
                }));   
        
            
        
        // Start the animation
        timeline.play();
    }

    
    public Group getGroup(int n)
    {
        Group[] number = {one,two,three,four,five,six};
        
        return number[n-1];
    }
}
