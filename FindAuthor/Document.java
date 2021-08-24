import java.util.ArrayList;
/**
 * A Document object represents a text document. 
 *
 * @author Caden Lin 
 * @version May 21, 2021 
 */
public class Document
{
    private ArrayList<Sentence> document; 
    private Scanner scanner; 
    private Token currentToken ; 

    /**
     * Constructor for objects of class Sentence
     * @param s the scanner for this document 
     */
    public Document(Scanner s)
    {
        document = new ArrayList<Sentence>() ; 
        scanner = s ; 
    }

    /**
     * Adds a sentence to the document 
     *
     * @param  s the sentence to add 
     */
    public void addSentence(Sentence s)
    {
        document.add(s) ; 
    }

    /** Gets the document in the form of a list of sentences 
     * @return an arraylist with the sentences of the document 
     * 
     */
    public ArrayList<Sentence> getDocument()
    {
        ArrayList<Sentence> newDocument = new ArrayList<Sentence>();
        for(Sentence s : document)
            newDocument.add(s) ; 
        return newDocument ; 
    }

    /** Converts the document into a string 
     * @return a string with the sentences of the document, separated by a comma 
     * 
     */
    public String toString()
    {
        String str = "";
        for(int i = 0 ; i < document.size() ; i++)
            str += document.get(i).toString() + ", " ; 
        return str; 
    }

    /** Moves onto the next token in the document 
     * @postcondition the currentToken is changed to the next token 
     * 
     */
    public void getNextToken() 
    {
        currentToken =  scanner.nextToken() ; 
    }

    /** Removes the current token and changes the current token 
     * to the next token 
     * @postcondition the current token is changed to the next token 
     * @param t the token to eat (remove) 
     * 
     */
    public void eat(Token t ) 
    {
        if(t == currentToken || t.equals(currentToken)) 
            getNextToken() ; 
        else
            throw new RuntimeException() ;
    }

    /** Adds words to the current phrase 
     * @return a phrase with the next word 
     * 
     */
    public Phrase parsePhrase() 
    {
        Phrase p = new Phrase();
        while (scanner.hasNextToken() &&
                !currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE) &&
                !currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE))
        {
            p.addToken(currentToken);
            eat(currentToken);
        }
        return p;

    }

    /** Adds phrases to the sentence until the sentence ends 
     * @return a sentence with the phrases of the sentence 
     * 
     */
    public Sentence parseSentence()
    {
        Sentence s = new Sentence() ; 
        while(scanner.hasNextToken() && ! 
                (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE)))
        {
            Phrase phrase = parsePhrase() ; 
            if(phrase.getPhrase().size() > 0)
                s.addPhrase(phrase) ; 
            if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE))
            {
                eat(currentToken);
                return s;
            }
            eat(currentToken) ; 
        }
        return s;
    }

    /** Parses sentences from the document until the end of the file 
     * 
     */
    public void parseDocument()
    {
        eat(currentToken);
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.WORD))
            eat(currentToken);
        while (scanner.hasNextToken())
        {            
            while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.WORD))
                eat(currentToken);
            Sentence s = parseSentence();
            if (s.getSentence().size() > 0)
                document.add(s);              
        }          
    }

    /** Returns a list of the sentences of the document
     * @return an arraylist of the sentences of the document 
     * 
     */
    public ArrayList<Sentence> getSentences()
    {
        return document ; 
    }

}
