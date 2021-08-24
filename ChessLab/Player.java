import java.awt.* ; 
import java.util.* ; 
/**
 * The Player class provides a blueprint for a chess player. 
 *
 * @author Caden Lin 
 * @version May 22, 2021 
 */
public abstract class Player
{
    // instance variables - replace the example below with your own
    private Board board ; 
    private String name ; 
    private Color color ; 

    /**
     * Constructor for objects of class Player
     * @param b the board to use 
     * @param n the name of the player 
     * @param c the color of the player's pieces 
     */
    public Player(Board b, String n, Color c)
    {
        board = b ; 
        name = n ; 
        color = c; 
    }

    /**
     * A blueprint to get the player's next move 
     *
     * @return    the next move
     */
    public abstract Move nextMove() ; 

    /**
     * Gets the board 
     * @return the board
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Gets the player name 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the player color 
     * @return the color
     */
    public Color getColor()
    {
        return color;
    }

}
