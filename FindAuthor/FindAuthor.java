import java.util.*;
import java.io.*;

/**
 * A FindAuthor object can calculate common stats for a document 
 * and then guess the author of the document. 
 *
 * @author Caden Lin 
 * @version May 21, 2021 
 */
public class FindAuthor
{
    /**
     * The main method that chooses a document 
     *
     * @param args arguments from the command line
     * @throws FileNotFoundException if no file is found
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            int ran = (int) (Math.random() * 5) + 1;
            System.out.println("Document number " + ran + " was chosen");
            File file = new File("/Users/cadenlin/Desktop/MysteryText/mystery"
                    + ran + ".txt");
            Document document = new Document(new Scanner(new FileReader(file)));
            guess(document);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");
        }
    }

    /**
     * Takes in a document and prints out the guessed author based on the 
     * value returned by compareStats method
     *
     * @param doc the document whose author is to be guessed
     */
    private static void guess(Document doc)
    {
        float[] mysteryStats = calculateStats(doc);
        Map<String, float[]> authorStats = getAuthorStats();
        float minDiff = (float) Integer.MAX_VALUE;
        String guess = "";
        for (String key : authorStats.keySet())
        {
            float dif = compareStats(mysteryStats, authorStats.get(key));
            if (dif < minDiff)
            {
                minDiff = dif;
                guess = key;
            }
        }
        System.out.println("The guess is " + guess);
    }

    /**
     * Compares the stats of a given document to those of a given author.
     * the weights of the stats are preselected. 
     * @param doc the document whose author is being found
     * @param author the author whose work is being compared to the document
     * @return a float that is smaller the closer the document matches with
     *         the given author.
     */
    private static float compareStats(float[] doc, float[] author)
    {
        float[] weights = new float[6] ; 
        weights[0] = 0 ; 
        weights[1] = 11 ; 
        weights[2] = 33 ; 
        weights[3] = 50 ; 
        weights[4] = 0.4f ; 
        weights[5] = 4 ;
        float totalDif = 0;
        for (int i = 1; i < 6; i++)
        {
            totalDif += (Math.abs(doc[i] - author[i])) * weights[i];
        }
        return totalDif ;
    }

    /**
     * Calculates the stats for a given document:
     *  average word length, type to token ratio,
     *  hypax legomana ratio, average words per sentence,
     * and average sentence complexity,
     *
     * @param doc the document to search 
     * @return a float array with the stats of the document
     */
    private static float[] calculateStats(Document doc)
    {
        doc.parseDocument();
        ArrayList<Sentence> sent = doc.getSentences();
        Set<String> unique = new HashSet<String>();
        Set<String> repeat = new HashSet<String>();
        int totalLength,  totalWords,  totalPhrases,  totalSent; 
        totalLength=0 ; 
        totalWords= 0 ;
        totalPhrases= 0 ; 
        totalSent = 0 ;
        for (Sentence sen : sent)
        {
            for (Phrase phrase : sen.getSentence())
            {
                for (Token token : phrase.getPhrase())
                {
                    if (token.getType().equals(Scanner.TOKEN_TYPE.WORD))
                    {
                        if (!unique.add(token.toString().toLowerCase()))
                        {
                            repeat.add(token.toString().toLowerCase());
                        }
                        totalLength += token.toString().length();
                        totalWords++;
                    }
                }
                totalPhrases++;
            }
            totalSent++;
        }
        float averageLength = (float) totalLength / totalWords;
        float typeTokenRatio = (float) unique.size() / totalWords;
        float hapaxLegomanaRatio = (float) (unique.size() - repeat.size()) / totalWords;
        float averageWordsPerSentence = (float) totalWords / totalSent;
        float averagePhrasesPerSentence = (float) totalPhrases / totalSent;
        float[] stats = new float[6] ; 
        stats[0] = 0 ; 
        stats[1] = averageLength ; 
        stats[2] = typeTokenRatio ; 
        stats[3] = hapaxLegomanaRatio ; 
        stats[4] = averageWordsPerSentence ; 
        stats[5] = averagePhrasesPerSentence ; 
        return stats;
    }

    /**
     * Returns a map whose keys are the names
     * of the authors and whose values are their stats.
     *
     * @return a map containing known authors and their stats
     */
    private static Map<String, float[]> getAuthorStats()
    {
        File[] authorFiles = new File("/Users/cadenlin/Desktop/SignatureFiles").listFiles();
        BufferedReader reader;
        Map<String, float[]> stats = new HashMap<String, float[]>();
        for (File file : authorFiles)
        {
            try
            {
                reader = new BufferedReader(new FileReader(file));
                stats.put(reader.readLine(), new float[] {0,
                        Float.parseFloat(reader.readLine()),
                        Float.parseFloat(reader.readLine()),
                        Float.parseFloat(reader.readLine()),
                        Float.parseFloat(reader.readLine()),
                        Float.parseFloat(reader.readLine()), });
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found");
            }
            catch (IOException e)
            {
                System.out.println("IO exception");
            }
        }
        return stats;
    }
}
