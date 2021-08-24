import java.awt.* ; 
import java.util.* ; 
/**
 * The Rook class simulates the rook chess piece.
 *
 * @author (Caden Lin 
 * @version May 22 2021 
 */
public class Rook extends Piece 
{

    /**
     * Constructor for objects of class Rook
     * @param col the color of the rook 
     * @param fileName the name of the file with the image of the rook 
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 5) ;
    }

    /**
     * Determines the possible destinations of the arraylist 
     *
     * @return  an arraylist with possible destinations 
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dest = new ArrayList<Location>() ; 
        sweep(dest, 0) ; 
        sweep(dest, 90) ; 
        sweep(dest, 180) ; 
        sweep(dest, 270) ; 
        return dest ; 
    }
}
