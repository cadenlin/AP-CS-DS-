import java.awt.* ; 
import java.util.* ; 
/**
 * A RandomPlayer randomly plays chess by randomly making valid moves 
 *
 *
 * @author Caden Lin
 * @version May 22, 2021 
 */
public class RandomPlayer extends Player 
{

    /**
     * Constructor for objects of class RandomPlayer
     * @param b the board to use 
     * @param n the name of the player 
     * @param c the color of the player's pieces
     */
    public RandomPlayer(Board b, String n, Color c)
    {
        super(b,n,c) ; 
    }

    /**
     * Determines the next Move of the player 
     *
     * @return  the next Move of the player 
     */
    public Move nextMove()
    {
        
        ArrayList<Move> moves = getBoard().allMoves(getColor()) ; 
        int ran = (int) (Math.random() * moves.size()) ; 
        return moves.get(ran) ; 
    }
}
