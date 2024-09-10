import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Circle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**************************************************************************
 * This is a battleship style game in a fortnite theme. 
 * GRIDxGRID grid (225 squares)
 * 
 * The game starts by rolling two dies and the sum of them
 * is the user's total amount of turns.
 * 
 * The game has prizes that have different shapes and sizes
 * 
 * Clicking each square the prize occupies reveals the prize
 * and gives you a certain score depending on the size of
 * the prize (smaller gives more, bigger gives less).
 * 
 * The prizes currently existing are:
 * 
 * V-buck prize (1 square) - prize: 100 + dice roll for more turns
 * Bomb prize (1 square) - prize: -200 and you lose a turn
 * Golden fish grand prize (1 square) - prize: 1000 + 2 dice rolls
 * Vending machine (6 sqaures:3 rows & 2 cols) - prize: 125 
 * Fish prize (3 squares: 1 row & 3 cols) - prize: 275
 * LLama prize (3 squares: 2 rows & 2 cols, row 1, col 1 not there)
 * prize: 500
 * 
 * NOTE: hitting a prize does not lose you a turn but missing loses a turn
 * 
 * After rolling the user gets to select squares to click:
 * 
 * The user gets to click on squares until he has lost all turns
 * 
 * Once a user clicks a square, sound & visual alerts will be played
 * The prize does not reveal itself until all squares of the prize are hit.
 * 
 * @author Adham Tawfik,
 * @version 2.2
 ***************************************************************************/

public class BattleShipGame extends Application 
{
    public final int GRID = 12;
    
    //Instance data required for the game
    public int turnsLeft;
    private int score = 0;
    public Label turnsLabel;
    public Label scoreLabel;
    private GridPane gridPane;
    private ImageView fishImageView1;
    private ImageView fishImageView2;
    private boolean fishRevealed = false;
    private boolean secondFishRevealed = false;
    private int fishSquaresRemaining = 3;
    private int secondFishSquaresRemaining = 3;
    
    private MediaPlayer shootPlayer;
    private MediaPlayer hitPlayer;
    private MediaPlayer shockwavePlayer;
    private MediaPlayer winPlayer;
 
    private FishPrize fishPrize1;
    private boolean fishPrize1Revealed = false;
    private FishPrize fishPrize2;
    private boolean fishPrize2Revealed = false;
    
    private VbuckPrize vbuckPrize1;
    private boolean vbuck1Revealed = false;
    private VbuckPrize vbuckPrize2;
    private boolean vbuck2Revealed = false;
    private VbuckPrize vbuckPrize3;
    private boolean vbuck3Revealed = false;
    private VbuckPrize vbuckPrize4;
    private boolean vbuck4Revealed = false;
    private VbuckPrize vbuckPrize5;
    private boolean vbuck5Revealed = false;
    
    private BombPrize bombPrize1;
    private boolean bomb1Revealed = false;
    private BombPrize bombPrize2;
    private boolean bomb2Revealed = false;
    private BombPrize bombPrize3;
    private boolean bomb3Revealed = false;
    private BombPrize bombPrize4;
    private boolean bomb4Revealed = false;
    private BombPrize bombPrize5;
    private boolean bomb5Revealed = false;
    
    private LlamaPrize llamaPrize1;
    private boolean llama1Revealed = false;
    private int llama1SquaresRemaining = 3;
    private LlamaPrize llamaPrize2;
    private boolean llama2Revealed = false;
    private int llama2SquaresRemaining = 3;
    
    private GoldenFishPrize goldenFishPrize;
    private boolean goldenFishRevealed = false;

    private VendingMachinePrize vendingMachinePrize;
    private boolean vendingMachineRevealed = false;
    private int vendingMachineSquaresRemaining = 6;
    
    private Random gen;
    private boolean[][] gridSpace = new boolean[GRID+1][GRID+1];
    
    private ImageView one;
    private ImageView two;
    private ImageView three;
    private ImageView four;
    private ImageView five;
    private ImageView six;
    private ImageView one2;
    private ImageView two2;
    private ImageView three2;
    private ImageView four2;
    private ImageView five2;
    private ImageView six2;
    
