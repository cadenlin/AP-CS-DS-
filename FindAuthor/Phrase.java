import java.util.ArrayList;
/**
 * The Phrase class creates phrases that consist of tokens. 
 *
 * @author Caden Lin 
 * @version March 26 2021 
 */
public class Phrase
{
    // instance variables - replace the example below with your own
    private ArrayList<Token> phrase; 

    /**
     * Constructor for objects of class Phrase
     */
    public Phrase()
    {
        phrase = new ArrayList<Token>(); 
    }

    /**
     * Adds a token to the phrase 
     *
     * @param  t the token to add to the phrase
     * @postcondition the token t is added to the phrase 
     */
    public void addToken(Token t)
    {
        phrase.add(t) ; 
    }

    /** Gets the internal data structure holding the tokens 
     * @return the ArrayList holding the tokens of the phrase 
     * 
     */
    public ArrayList<Token> getPhrase()
    {
        ArrayList<Token> newPhrase = new ArrayList<Token>();
        for(Token t : phrase)
            newPhrase.add(t) ; 
        return newPhrase ; 
    }

    /** Returns the phrase as a string representation 
     * @return a string with the tokens of the phrase, separated by commas 
     * 
     */ 
    public String toString()
    {
        String str = ""; 
        for(int i = 0 ; i < phrase.size() ; i++)
            str+=phrase.get(i).toString() ; 
        return str;
    }
}
