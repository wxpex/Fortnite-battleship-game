import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

/******************************************
 * v-buck prize. 
 * Uses 1 row and 1 column
 * 
 * 
 * @author Adham Tawfik
 * @version 1.0
 *****************************************/
public class VbuckPrize 
{

    private ImageView vbuckImageView;
    private boolean vbuckRevealed = false;
    private int vbuckPositionCol;
    private int vbuckPositionRow;

    public VbuckPrize(int row, int col, boolean[][] grid) throws InvalidSquareException
    {
        //Checks if all the squares are available if not it throws exception
        if (grid[row][col] == false)
        {
            vbuckPositionRow = row ;
            vbuckPositionCol = col;
            
            grid[row][col] = true;
        }
        else
        {
            throw new InvalidSquareException();
        }
        
        
        
        vbuckImageView = new ImageView(new Image("vbuck.png"));
        vbuckImageView.setFitWidth(60); // 1 column
        vbuckImageView.setFitHeight(40); // 1 row
        vbuckImageView.setVisible(false);
    }
    
    public ImageView getVbuckImageView()
    {
        return vbuckImageView;
    } 

    public boolean isVbuckRevealed() 
    {
        return vbuckRevealed;
    }

    public int getVbuckPositionCol() 
    {
        return vbuckPositionCol;
    }

    public int getVbuckPositionRow() 
    {
        return vbuckPositionRow;
    }

    public boolean checkVbuckHit(int row, int col) 
    {
        if (!vbuckRevealed && isVbuckSquare(row, col, vbuckPositionCol, vbuckPositionRow)) 
        {
            vbuckRevealed = true;
            return true; // V-Buck hit
        }
        return false; // Not a V-Buck hit
    }

    private boolean isVbuckSquare(int row, int col, int vbuckCol, int vbuckRow) 
    {
        return col == vbuckCol && row == vbuckRow;
    }
}