    VBox rightBox;
    
    
    @Override
    public void start(Stage primaryStage) 
    {
        BorderPane root = new BorderPane();
        root.setPrefSize(900, 600);
        
        //Load the dice images
        one = new ImageView("one.png");
        one.setFitWidth(100);
        one.setFitHeight(100);
        
        two = new ImageView("two.png");
        two.setFitWidth(100);
        two.setFitHeight(100);
        
        three = new ImageView("three.png");
        three.setFitWidth(100);
        three.setFitHeight(100);
        
        four = new ImageView("four.png");
        four.setFitWidth(100);
        four.setFitHeight(100);
        
        five = new ImageView("five.png");
        five.setFitWidth(100);
        five.setFitHeight(100);
        
        six = new ImageView("six.png");
        six.setFitWidth(100);
        six.setFitHeight(100);
        
        one2 = new ImageView("one.png");
        one2.setFitWidth(100);
        one2.setFitHeight(100);
        
        two2 = new ImageView("two.png");
        two2.setFitWidth(100);
        two2.setFitHeight(100);
        
        three2 = new ImageView("three.png");
        three2.setFitWidth(100);
        three2.setFitHeight(100);
        
        four2 = new ImageView("four.png");
        four2.setFitWidth(100);
        four2.setFitHeight(100);
        
        five2 = new ImageView("five.png");
        five2.setFitWidth(100);
        five2.setFitHeight(100);
        
        six2 = new ImageView("six.png");
        six2.setFitWidth(100);
        six2.setFitHeight(100);
        
        
        // Load the background image
        Image backgroundImage = new Image("fortnitebackground4.jpg");
        
        // Create a background image
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Create a background with the background image
        Background backgroundObj = new Background(background);

        // Create the root pane
        StackPane stackPane = new StackPane();
        root.setBackground(backgroundObj);
        
        // Load sound files
        Media shootSound = new Media(getClass().getResource("shoot.mp3").toExternalForm());
        shootPlayer = new MediaPlayer(shootSound);

        Media hitSound = new Media(getClass().getResource("hit.mp3").toExternalForm());
        hitPlayer = new MediaPlayer(hitSound);
        
        Media shockwaveSound = new Media(getClass().getResource("shockwave.mp3").toExternalForm());
        shockwavePlayer = new MediaPlayer(shockwaveSound);
        
        Media winSound = new Media(getClass().getResource("win.mp3").toExternalForm());
        winPlayer = new MediaPlayer(winSound);

        // Create the title label
        Label titleLabel = new Label("FORTSHIP");
        titleLabel.setTextFill(Color.YELLOW);
        titleLabel.setFont(javafx.scene.text.Font.font("Arial", 30));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);
        
        // Right: Score & turns
        int diceRoll1 = rollDice();
        int diceRoll2 = rollDice2();
        turnsLeft += diceRoll1 + diceRoll2;
        scoreLabel = new Label("Score: 0  ");
        turnsLabel = new Label("Turns Left: "  + "");
        scoreLabel.setFont(javafx.scene.text.Font.font("Arial", 30));
        turnsLabel.setFont(javafx.scene.text.Font.font("Arial", 30));
        
        scoreLabel.setTextFill(Color.YELLOW);
        turnsLabel.setTextFill(Color.YELLOW);
        
        VBox rightBox = new VBox();
        rightBox.getChildren().addAll(scoreLabel,turnsLabel,one,one2);
        rightBox.setAlignment(Pos.CENTER);
        root.setRight(rightBox);
        BorderPane.setAlignment(rightBox, Pos.CENTER);
        
       
        
        // Left: Rules of the game
        Label rules = new Label("Welcome to FortShip!\nThe rules of the game are simple.\nYou will recieve 2 rolls to determine your turns.\nThen you will pick squares where you think the prizes are.\nPrizes will only be revealed once you click\nevery square occupied by that prize.\nThere will be sounds to tell you if you hit something and \nprize squares will be colored in red.\nPrizes:\nFish: 275 v-bucks\nLlama: 500 v-bucks\nV-buck: 1 extra roll and 100 v-bucks\nShockwave grenade: -100 v-bucks\nGolden fish: 2 extra rolls and 1000-vbucks!\nGood luck!");
        rules.setFont(javafx.scene.text.Font.font("Arial", 15));
        rules.setTextFill(Color.YELLOW);
        root.setLeft(rules);
        

        // Bottom: Buttons (in HBox)
        Button reveal = new Button("Reveal");
        Button clear = new Button("Clear");
        
        
        reveal.setOnAction(event -> revealAllPrizes());
        clear.setOnAction(event -> clearMessage(root));
        

        HBox bottomButtons = new HBox(10, reveal,clear);
        bottomButtons.setAlignment(Pos.CENTER);
        root.setBottom(bottomButtons);

        // Center: Grid
        gridPane = createGrid();
        root.setCenter(gridPane);
        
