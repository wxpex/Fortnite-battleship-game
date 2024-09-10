import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
/******************************************
 * Vending machine prize. 
 * Uses 3 rows and 2 columns
 * 
 * 
 * @author Adham Tawfik
 * @version 1.0
 *****************************************/
public class VendingMachinePrize
 {
    //Instance Data
    private ImageView vendingMachineImageView;
    private boolean vendingMachineRevealed = false;
    private int vendingMachineSquaresRemaining = 6;

    private int vendingMachinePositionCol;
    private int vendingMachinePositionRow;

    
    public VendingMachinePrize(int row, int col, boolean[][] grid) throws InvalidSquareException 
    {
        //If all the squares area available it places the machine if not it throws exception
        if (grid[row][col] == false && grid[row+1][col] == false &&
                grid[row+2][col] == false && grid[row][col+1] == false &&
                grid[row+1][col+1] == false && grid[row+2][col+1] == false)
        {
            vendingMachinePositionRow = row;
            vendingMachinePositionCol = col;
            
            grid[row][col] = true;
            grid[row+1][col] = true;
            grid[row+2][col] = true;
            grid[row][col+1] = true;
            grid[row+1][col+1] = true;
            grid[row+2][col+1] = true;
        }
        else
        {
            throw new InvalidSquareException();
        }
                
        vendingMachineImageView = new ImageView(new Image("vendingmachine.png"));
        vendingMachineImageView.setFitWidth(125); // 2 columns
        vendingMachineImageView.setFitHeight(120); // 3 rows
    }

    //Methods
    public ImageView getVendingMachineImageView()
    {
        return vendingMachineImageView;
    }

    public int getVendingMachinePositionCol() 
    {
        return vendingMachinePositionCol;
    }

    public int getVendingMachinePositionRow()
    {
        return vendingMachinePositionRow;
    }

    public boolean checkVendingMachineHit(int row, int col) 
    {
        return row >= vendingMachinePositionRow && row < vendingMachinePositionRow + 3 &&
               col >= vendingMachinePositionCol && col < vendingMachinePositionCol + 2;
               
    }

    public boolean isVendingMachineRevealed() 
    {
        return vendingMachineRevealed;
    }

    public void revealVendingMachine() 
    {
        vendingMachineImageView.setVisible(true);
        vendingMachineRevealed = true;
    }

    public int getVendingMachineSquaresRemaining() 
    {
        return vendingMachineSquaresRemaining;
    }

    public void decrementVendingMachineSquaresRemaining() 
    {
        vendingMachineSquaresRemaining--;
    }
}

