import java.awt.*;
import java.util.*;
/** 
 * A Board represents a rectangular game board, containing Piece objects.
 * 
 *  @author Caden Lin 
 *  @version May 21, 2021 
 */
public class Board extends BoundedGrid<Piece>
{
    // Constructs a new Board with the given dimensions
    /** Constructs a new 8x8 board. 
     * 
     */
    public Board()
    {
        super(8, 8);
    }

    /** Undoes a move by moving the piece back to its source 
     *  @precondition the move has already been made on the board
     *  @postcondition the piece has moved back to its source,
     *          and any captured piece is returned to its location
     *   @param move the move that is to be undone 
     * 
     */
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();

        piece.moveTo(source);

        if (victim != null)
            victim.putSelfInGrid(piece.getBoard(), dest);
    }

    /** Determines all the possible moves that can be made with pieces of a given color 
     * @param color the piece color to find the possible moves of 
     * @return an arraylist with the possible Moves that can be made 
     * 
     * 
     */
    public ArrayList<Move> allMoves(Color color)
    {
        ArrayList<Move> move = new ArrayList<Move>();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
            {
                Piece p = get(new Location(i,j));
                if (p != null && p.getColor().equals(color))
                    for (Location loc : p.destinations())
                        move.add(new Move(p,loc));
            }
        return move;
    }

    /** Executes a move by moving a piece from a source to its destination 
     * @postcondition the specified piece has been moved to the specified destination 
     * @param move the move to be executed 
     * 
     *
     */
    public void executeMove(Move move)
    {
        move.getPiece().moveTo(move.getDestination()) ; 
    }

}