import java.awt.Color;
/**
 * class BLock encapsulates a Block abstraction which can be placed into a Gridworld style grid
 * You are expected to comment this class according to the style guide.
 * @author Caden Lin 
 * @version March 16, 2021 
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    /**
     * constructs a blue block, because blue is the greatest color ever!
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    /** Determines the color of the block 
     * @return the color of the block
     * 
     */
    public Color getColor()
    {
        return color; 
    }

    /** Sets the color of the block
     * @postcondition the color of the block is newColor 
     * @param newColor the new color of the block 
     * 
     */
    public void setColor(Color newColor)
    {
        color = newColor; 
    }

    /** Determines the grid that the block is in 
     * @return the grid that the block is in 
     * 
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid; 
    }

    /** Determines the location of the block 
     * @return the location of the block
     * 
     */
    public Location getLocation()
    {
        return location; 
    }

    /** Removes the block from the grid it is in 
     * @postcondition the block is removed from its grid 
     * 
     */
    public void removeSelfFromGrid()
    {
        grid.remove(location) ; 
        grid = null; 
        location = null; 
    }

    /** Puts the block in a grid 
     * @precondition loc is a valid location in the grid 
     * @postcondition the block is in a grid 
     * @param gr the grid to put the block in 
     * @param loc the location to put the block at 
     * 
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        location = loc; 
        grid = gr; 
        Block old = grid.get(loc) ; 
        if(old != null) 
            old.removeSelfFromGrid() ; 
        grid.put(loc, this) ; 
    }

    /** Moves the block to a new location in the grid 
     * @postcondition the block is in a new location in the grid 
     * @param newLocation a valid location in the grid to move 
     *          the block to 
     *
     */
    public void moveTo(Location newLocation)
    {
        grid.remove(location); 
        putSelfInGrid(grid, newLocation) ; 
    }

    /**
     * Produces a string with the location and color of this block
     * @return a string with the location and color of this block
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}