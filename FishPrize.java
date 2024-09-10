import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;


/******************************************
 * fish prize. 
 * Uses 3 rows and 1 column
 * 
 * 
 * @author Adham Tawfik
 * @version 1.0
 *****************************************/
public class FishPrize 
{
    private ImageView fishImageView;
    private int fishPositionCol;
    private int fishPositionRow;
    private boolean fishRevealed = false;
    private int fishSquaresRemaining = 3;

    public FishPrize(int row, int col, boolean[][] grid) throws InvalidSquareException
    {
        //Checks if all the squares are available if not it throws exception
        if (grid[row][col] == false && grid[row][col+1] == false && grid[row][col+2] == false)
        {
            fishPositionRow = row;
            fishPositionCol= col;
            
            grid[row][col] = true;
            grid[row][col+1] = true;
            grid[row][col+2] = true;
            
        }
        else
        {
            throw new InvalidSquareException();
        }
        
        
        fishImageView = new ImageView(new Image("fish.png"));
        fishImageView.setFitWidth(180); 
        fishImageView.setFitHeight(40); 
        fishImageView.setVisible(false);
    }
    
    //Methods
    public boolean checkFishHit(int row, int col) 
    {
        return (col == fishPositionCol && row == fishPositionRow) ||
               (col == fishPositionCol + 1 && row == fishPositionRow) ||
               (col == fishPositionCol + 2 && row == fishPositionRow);
    }
    
    public boolean isFishRevealed() 
    {
        return fishRevealed;
    }
    
    public int getFishPositionRow()
    {
        return fishPositionRow;
    }
    
    public int getFishPositionCol()
    {
        return fishPositionCol;
    }

    public void revealFish() 
    {
        fishImageView.setVisible(true);
        fishRevealed = true;
    }

    public int getFishSquaresRemaining() 
    {
        return fishSquaresRemaining;
    }

    public void decrementFishSquares() 
    {
        fishSquaresRemaining--;

    }
}
