import java.awt.* ; 
import java.util.* ; 
/**
 * The King class simulates the king chess piece. 
 *
 * @author Caden Lin 
 * @version May 21, 2021 
 */
public class King extends Piece 
{

    /** 
     * Constructor for objects of class King
     * @param col the color of the king 
     * @param fileName the name of the file with the image of the king 
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 100) ;
    }

    /** Finds all possible destinations of the king 
     * @return an arraylist of locations with the valid 
     *          destinations that the king could move to 
     * 
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dest = new ArrayList<Location>() ; 
        for(int i = getLocation().getRow() - 1 ; i <= getLocation().getRow()+1 ; i++)
            for(int j = getLocation().getCol() - 1 ; j<= getLocation().getCol()+1 ; j++)
                if( !(i==getLocation().getRow() && j == getLocation().getCol()) 
                        && isValidDestination(new Location(i,j)))
                    dest.add(new Location (i,j)) ;   
        return dest; 
    }
}
