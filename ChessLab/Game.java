import java.util.* ; 
import java.awt.Color ; 
/**
 * The Game class simulates a game of chess. 
 *
 * @author Caden Lin
 * @version May 21, 2021 
 */
public class Game
{
    private static boolean over ; //whether the game is over or not 

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        over = false ; 
    }

    /**
     * The main method - creates the chess game
     *
     * @param  args arguments from the command line 
     */
    public static void main(String[] args)
    {
        Board board = new Board();
        Piece blackKing = new King(Color.BLACK,
                "/Users/cadenlin/Desktop/chessfiles/black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        Piece whiteKing = new King(Color.WHITE,
                "/Users/cadenlin/Desktop/chessfiles/white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));
        Piece blackRook1 = new Rook(Color.BLACK, 
                "/Users/cadenlin/Desktop/chessfiles/black_rook.gif") ; 
        blackRook1.putSelfInGrid(board, new Location(0,0)) ; 
        Piece blackRook2 = new Rook(Color.BLACK, 
                "/Users/cadenlin/Desktop/chessfiles/black_rook.gif") ; 
        blackRook2.putSelfInGrid(board, new Location(0,7)) ; 
        Piece whiteRook1 = new Rook(Color.WHITE, 
                "/Users/cadenlin/Desktop/chessfiles/white_rook.gif") ; 
        whiteRook1.putSelfInGrid(board, new Location(7,0)) ; 
        Piece whiteRook2 = new Rook(Color.WHITE, 
                "/Users/cadenlin/Desktop/chessfiles/white_rook.gif") ; 
        whiteRook2.putSelfInGrid(board, new Location(7,7)) ; 
        BoardDisplay display = new BoardDisplay(board);

        Player white = new HumanPlayer(display, board, "White", Color.WHITE) ; 
        Player black = new RandomPlayer(board, "Black", Color.BLACK) ; 
        play(board, display, white, black) ; 
    }

    /** Moves a player to their next turn 
     * @postcondition the specified player has performed a turn 
     * @param board the board being used 
     * @param display a board display for the game 
     * @param player the player who is making a turn 
     * 
     */
    private static void nextTurn(Board board, BoardDisplay display, Player player)
    {
        display.setTitle(player.getName()) ; 
        Move move = player.nextMove() ; 
        if(move==null)
        {
            over = true ; 
            display.setTitle("draw :|") ; 
            return ; 
        }
        board.executeMove(move) ; 
        if(move.getVictim() instanceof King)
        {
            over = true ; 
            String col = "" ; 
            if(player.getColor().equals(Color.BLACK))
                col = "Black" ; 
            else
                col = "White" ; 
            display.setTitle( col + " wins! :) ") ; 
        }
        else
        {
            display.clearColors() ; 
            display.setColor(move.getSource(), Color.PINK) ; 
            display.setColor(move.getDestination(), Color.GREEN) ; 
            try 
            {
                Thread.sleep(500);
            } 
            catch(InterruptedException e) 
            {
                //making the checkstyle happy 
            }
        }
    }

    /** Plays a game of chess 
     * @postcondition a game of chess has been played 
     * @param board the board being used 
     * @param display a display for the game 
     * @param white the white player 
     * @param black the black player 
     * 
     */
    public static void play(Board board, BoardDisplay display,Player white, Player black)
    {
        while(true)
        {
            if(!over) 
                nextTurn(board, display, white) ; 
            if(!over) 
                nextTurn(board, display, black) ; 
        }
    }

}
