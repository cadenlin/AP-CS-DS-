/** The Rectangle class creates Rectangles with a width and length. 
 * @author Caden Lin 
 * @version Feb 9 2021
 */
public class Rectangle
{
    private int length;
    private int width;

    /** Constructor for Rectangle objects 
     * @param len the length of the rectangle 
     * @param w the width of the rectangle 
     * 
     */
    public Rectangle(int len, int w)
    {
        length = len;
        width = w;
    }

    /** determines the length of the rectangle 
     * @return the length of the rectangle 
     * 
     */
    public int getLength()
    {
        return length;
    }

    /** determines the width of the rectangle 
     * @return the width of the rectangle 
     * 
     */
    public int getWidth()
    {
        return width;
    }

    /** returns a string output of the dimensions of the rectangle 
     * @return a string with the dimensions of the rectangle 
     * 
     */
    public String toString()
    {
        return length + "x" + width;
    }

    /** determines if two rectangles have equal dimensions 
     * @param rec the rectangle to compare to this one 
     * @return true if the rectangles have the same dimensions, false otherwise 
     * 
     */
    public boolean equals(Rectangle rec)
    {
        return (length==rec.getLength() && width==rec.getWidth());
    }
    
    /** Determines the hashCode of the rectangle 
     * @return the hashCode of the rectangle 
     * 
     */
    public int hashCode()
    {
        return ((length * 17) + (width * 13));   
    }
}