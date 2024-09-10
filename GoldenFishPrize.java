import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

/******************************************
 * Golden fish prize. 
 * Uses 1 row and 1 column
 * 
 * 
 * @author Adham Tawfik
 * @version 1.0
 *****************************************/
public class GoldenFishPrize 
{

    private ImageView goldenFishImageView;
    private boolean goldenFishRevealed = false;
    private int goldenFishPositionCol;
    private int goldenFishPositionRow;

    public GoldenFishPrize(int row, int col, boolean[][] grid) throws InvalidSquareException 
    {
        //Checks if all the squares are available if not it throws exception
        if (grid[row][col] == false)
        {
            goldenFishPositionRow = row;
            goldenFishPositionCol = col;
            
            grid[row][col] = true;
        }
        else
        {
            throw new InvalidSquareException();
        }
        
        
        goldenFishImageView = new ImageView(new Image("goldenfish.png"));
        goldenFishImageView.setFitWidth(60); // 1 column
        goldenFishImageView.setFitHeight(40); // 1 row
        goldenFishImageView.setVisible(false);
    }

    public ImageView getGoldenFishImageView() 
    {
        return goldenFishImageView;
    }

    public boolean isGoldenFishRevealed() {
        return goldenFishRevealed;
    }

    public int getGoldenFishPositionCol() {
        return goldenFishPositionCol;
    }

    public int getGoldenFishPositionRow() {
        return goldenFishPositionRow;
    }

    public boolean checkGoldenFishHit(int row, int col) 
    {
        if (!goldenFishRevealed && isGoldenFishSquare(row, col, goldenFishPositionCol, goldenFishPositionRow)) {
            goldenFishRevealed = true;
            return true; // V-Buck hit
        }
        return false; // Not a V-Buck hit
    }

    private boolean isGoldenFishSquare(int row, int col, int vbuckCol, int goldenFishPositionRow) {
        return col == goldenFishPositionCol && row == goldenFishPositionRow;
    }
}
