import java.util.ArrayList;

/** The MyBoundedGrid class creates 2x2 grids that hold objects.
 * 
 * 
 * @author  Caden Lin 
 * @version March 16, 2021 
 * 
 * @param <E> the type of object that is stored in the grid 
 */
public class MyBoundedGrid<E>
{
    private Object[][] elem; 

    /**
     * Constructs an empty MyBoundedGrid with the given dimensions.
     * 
     * @param rows  the number of rows in the grid
     * @param cols  the number of columns in the grid
     */
    public MyBoundedGrid(int rows, int cols)
    {
        elem = new Object[rows][cols] ; 
    }

    /**
     * Determines the number of rows 
     * 
     * @return the number of rows in the grid 
     */
    public int getNumRows()
    {
        return elem.length ; 
    }

    /**
     * Determines the number of columns
     * 
     * @return the number of columns in the grid 
     */
    public int getNumCols()
    {
        return elem[0].length; 
    }

    /**
     * Determines whether a location exists in the grid 
     * 
     * @param loc the location in quesion
     * @return true if loc is valid in the grid; false otherwise 
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0 && 
            loc.getRow() < getNumRows() && loc.getCol() < getNumCols() ; 
    }

    /**
     * Retrieves an element from this grid at a location
     * @precondition loc is a valid location in the grid 
     * 
     * @param loc the location to get the element at 
     * 
     * @return the object at location loc, 
     *         null if the location is unoccupied
     */
    public E get(Location loc)
    {
        return (E) elem[loc.getRow()][loc.getCol()] ; 
    }

    /**
     * Puts an element at location loc on this grid and
     * returns the object previously at that location or
     * null if the location was unoccupied.
     * 
     * @param loc is a valid location in this grid
     * @param obj the object to put in the grid
     * 
     * @return the previous object the location,  
     *          null if the location was unoccupied
     */
    public E put(Location loc, E obj)
    {
        E old = get(loc) ;
        elem[loc.getRow()][loc.getCol()] = obj ; 
        return old; 
    }

    /**
     * Removes an element from this grid at a location and
     * returns the object previously at that location or
     * null if the location was unoccupied.
     * 
     * @param loc is a valid location in this grid
     * 
     * @return the object that was the location,  
     *         null if the location was unoccupied
     */
    public E remove(Location loc)
    {
        E old = get(loc) ; 
        elem[loc.getRow()][loc.getCol()] = null;
        return old ; 
    }

    /**
     * Determines all the occupied locations in this grid.
     * 
     * @return an array list of all the occupied locations in the grid 
     *         
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locations = new ArrayList<Location>() ; 
        for(int i = 0 ; i < getNumRows() ; i++)
            for(int j = 0 ; j < getNumCols() ; j++)
                if (elem[i][j] != null)
                    locations.add(new Location(i,j)); 
        return locations ; 
    }
}