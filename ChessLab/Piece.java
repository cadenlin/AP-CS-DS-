import java.awt.*;
import java.util.*;

/** The Piece class creates a blueprint for chess pieces 
 * @author Caden Lin 
 * @version May 21, 2021 
 * 
 */
public abstract class Piece
{
    //the board this piece is on
    private Board board;
    //the location of this piece on the board
    private Location location;
    //the color of the piece
    private Color color;
    //the file used to display this piece
    private String imageFileName;
    //the approximate value of this piece in a game of chess
    private int value;
    //constructs a new Piece with the given attributes.
    /** Constructs a new Piece with the given attributes
     * @param col the color of the piece 
     * @param fileName the name of the file with the image of the piece 
     * @param val the value of the piece 
     * 
     */
    public Piece(Color col, String fileName, int val)
    {
        color = col;
        imageFileName = fileName;
        value = val;
    }

    /** Gets the board this piece is on 
     * @return the board the piece is on 
     * 
     */
    public Board getBoard()
    {
        return board;
    }

    /** Gets the location of the piece 
     * @return the location of the piece on the board 
     * 
     */
    public Location getLocation()
    {
        return location;
    }

    /** Gets the color of the piece
     * @return the color of the piece 
     */
    public Color getColor()
    {
        return color;
    }

    /** Gets the name of the file used to display the piece 
     * @return a string with the name of the file used to display this piece 
     * 
     */
    public String getImageFileName()
    {
        return imageFileName;
    }

    /** Gets the value of the piece 
     * @return the value of the piece 
     * 
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed. <br />
     * Precondition: (1) This piece is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. <br />
     * Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                "The board contains a different piece at location "
                + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed. <br />
     * Precondition: (1) This piece is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this piece
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                "The board contains a different piece at location "
                + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }

    /** Determines if the given location is a valid destination for the piece 
     * @param dest the destination to chek 
     * @return true if the piece can move to destination, false otherwise 
     * 
     */
    public boolean isValidDestination(Location dest) 
    {
        if(! (board.isValid(dest)) ) 
            return false ; 
        if (board.get(dest) == null || ! (board.get(dest).getColor().equals(getColor()))) 
            return true ; 
        return false ; 
    }

    /** Determines the valid destinations of the piece 
     * @return an arraylist with the valid destinations of the piece 
     *          (all the locations the piece can move to) 
     * 
     */
    public abstract ArrayList<Location> destinations() ;

    /** Takes in an arraylist of locations and adds all the 
     * valid destinations of this piece in a given direction to this arraylist 
     * @param dests the arraylist to add the locations to 
     * @param direction an integer that species the direction to move in 
     * @postcondition the arraylist has been filled with valid destinations in the given direction
     * 
     */
    public void sweep(ArrayList<Location> dests,int direction)
    {
        Location loc = getLocation().getAdjacentLocation(direction) ; 
        while(board.isValid(loc) && board.get(loc)==null)
        {
            dests.add(loc) ; 
            loc = loc.getAdjacentLocation(direction) ; 
        }
        if(isValidDestination(loc))
            dests.add(loc) ; 
    }

}