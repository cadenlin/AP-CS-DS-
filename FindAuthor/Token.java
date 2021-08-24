
/**
 * A Token is either a word, digit, end of phrase character, 
 *  end of sentence character, special character, or end of file. 
 *
 * @author Caden Lin 
 * @version May 21, 2021 
 */
public final class Token
{
    private Scanner.TOKEN_TYPE type;
    private String token; 

    /**
     * Constructor for objects of class Token
     * @param scan the type of the token 
     * @param val a string form of the token 
     */
    public Token(Scanner.TOKEN_TYPE scan, String val)
    {
        type = scan; 
        token = val; 
    }

    /**
     * Converts the Token into a string 
     *
     * @return a string representation of the token 
     */
    public String toString()
    {
        return "[" + type + "," + token + "]" ;
    }
    
    /** Gets the type of the token 
     * @return the type of the token 
     * 
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }
    
    /** Gets the value of the token 
     * @return a string of the contents of the token 
     * 
     */
    public String getToken()
    {
        return token;
    }
    
    /** Determines if two tokens are equal by comparing 
     *  their types and values 
     *  @param t the other token to compare to 
     *  @return true if the tokens are equal, false otherwise 
     * 
     */
    public boolean equals(Object t)
    {
        return ((Token) t).getType().equals(getType()) 
                && ((Token) t).getToken().equals(getToken()) ; 
    }
}
