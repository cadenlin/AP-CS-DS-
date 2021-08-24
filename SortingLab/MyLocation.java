/**
 * The MyLocation class defines a MyLocation object which has a 
 * given number of rows and columns. 
 * @author Caden Lin 
 * @version March 9, 2021
 */
public class MyLocation implements Comparable
{
    private int row;
    private int col;

    /**
     * Constructor: MyLocation()
     * Usage: MyLocation loc = new MyLocation(row, col);
     * -----------------------------
     * creates a MyLocation object with the given row & col
     * 
     * @param r - row of this MyLocation
     * @param c - column of this MyLocaiton
     */
    public MyLocation(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * Method: getRow()
     * Usage: determines the number of rows 
     * @return the number of rows in the object  
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Method: getCol()
     * Usage: determines the number of columns
     * @return the number of columns in the object 
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Method: equals()
     * Usage: determines if two MyLocation objects are equal 
     * @return true if the two objects have the same number 
     * of rows and columns, false otherwise
     * @param other the other MyLocation object to compare to 
     */
    public boolean equals(Object other)
    {
        MyLocation x = (MyLocation) other;
        if(row == x.getRow() && col == x.getCol())
            return true;
        return false; 
    }

    /**
     * Method: toString()
     * Usage: prints the object as a string 
     * @return a string with the number of rows and columns 
     * of the object, separated by a comma, surrounded by brackets
     * 
     */
    public String toString()
    {
        return "[" + row + ", " + col + "]";
    }

    /**
     * Method: compareTo()
     * Usage: compares two MyLocation objects 
     * A MyLocation object is less than another MyLocation object 
     * if it has fewer rows, or if they have the same number of rows, 
     * if it has fewer columns. Two MyLocation objects are equal 
     * if they have the same number of rows and columns. Otherwise, 
     * the MyLocation object is greater than the other MyLocation object. 
     * @return -1 if this object is less than the other object, 
     * 0 if the objects are equal, 1 if this object is greater 
     * @param x the other MyLocation object to compare to 
     */
    public int compareTo(Object x)
    {
        MyLocation y = (MyLocation) x; 
        if(getRow() < y.getRow() ) 
            return -1; 
        if(getRow() == y.getRow())
        {
            if(getCol() < y.getCol() )
                return -1;
            if (getCol() == y.getCol())
                return 0;
            return 1;
        }
        return 1; 
    }
}