import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

/******************************************
 * bomb prize. 
 * Uses 1 row and 1 column
 * 
 * 
 * @author Adham Tawfik
 * @version 1.0
 *****************************************/
public class BombPrize 
{

    private ImageView bombImageView;
    private boolean bombRevealed = false;
    private int bombPositionCol;
    private int bombPositionRow;

    public BombPrize(int row, int col, boolean[][] grid) throws InvalidSquareException
    {
        //Checks if all the squares are available if not it throws exception
        if (grid[row][col] == false)
        {
            bombPositionRow = row;
            bombPositionCol = col;
            
            grid[row][col] = true;
        }
        else
        {
            throw new InvalidSquareException();
        }
                
        bombImageView = new ImageView(new Image("bomb.png"));
        bombImageView.setFitWidth(60); // 1 column
        bombImageView.setFitHeight(40); // 1 row
        bombImageView.setVisible(false);
    }

    public ImageView getBombImageView() 
    {
        return bombImageView;
    }

    public boolean isBombRevealed() 
    {
        return bombRevealed;
    }

    //
    public boolean checkBombHit(int row, int col) 
    {
        if (!bombRevealed && bombPositionRow == row && bombPositionCol == col) {
            bombRevealed = true;
            return true; // Bomb hit
        }
        return false; // Bomb not hit
    }

    //Methods
    public int getBombPositionCol() 
    {
        return bombPositionCol;
    }

    public int getBombPositionRow() 
    {
        return bombPositionRow;
    }
}