        stackPane.getChildren().add(root);
        
        Scene scene = new Scene(stackPane);
        primaryStage.setTitle("Battleship Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGrid() 
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
         // Create drop shadow effect to simulate the outline
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(Color.BLACK);

        // Add labels for rows (alphabets)
        for (int i = 0; i < GRID; i++) 
        {
            char c = (char) ('A' + i);
            Label label = new Label("      " + String.valueOf(c));
            label.setStyle("-fx-font-size: 16;");
            label.setTextFill(Color.PINK);
            label.setEffect(dropShadow);
            gridPane.add(label, c - 'A' + 1, 0);
        }

        // Add labels for columns (numbers)
        for (int i = 1; i <= GRID; i++) 
        {
            Label label = new Label(String.valueOf(i));
            label.setStyle("-fx-font-size: 14;");
            label.setTextFill(Color.PINK);
            label.setEffect(dropShadow);
            gridPane.add(label, 0, i);
        }

        // Add rectangles (squares) to the grid
        for (int row = 1; row <= GRID; row++) 
        {
            for (int col = 1; col <= GRID; col++) 
            {
                Rectangle square = new Rectangle(60, 40);
                square.setFill(Color.BLACK);
                square.setStroke(Color.BLACK);
                square.setStrokeWidth(2);
                square.setOpacity(0.2);

                int finalRow = row; // To use in the event handler
                int finalCol = col;

                //When the user clicks a square we handle it in a method and
                //disable the square so it cant be clicked again :)
                square.setOnMouseClicked(event -> {
                    handleSquareClick(finalRow, finalCol, square);
                    square.setDisable(true);
                });

                gridPane.add(square, col, row);
            }
        }
        
        /************************************
         * First fish prize
         ***********************************/
        boolean validFishSquare = false;
        do
        {
            try
            {
                validFishSquare = true;
                fishPrize1 = new FishPrize(randomNum(GRID),randomNum(GRID-2),gridSpace);
            }
            catch (InvalidSquareException ise)
            {
                validFishSquare = false;    
            }
        }while(!validFishSquare);
        
        fishImageView1 = new ImageView(new Image("fish.png"));
        fishImageView1.setFitWidth(180); // 3 columns
        fishImageView1.setFitHeight(40); // 1 row
        fishImageView1.setVisible(false);
        gridPane.add(fishImageView1, fishPrize1.getFishPositionCol(), fishPrize1.getFishPositionRow(), 3, 1);

        /****************************
         * Second fish prize
         ****************************/
        boolean validFishSquare2 = false;
        do
        {
            try
            {
                validFishSquare2 = true;
                fishPrize2 = new FishPrize(randomNum(GRID),randomNum(GRID-2),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validFishSquare2 = false;    
            }
        }while(!validFishSquare2);
        
        fishImageView2 = new ImageView(new Image("fish.png"));
        fishImageView2.setFitWidth(180); // 3 columns
        fishImageView2.setFitHeight(40); // 1 row
        fishImageView2.setVisible(false);
        gridPane.add(fishImageView2, fishPrize2.getFishPositionCol(), fishPrize2.getFishPositionRow(), 3, 1);
        
        /****************************
         * First V-buck prize
         ****************************/
        boolean validVbuckSquare = false;
        do
        {
            try
            {
                validVbuckSquare  =true;
                vbuckPrize1 = new VbuckPrize(randomNum(GRID),randomNum(GRID),gridSpace); 
            }
            catch(InvalidSquareException ise)
            {
                validVbuckSquare = false;
            }
        }while(!validVbuckSquare);
        
        gridPane.add(vbuckPrize1.getVbuckImageView(), vbuckPrize1.getVbuckPositionCol(), vbuckPrize1.getVbuckPositionRow(), 1, 1);
        
        /****************************
         * Second V-buck prize
         ****************************/
        boolean validVbuckSquare2 = false;
        do
        {
            try
            {
                validVbuckSquare2  =true;
                vbuckPrize2 = new VbuckPrize(randomNum(GRID),randomNum(GRID),gridSpace); 
            }
            catch(InvalidSquareException ise)
            {
                validVbuckSquare2 = false;
            }
        }while(!validVbuckSquare2);
        
        
        gridPane.add(vbuckPrize2.getVbuckImageView(), vbuckPrize2.getVbuckPositionCol(), vbuckPrize2.getVbuckPositionRow(), 1, 1);
        
        /****************************
         * Third V-buck prize
         ****************************/
        boolean validVbuckSquare3 = false;
        do
        {
            try
            {
                validVbuckSquare3  =true;
                vbuckPrize3 = new VbuckPrize(randomNum(GRID),randomNum(GRID),gridSpace); 
            }
            catch(InvalidSquareException ise)
            {
                validVbuckSquare3 = false;
            }
        }while(!validVbuckSquare3);
        
        gridPane.add(vbuckPrize3.getVbuckImageView(), vbuckPrize3.getVbuckPositionCol(), vbuckPrize3.getVbuckPositionRow(), 1, 1);
        
        /****************************
         * Fourth V-buck prize
         ****************************/
        boolean validVbuckSquare4 = false;
        do
        {
            try
            {
                validVbuckSquare4  =true;
                vbuckPrize4 = new VbuckPrize(randomNum(GRID),randomNum(GRID),gridSpace); 
            }
            catch(InvalidSquareException ise)
            {
                validVbuckSquare4 = false;
            }
        }while(!validVbuckSquare4);
        
        gridPane.add(vbuckPrize4.getVbuckImageView(), vbuckPrize4.getVbuckPositionCol(), vbuckPrize4.getVbuckPositionRow(), 1, 1);
        
        /*********************************
         * Fifth and final V-buck prize
         *********************************/
        boolean validVbuckSquare5 = false;
        do
        {
            try
            {
                validVbuckSquare5  =true;
                vbuckPrize5 = new VbuckPrize(randomNum(GRID),randomNum(GRID),gridSpace); 
            }
            catch(InvalidSquareException ise)
            {
                validVbuckSquare5 = false;
            }
        }while(!validVbuckSquare5);
        
        gridPane.add(vbuckPrize5.getVbuckImageView(), vbuckPrize5.getVbuckPositionCol(), vbuckPrize5.getVbuckPositionRow(), 1, 1);
        
        
        /****************************
         * First bomb prize
         ****************************/
        boolean validBombSquare = false;
        do
        {
            validBombSquare = true;
            
            try
            {
                bombPrize1 = new BombPrize(randomNum(GRID),randomNum(GRID),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validBombSquare = false;
            }
            
        }   
        while (!validBombSquare);
            
        
        /****************************
         * Second bomb prize
         ****************************/
        boolean validBombSquare2 = false;
        do
        {
            validBombSquare2 = true;
            
            try
            {
                bombPrize2 = new BombPrize(randomNum(GRID),randomNum(GRID),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validBombSquare2 = false;
            }
            
        }   
        while (!validBombSquare2);
        
        /****************************
         * Third bomb prize
         ****************************/
        boolean validBombSquare3 = false;
        do
        {
            validBombSquare3 = true;
            
            try
            {    
                bombPrize3 = new BombPrize(randomNum(GRID),randomNum(GRID),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validBombSquare3 = false;
            }
            
        }   
        while (!validBombSquare3);     
        
        
        /****************************
         * Fourth bomb prize
         ****************************/
        boolean validBombSquare4 = false;
        do
        {
            validBombSquare4 = true;
            
            try
            { 
                bombPrize4 = new BombPrize(randomNum(GRID),randomNum(GRID),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validBombSquare4 = false;
            }
            
        }   
        while (!validBombSquare4);         
            
        
        /****************************
         * Fifth bomb prize
         ****************************/
        boolean validBombSquare5 = false;
        do
        {
            validBombSquare5 = true;
            
            try
            {     
                bombPrize5 = new BombPrize(randomNum(GRID),randomNum(GRID),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validBombSquare5 = false;
            }
            
        }   
        while (!validBombSquare5);        
           
        
        //Add all the bomb prizes to the grid.
        gridPane.add(bombPrize1.getBombImageView(), bombPrize1.getBombPositionCol(), bombPrize1.getBombPositionRow(), 1, 1);
        gridPane.add(bombPrize2.getBombImageView(), bombPrize2.getBombPositionCol(), bombPrize2.getBombPositionRow(), 1, 1);
        gridPane.add(bombPrize3.getBombImageView(), bombPrize3.getBombPositionCol(), bombPrize3.getBombPositionRow(), 1, 1); 
        gridPane.add(bombPrize4.getBombImageView(), bombPrize4.getBombPositionCol(), bombPrize4.getBombPositionRow(), 1, 1);
        gridPane.add(bombPrize5.getBombImageView(), bombPrize5.getBombPositionCol(), bombPrize5.getBombPositionRow(), 1, 1);
       
       
        /****************************
         * First llama prize
         ****************************/
        boolean validLlamaSquare = false;
        do
        {
            validLlamaSquare = true;
            
            try
            {     
                llamaPrize1 = new LlamaPrize(randomNum(GRID-1),randomNum(GRID-1),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validLlamaSquare = false;
            }
            
        }   
        while (!validLlamaSquare);  
        
        gridPane.add(llamaPrize1.getLlamaImageView(), llamaPrize1.getLlamaPositionCol(), llamaPrize1.getLlamaPositionRow(), 3, 2);

        /****************************
         * Second llama prize
         ****************************/
        boolean validLlamaSquare2 = false;
        do
        {
            validLlamaSquare2 = true;
            
            try
            {     
                llamaPrize2 = new LlamaPrize(randomNum(GRID-1),randomNum(GRID-1),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validLlamaSquare2 = false;
            }
            
        }   
        while (!validLlamaSquare2); 
        
        gridPane.add(llamaPrize2.getLlamaImageView(), llamaPrize2.getLlamaPositionCol(), llamaPrize2.getLlamaPositionRow(), 3, 2);
        
        /****************************
         * Golden fish prize
         ****************************/
        boolean validGoldenFish = false;
        do
        {
            validGoldenFish = true;
            
            try
            {     
                goldenFishPrize = new GoldenFishPrize(randomNum(GRID),randomNum(GRID),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validGoldenFish = false;
            }
            
        }   
        while (!validGoldenFish); 
        
        gridPane.add(goldenFishPrize.getGoldenFishImageView(), goldenFishPrize.getGoldenFishPositionCol(), goldenFishPrize.getGoldenFishPositionRow(), 1, 1);
        
        
        /******************************
         * Vending machine prize
         ******************************/
        boolean validVendingSquare = false;
        do
        {
            validVendingSquare = true;
            
            try
            {     
                vendingMachinePrize = new VendingMachinePrize(randomNum(GRID-2),randomNum(GRID-1),gridSpace);
            }
            catch(InvalidSquareException ise)
            {
                validVendingSquare = false;
            }
            
        }   
        while (!validVendingSquare);
        
        //Add the vending machine to the grid
        gridPane.add(vendingMachinePrize.getVendingMachineImageView(),
        vendingMachinePrize.getVendingMachinePositionCol(),
        vendingMachinePrize.getVendingMachinePositionRow(),
        2, 3);
        vendingMachinePrize.getVendingMachineImageView().setVisible(false);

        //Center the grid
        gridPane.setAlignment(Pos.CENTER); 

        return gridPane;
    }
    
    
    /***********************************************
     *SQUARE CLICK HANDLER
     *This method handles when a square is clicked 
     * using a lot of if-else statements
     **********************************************/
    
    private void handleSquareClick(int row, int col, Rectangle square) 
    {
        if (turnsLeft > 0) 
        {
            square.setFill(Color.BLACK);
            square.setOpacity(1);
            
            // Play shoot sound effect when a player picks a square
            shootPlayer.stop();
            shootPlayer.play();

            
            // Check if the clicked square is part of any prize
            if (!vbuck1Revealed && vbuckPrize1.checkVbuckHit(row, col)) 
            {
                // Handle V-Buck hit
                vbuck1Revealed = true;
                handleVbuckHit(vbuckPrize1.getVbuckImageView());
                turnsLeft += rollDice();
                updateTurnsLabel();
                square.setFill(Color.RED);
            }
            else if (!vbuck2Revealed && vbuckPrize2.checkVbuckHit(row, col)) {
                // Handle V-Buck hit
                vbuck2Revealed = true;
                handleVbuckHit(vbuckPrize2.getVbuckImageView());
                turnsLeft += rollDice();
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!vbuck3Revealed && vbuckPrize3.checkVbuckHit(row, col)) {
                // Handle V-Buck hit
                vbuck3Revealed = true;
                handleVbuckHit(vbuckPrize3.getVbuckImageView());
                turnsLeft += rollDice();
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!vbuck4Revealed && vbuckPrize4.checkVbuckHit(row, col)) {
                // Handle V-Buck hit
                vbuck4Revealed = true;
                handleVbuckHit(vbuckPrize4.getVbuckImageView());
                turnsLeft += rollDice();
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!vbuck5Revealed && vbuckPrize5.checkVbuckHit(row, col)) 
            {
                // Handle V-Buck hit
                vbuck5Revealed = true;
                handleVbuckHit(vbuckPrize5.getVbuckImageView());
                turnsLeft += rollDice();
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!fishRevealed && isFishSquare(row, col, fishPrize1.getFishPositionCol(), fishPrize1.getFishPositionRow()))
            {
                // Check if the clicked square is part of the first fish
                fishSquaresRemaining--;

                // Play hit sound effect
                hitPlayer.stop();
                hitPlayer.play();

                if (fishSquaresRemaining == 0) 
                {
                    revealFish(fishImageView1);
                    updateScore(275);
                }
                
                updateTurnsLabel();
                square.setFill(Color.RED);
            }  
            else if (!llama1Revealed && llamaPrize1.checkLlamaHit(row, col)) 
            {
                // Check if the clicked square is part of the first llama
                if (llama1SquaresRemaining == 1)
                {
                    hitPlayer.stop();
                    hitPlayer.play();
                    updateScore(500);
                    llamaPrize1.revealLlama();
                    llamaPrize1.getLlamaImageView().setVisible(true);
                    llama1Revealed = true;
                    revealLlama(llamaPrize1);
                }
                else
                {
                    hitPlayer.stop();
                    hitPlayer.play();
                    llama1SquaresRemaining--;
                }
            
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!goldenFishRevealed && goldenFishPrize.checkGoldenFishHit(row, col)) 
            {
                goldenFishRevealed = true;
                handleGoldenFishHit(goldenFishPrize.getGoldenFishImageView());
                turnsLeft += rollDice() + rollDice2();       
                updateTurnsLabel();
                square.setFill(Color.RED);
            }
        
            else if (!llama2Revealed && llamaPrize2.checkLlamaHit(row, col)) 
            {
                // Check if the clicked square is part of the second llama
                if (llama2SquaresRemaining == 1)
                {
                    hitPlayer.stop();
                    hitPlayer.play();
                    updateScore(500);
                    llamaPrize2.getLlamaImageView().setVisible(true);
                    llama2Revealed = true;
                    revealLlama(llamaPrize2);
                }
                else 
                {
                    hitPlayer.stop();
                    hitPlayer.play();
                    llama2SquaresRemaining--;
                }
            
        
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!vendingMachineRevealed && vendingMachinePrize.checkVendingMachineHit(row, col)) 
            {
                // Check if the clicked square is part of the Vending Machine
                if (vendingMachineSquaresRemaining == 1) 
                {
        
                    hitPlayer.stop();
                    hitPlayer.play();
                    vendingMachinePrize.revealVendingMachine();
                    vendingMachinePrize.getVendingMachineImageView().setVisible(true);
                    vendingMachineRevealed = true;
                    updateScore(125);
                } 
                else 
                {
                    vendingMachineSquaresRemaining--;
                    hitPlayer.stop();
                    hitPlayer.play();
                    updateTurnsLabel();
                }
                
                square.setFill(Color.RED);
            }
             else if (!secondFishRevealed && isFishSquare(row, col, fishPrize2.getFishPositionCol(), fishPrize2.getFishPositionRow())) 
             {
                // Check if the clicked square is part of the second fish
                secondFishSquaresRemaining--;

                // Play hit sound effect
                hitPlayer.stop();
                hitPlayer.play();

                if (secondFishSquaresRemaining == 0) 
                {
                    revealFish(fishImageView2);
                    updateScore(275);
                }
                
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!bomb1Revealed && bombPrize1.checkBombHit(row, col)) 
            {
                // Handle Bomb hit
                bomb1Revealed = true;
                handleBombHit(bombPrize1.getBombImageView());
                turnsLeft--;
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!bomb2Revealed && bombPrize2.checkBombHit(row, col)) 
            {
                // Handle Bomb hit
                bomb2Revealed = true;
                handleBombHit(bombPrize2.getBombImageView());
                turnsLeft--;
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!bomb3Revealed && bombPrize3.checkBombHit(row, col)) 
            {
                // Handle Bomb hit
                bomb3Revealed = true;
                handleBombHit(bombPrize3.getBombImageView());
                turnsLeft--;
                updateTurnsLabel();
                square.setFill(Color.RED);
            } else if (!bomb4Revealed && bombPrize4.checkBombHit(row, col)) 
            {
                // Handle Bomb hit
                bomb4Revealed = true;
                handleBombHit(bombPrize4.getBombImageView());
                turnsLeft--;
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else if (!bomb5Revealed && bombPrize5.checkBombHit(row, col)) 
            {
                // Handle Bomb hit
                bomb5Revealed = true;
                handleBombHit(bombPrize5.getBombImageView());
                turnsLeft--;
                updateTurnsLabel();
                square.setFill(Color.RED);
            } 
            else 
            {
                turnsLeft--;
                updateTurnsLabel();
            }
        }
    }
    
    /*******************************************
    * Checks if its a square occupied by a fish
    ********************************************/
    private boolean isFishSquare(int row, int col, int fishCol, int fishRow) 
    {
        return col >= fishCol && col < fishCol + 3 && row == fishRow;
    }
    
    
    /*******************************************
    * Handles v-buck square hit
    ********************************************/
    private void handleVbuckHit(ImageView vbuckImageView) 
    {
        // Play hit sound effect for V-Buck
        hitPlayer.stop();
        hitPlayer.play();

        // Reveal the V-Buck
        vbuckImageView.setVisible(true);

        // Update the score for hitting a V-Buck
        updateScore(100);
    }

    
    /*******************************************
    * Handles golden fish square hit
    ********************************************/
    private void handleGoldenFishHit(ImageView goldenFishImageView) 
    {
        // Play hit sound effect for V-Buck
        winPlayer.stop();
        winPlayer.play();

        // Reveal the V-Buck
        goldenFishImageView.setVisible(true);
    
        // Update the score for hitting a V-Buck
        updateScore(1000);
    }   
    
    /*******************************************
    * Handles bomb hit
    ********************************************/
    private void handleBombHit(ImageView bombImageView) 
    {
        // Play hit sound effect for bomb
        shockwavePlayer.stop();
        shockwavePlayer.play();

        // Reveal the bomb
        bombImageView.setVisible(true);

        // Update the score for hitting a bomb
        if (score > 0 )
        {
            updateScore(-100);    
        }
    
        
    }

    /********************************************
    * Reveal fish method reveals the fish to user
    *********************************************/
    private void revealFish(ImageView fishImageView) 
    {
        fishImageView.setVisible(true);

        if (fishImageView == fishImageView1) 
        {
            fishRevealed = true;
        } 
        else if (fishImageView == fishImageView2) 
        {
            secondFishRevealed = true;
        }
    }

    
    /*************************************************
    * Adds the points in the parameter to user's score
    **************************************************/
    public void updateScore(int points) 
    {
        score += points;
        scoreLabel.setText("Score: " + score);
    }

    
    /*******************************************************
    * Updates the turns label to show the correct turns left
    ********************************************************/
    private void updateTurnsLabel() 
    {
        turnsLabel.setText("Turns Left: " + turnsLeft);
    }

    
    /*******************************************
    * Disables grid to not allow clicking
    ********************************************/
    private void disableGrid() 
    {
        for (javafx.scene.Node node : gridPane.getChildren()) 
        {
            if (node instanceof Rectangle) 
            {
                node.setDisable(true);
            }
        }
    }
    
    /*******************************************
    * Reveals every prize
    ********************************************/
    private void revealAllPrizes() 
    {
        
        //Check if fish is revealed and if notreveal
        if (!fishRevealed) 
        {
            revealFish(fishImageView1);
        }

        if (!secondFishRevealed) 
        {
            revealFish(fishImageView2);
        }

        // Check and reveal V-Buck
        if (!vbuckPrize1.isVbuckRevealed()) 
        {
            vbuckPrize1.getVbuckImageView().setVisible(true);
            vbuck1Revealed = true;
        }
    
        if (!vbuckPrize2.isVbuckRevealed()) 
        {
            vbuckPrize2.getVbuckImageView().setVisible(true);
            vbuck2Revealed = true;
        }
    
        if (!vbuckPrize3.isVbuckRevealed()) 
        {
            vbuckPrize3.getVbuckImageView().setVisible(true);
            vbuck3Revealed = true;
        }
    
        if (!vbuckPrize4.isVbuckRevealed()) 
        {
            vbuckPrize4.getVbuckImageView().setVisible(true);
            vbuck4Revealed = true;
        }
    
        if (!vbuckPrize5.isVbuckRevealed()) 
        {
            vbuckPrize5.getVbuckImageView().setVisible(true);
            bomb4Revealed = true;
        }
    
        
        //Checks if bombs are revealed and if not reveals bombs
        if (!bombPrize1.isBombRevealed()) 
        {
            bombPrize1.getBombImageView().setVisible(true);
            bomb1Revealed = true;
        }
    
        if (!bombPrize2.isBombRevealed()) 
        {
            bombPrize2.getBombImageView().setVisible(true);
            bomb2Revealed = true;
        }
    
        if (!bombPrize3.isBombRevealed()) 
        {
            bombPrize3.getBombImageView().setVisible(true);
            bomb3Revealed = true;
        }
    
        if (!bombPrize4.isBombRevealed()) 
        {
            bombPrize4.getBombImageView().setVisible(true);
            bomb4Revealed = true;
        }
    
        if (!bombPrize5.isBombRevealed()) 
        {
            bombPrize5.getBombImageView().setVisible(true);
            bomb5Revealed = true;
        }
    
        // Check and reveal Llamas
        if (!llama1Revealed) 
        {
            revealLlama(llamaPrize1);
        }

        if (!llama2Revealed) 
        {
            revealLlama(llamaPrize2);
        }

        //Check golden fish and reveal
        if (!goldenFishRevealed)
        {
            goldenFishPrize.getGoldenFishImageView().setVisible(true);
            goldenFishRevealed = true;
        }

        //Check vending machine and reveal
        if (!vendingMachineRevealed) 
        {
            vendingMachinePrize.getVendingMachineImageView().setVisible(true);
            vendingMachineRevealed = true;
        }
    }

    /*******************************************
    * Reveals the llama on the grid
    ********************************************/
    private void revealLlama(LlamaPrize llamaPrize) 
    {
        llamaPrize.getLlamaImageView().setVisible(true);

        if (llamaPrize == llamaPrize1) 
        {
            llama1Revealed = true;
        } 
        else if (llamaPrize == llamaPrize2) 
        {
            llama2Revealed = true;
        }
    }

    /**********************************************************************
    * Random number generator method to specify which rows and columns the
    * item can go
    ***********************************************************************/
    private int randomNum(int number)
    {
        gen = new Random();
        return gen.nextInt(number) + 1;
    }
    
    /*********************************************************************
     * Clears the message from the left of the screen
     ********************************************************************/
     private void clearMessage(BorderPane root)
     {
         root.setLeft(null);
     }
    
     /********************************************************************
      * Dice roll methods with animations! very cool 
      *******************************************************************/
    private int rollDice() 
    {
        Random gen = new Random();
        int n = gen.nextInt(6) + 1;
        ImageView tempOne = new ImageView();
        tempOne.setImage(one.getImage());
        ImageView[] rolledNum = {tempOne, two, three, four, five, six};
           
        Timeline timeline = new Timeline();
    
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(0), event -> 
            {
                one.setImage(two.getImage());
            }),
            new KeyFrame(Duration.seconds(0.5), event -> 
            {
                one.setImage(three.getImage());       
            }),
            new KeyFrame(Duration.seconds(1), event -> 
            {
                one.setImage(four.getImage());        
            }),
            new KeyFrame(Duration.seconds(1.5), event -> 
            {
                one.setImage(five.getImage());        
            }),
            new KeyFrame(Duration.seconds(2), event -> 
            {
                one.setImage(six.getImage());        
            }),
            new KeyFrame(Duration.seconds(2.5), event -> 
            {
                one.setImage(four.getImage());            
            }), 
            new KeyFrame(Duration.seconds(3), event -> 
            {
                one.setImage(rolledNum[n - 1].getImage());
                updateTurnsLabel();
            }));   
    
        // Start the animation
        timeline.play();
        
        return n;
    }

    private int rollDice2() 
    {
        ImageView tempOne = new ImageView();
        tempOne.setImage(one2.getImage());
        ImageView[] rolledNum = {tempOne, two2, three2, four2, five2, six2};
        
        Random gen = new Random();
        int n = gen.nextInt(6) + 1;
        Timeline timeline = new Timeline();
    
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.seconds(0), event -> 
            {
                one2.setImage(four.getImage());
            }),
            new KeyFrame(Duration.seconds(0.5), event -> 
            {
                one2.setImage(three.getImage());       
            }),
            new KeyFrame(Duration.seconds(1), event -> 
            {
                one2.setImage(one.getImage());        
            }),
            new KeyFrame(Duration.seconds(1.5), event -> 
            {
                one2.setImage(six.getImage());        
            }),
            new KeyFrame(Duration.seconds(2), event -> 
            {
                one2.setImage(two.getImage());        
            }),
            new KeyFrame(Duration.seconds(2.5), event -> 
            {
                one2.setImage(five.getImage());            
            }), 
            new KeyFrame(Duration.seconds(3), event -> 
            {
                one2.setImage(rolledNum[n - 1].getImage()); 
                updateTurnsLabel();
            }));   
    
        // Start the animation
        timeline.play();
        
        return n;
    }
}
