import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 * @author Caden Lin 
 * @version March 26 2021 
 *
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;
    // define symbolic constants for each type of token
    /** Enum that represents the possible token types 
     * 
     */
    public static enum TOKEN_TYPE
    {
        /**
         * a word, consisting of lowercase or uppercase letters 
         */
        WORD, 
        
        /**
         *  an end of sentence puncutation (.!?) 
         */
        END_OF_SENTENCE,
        /**
         * special character 
         */
         END_OF_FILE, 
         
         /**
          *  an end of phrase puncuation (,;:)
          */
        END_OF_PHRASE,
        /**
         * a digit in a number 
         */
        DIGIT, 
        /**
         *  anything else 
         */
        UNKNOWN
    } ;
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * The eat method compares a String to the current char and advances 
     * in the text if the two are equal. 
     * @param str the string to compare to the current char 
     * @postcondition the program moves on to the next char if the strings are equal
     *                  otherwise, an error is thrown 
     */
    private void eat(String str)
    {
        if (str.equals(currentChar))
            getNextChar();
        else
            throw new IllegalArgumentException(); 
    }

    /** Determines if a string is a letter 
     * @param str the string to evaluate 
     * @return true if the string is a letter, false otherwise 
     * 
     */
    private boolean isLetter(String str)
    {
        return (str.toLowerCase().compareTo("z") <= 0 && str.toLowerCase().compareTo("a") >= 0) ; 
    }

    /** Determines if a string is a digit 
     * @param str the string to evaluate 
     * @return true if the string is a digit, false otherwise 
     * 
     */
    private boolean isDigit(String str) 
    {
        return (str.toLowerCase().compareTo("9") <= 0 && str.toLowerCase().compareTo("0") >= 0) ;
    }

    /** Determines if a string is a special character  
     * A special character is a single quotation mark or a hyphen 
     * @param str the string to evaluate 
     * @return true if the string is a special character, false otherwise 
     * 
     */
    private boolean isSpecial(String str) 
    {
        return (str.toLowerCase().equals("'") || str.toLowerCase().equals("-")) ;
    }

    /** Determines if a string is a sentence terminator 
     * A sentence terminator is a period, question mark, or exclamation mark. 
     * @param str the string to evaluate 
     * @return true if the string is a letter, false otherwise 
     * 
     */
    private boolean isSentenceTerminator(String str) 
    {
        return (str.toLowerCase().equals(".") || str.toLowerCase().equals("?")
                || str.toLowerCase().equals("!")) ;
    }

    /** Determines if a string is a phrase terminator 
     * A phrase terminator is a comma, a semicolon, or colon 
     * @param str the string to evaluate 
     * @return true if the string is a phrase terminator , false otherwise 
     * 
     */
    private boolean isPhraseTerminator(String str) 
    {
        return (str.toLowerCase().equals(",") || str.toLowerCase().equals(";")
                || str.toLowerCase().equals(":")) ;
    }

    /** Determines if a string is blank space 
     * @param str the string to evaluate 
     * @return true if the string is blank space, false otherwise 
     * 
     */
    private boolean isSpace(String str) 
    {
        String temp = " \r\n";
        int index = temp.indexOf(str.toLowerCase());
        return index != -1; 
    }

    /** Determines if the program is at the end of the file
     * @return true if the program is at end of file, false otherwise 
     */
    public boolean hasNextToken()
    {
        return !(endOfFile) ; 
    }

    /** Returns the next token in the input stream 
     * @return the next token in the input
     * 
     */
    public Token nextToken()
    {
        String str = ""; 
        Token t; 
        while(hasNextToken() && isSpace(currentChar))
        {
            eat(currentChar);
        }
        if(isLetter(currentChar))
        {
            str+=currentChar ; 
            eat(currentChar);
            while(isLetter(currentChar) || isDigit(currentChar) || isSpecial(currentChar))
            {
                str+=currentChar ; 
                eat(currentChar); 
            }
            t = new Token(TOKEN_TYPE.WORD, str.toLowerCase()) ; 
        } 
        else if (isSentenceTerminator(currentChar))
        {
            str+=currentChar ; 
            eat(currentChar);
            t = new Token(TOKEN_TYPE.END_OF_SENTENCE, str) ; 
        }
        else if (isPhraseTerminator(currentChar))
        {
            str+=currentChar ; 
            eat(currentChar);
            t = new Token(TOKEN_TYPE.END_OF_PHRASE, str) ; 
        }
        else if (isDigit(currentChar))
        {
            str+=currentChar ; 
            eat(currentChar);
            t = new Token(TOKEN_TYPE.DIGIT, str) ; 
        }
        else if (endOfFile) 
        {
            t = new Token(TOKEN_TYPE.END_OF_FILE, str) ; 
        } 
        else
        {
            str+=currentChar;
            eat(currentChar); 
            t = new Token(TOKEN_TYPE.UNKNOWN, str) ; 
        }
        return t; 

    }

}
