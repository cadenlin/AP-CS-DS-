import java.awt.* ; 
import java.util.* ; 
/**
 * The HumanPlayer class allows a human to play a game of chess.
 * 
 *
 * @author Caden Lin
 * @version May 21, 2021 
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display; 

    /**
     * Constructor for objects of class HumanPlayer
     * @param d the board display 
     * @param b the board being used 
     * @param n the name of the player 
     * @param c the color of the player's pieces 
     */
    public HumanPlayer(BoardDisplay d, Board b, String n, Color c)
    {
        super(b,n,c) ; 
        display = d ; 
    }

    /**
     * Finds the next move of the player by allowing 
     * the user to select a possible move 
     *
     * @return  the next move of the player 
     */
    public Move nextMove()
    {
        Move move = display.selectMove() ; 
        ArrayList<Move> moves = getBoard().allMoves(getColor()) ; 
        while(!moves.contains(move))
        {
            display.clearColors() ; 
            move = display.selectMove() ; 
        }
        return move ; 
    }
}
