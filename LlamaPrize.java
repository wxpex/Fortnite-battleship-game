import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

/******************************************
 * Llama prize. 
 * Uses 2 rows and 2 columns
 * but the 1st row column 2 isnt used
 * 
 * @author Adham Tawfik
 * @version 1.0
 *****************************************/

public class LlamaPrize 
{
    private ImageView llamaImageView;
    private int llamaPositionCol;
    private int llamaPositionRow;
    private boolean llamaRevealed = false;
    private int llamaSquaresRemaining = 3;

    public LlamaPrize(int row, int col, boolean[][] grid) throws InvalidSquareException 
    {
        //Checks if all the squares are available if not it throws exception
        if (grid[row][col] == false && grid[row+1][col] == false && grid[row+1][col+1] == false)
        {
            llamaPositionRow = row;
            llamaPositionCol = col;
            
            grid[row][col] = true;
            grid[row+1][col] = true;
            grid[row+1][col+1] = true;
        }
        else
        {
            throw new InvalidSquareException();
        }
        
        
        llamaImageView = new ImageView(new Image("llama.png"));
        llamaImageView.setFitWidth(120); // 2 columns
        llamaImageView.setFitHeight(80); // 2 rows
        llamaImageView.setVisible(false);
    }

    public boolean checkLlamaHit(int row, int col) {
        return (col == llamaPositionCol && row == llamaPositionRow) ||
               (col == llamaPositionCol && row == llamaPositionRow + 1) ||
               (col == llamaPositionCol + 1 && row == llamaPositionRow + 1);
    }

    public ImageView getLlamaImageView()
    {
        return llamaImageView;
    }

    public int getLlamaPositionCol() 
    {
        return llamaPositionCol;
    }

    public int getLlamaPositionRow() 
    {
        return llamaPositionRow;
    }

    public boolean isLlamaRevealed() 
    {
        return llamaRevealed;
    }

    public void revealLlama() 
    {
        llamaImageView.setVisible(true);
        llamaRevealed = true;
    }

    public int getLlamaSquaresRemaining() 
    {
        return llamaSquaresRemaining;
    }

    public void decrementLlamaSquaresRemaining() 
    {
        llamaSquaresRemaining--;

    }
}


