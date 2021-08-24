import java.io.*;
/**
 * Tests the Scanner Class 
 *
 * @author Caden Lin 
 * @version May 21, 2021 
 */
public class ScannerTester
{
    /**
     *  Main tester method 
     *
     * @param  str array of String objects 
     */
    public static void main(String[] str) throws FileNotFoundException
    {
        FileReader reader = new FileReader(new File("/Users/cadenlin/Desktop//MysteryText/mystery1.txt"));
        Scanner scanner = new Scanner(reader);
        while(scanner.hasNextToken())
        {
            System.out.println(scanner.nextToken().toString());
        }
    }
}
