import java.util.ArrayList;
/**
 * A Sentence object represents a sentence of a document. 
 *
 * @author Caden Lin 
 * @version May 21, 2021 
 */
public class Sentence
{
    private ArrayList<Phrase> sentence; 

    /**
     * Constructor for objects of class Sentence
     */
    public Sentence()
    {
        sentence = new ArrayList<Phrase>() ; 
    }

    /**
     * Adds a phrase to the sentence 
     * @postcondition a phrase has been added to the sentence 
     * @param p the phrase to add to the sentence 
     *
     */
    public void addPhrase(Phrase p)
    {
        sentence.add(p) ; 
    }

    /** Produces a list of the phrases in the sentence 
     * @return an arraylist of the phrases in the sentence 
     * 
     */
    public ArrayList<Phrase> getSentence()
    {
        ArrayList<Phrase> newSentence = new ArrayList<Phrase>();
        for(Phrase t : sentence)
            newSentence.add(t) ; 
        return newSentence ; 
    }

    /** Converts the sentence into a string 
     * @return a string form of the sentence 
     * 
     */
    public String toString()
    {
        String str = "";
        for(int i = 0 ; i < sentence.size() ; i++)
            str += sentence.get(i); 
        return str; 
    }
}
