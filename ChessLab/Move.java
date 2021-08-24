// Represents a single move, in which a piece moves to a destination location.
// Since a move can be undone, also keeps track of the source location and any captured victim.
import java.awt.* ; 
import java.util.* ; 
/** The Move class represents a move with a specified piece 
 * from its source to a destination. 
 * @author Caden Lin 
 * @version May 22, 2021 
 * 
 */
public class Move
{
    private Piece piece;          //the piece being moved
    private Location source;      //the location being moved from
    private Location destination; //the location being moved to
    private Piece victim;         //any captured piece at the destination

    /** Constructor for Move objects 
     * @param piece the piece being moved 
     * @param destination the destination of the object 
     * 
     */
    public Move(Piece piece, Location destination)
    {
        this.piece = piece;
        this.source = piece.getLocation();
        this.destination = destination;
        this.victim = piece.getBoard().get(destination);

        if (source.equals(destination))
            throw new IllegalArgumentException("Both source and dest are " + source);
    }

    /** Gets the piece being moved 
     * @return the piece being moved 
     * 
     */
    public Piece getPiece()
    {
        return piece;
    }

    /** Gets the source of the piece 
     * @return the source of the piece 
     * 
     */
    public Location getSource()
    {
        return source;
    }

    /** Gets the destination of the piece 
     * @return the destination of the piece 
     * 
     */
    public Location getDestination()
    {
        return destination;
    }

    /**  Gets the victim of the piece if there is one 
     * @return the victim, if any 
     * 
     */
    public Piece getVictim()
    {
        return victim;
    }
    
    /** Gets a string description of the move 
     * @return a string description of the move 
     * 
     */
    public String toString()
    {
        return piece + " from " + source + " to " + destination + " containing " + victim;
    }

    /** Determines if two moves are equal 
     * @param x the other Move to compare this move to 
     * @return true if the moves are equal 
     * 
     */
    public boolean equals(Object x)
    {
        Move other = (Move)x;
        return piece == other.getPiece() && source.equals(other.getSource()) &&
        destination.equals(other.getDestination()) && victim == other.getVictim();
    }

    /** Gets a hash code for this move 
     * Two equivalent moves have the same hash code
     *  @return a hash code for this move 
     */
    public int hashCode()
    {
        return piece.hashCode() + source.hashCode() + destination.hashCode();
    }

}