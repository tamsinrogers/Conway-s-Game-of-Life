/**
 * File: Landscape.java
 * Author: Tamsin Rogers
 * Date: 2/24/20
 */	
 
import java.util.*; 														// import the util package
import java.awt.Graphics;													// import the graphics package

/* holds a 2d grid of references to cell objects */
public class Landscape
{
	private int rows;														// initialize a variable to store the number of rows in the 2d array
	private int cols;														// initialize a variable to store the number of columns in the 2d array
	private Cell[][] landscape;												// initialize the landscape 2d array
	private Cell[][] grid;													// initialize the grid 2d array
	private int i;															// initialize the counter i
	private int j;															// initialize the counter j

	/* sets the number of rows and columns to the given values, sets up the grid of Cell references, allocates a cell object each Grid location */
	public Landscape( int rows, int cols ) 
	{
		this.rows = rows;													// initialize the rows variable
		this.cols = cols;													// initialize the columns variable
		landscape = new Cell[rows][cols];									// creates a new grid of Cell references named landscape
		
		for(int i=0; i<rows; i++)											// run through the grid
		{
			for (int j=0; j<cols; j++)
			{
				Cell cell = new Cell(false);								// set all of the cells in the grid to be dead	
				landscape[i][j] = cell;
				cell.setAlive(false);
			}
		}
	}

	/* calls the reset method for each cell */
	public void reset() 
	{
		for(int i=0; i<rows; i++)											//run through the grid
		{
			for (int j=0; j<cols; j++)
			{
				Cell cell = new Cell(false);								// create a new, dead cell object in the grid
				landscape[i][j] =  cell;
				cell.setAlive(false);
			}
		}
	}
	
	/* returns the number of rows in the Landscape */
	public int getRows() 
	{
		return this.rows;													// return the number of rows
	}
	
	/* returns the number of columns in the Landscape */
	public int getCols() 
	{	
		return this.cols;													// return the number of columns
	}
	
	/* returns a reference to the Cell located at position (row, col) */
	public Cell getCell( int row, int col ) 
	{
		return this.landscape[row][col];												// return the grid cell located at the specified position
	}
	
	/* converts the Landscape into a string representation. */
	public String toString() 
	{
		String s = "";
		
		for(int i=0; i<rows; i++)											// run through the grid
		{
			for (int j=0; j<cols; j++)
			{
				s += " " + landscape[i][j];									// spaces out the cells
			}
			
			s+= "\n";														// separates cells into rows
		}
		
		return s;
	}
	
	/* returns a list of references cells surrounding a given cell */
	public ArrayList<Cell> getNeighbors( int row, int col ) 
	{
		ArrayList<Cell> neighbors = new ArrayList<Cell>();					// create a new ArrayList to store neighboring cells
		int numberOfRows = landscape.length;								// save the number of rows in the grid
		int numberOfCols = landscape[0].length;								// save the number of columns in the grid
		
		if (row>0 && row<numberOfRows-1 && col>0 && col<numberOfCols-1)	//inner cells
		{
			//System.out.println("case 1");
			neighbors.add(landscape[row-1][col]);							// directly above
			neighbors.add(landscape[row+1][col]);							// directly below
			neighbors.add(landscape[row][col-1]);							// directly left
			neighbors.add(landscape[row][col+1]);							// directly right
			neighbors.add(landscape[row-1][col-1]);							// top left corner
			neighbors.add(landscape[row-1][col+1]);							// top right corner
			neighbors.add(landscape[row+1][col-1]);							// bottom left corner
			neighbors.add(landscape[row+1][col+1]);							// bottom right corner
			return neighbors;
		}
		else if(row==0 && col>0 && col<numberOfCols-1)						// top row of cells, excluding top left/right corner
		{
			//System.out.println("case 2");
			neighbors.add(landscape[row+1][col]);							// directly below
			neighbors.add(landscape[row][col-1]);							// directly left
			neighbors.add(landscape[row][col+1]);							// directly right
			neighbors.add(landscape[row+1][col-1]);							// bottom left corner
			neighbors.add(landscape[row+1][col+1]);							// bottom right corner
			return neighbors;
		}
		else if(row==numberOfRows-1 && col>0 && col<numberOfCols-1)			// bottom row of cells, excluding bottom left/right corners
		{
			//System.out.println("case 3");
			neighbors.add(landscape[row-1][col]);							// directly above
			neighbors.add(landscape[row][col-1]);							// directly left
			neighbors.add(landscape[row][col+1]);							// directly right
			neighbors.add(landscape[row-1][col-1]);							// top left corner
			neighbors.add(landscape[row-1][col+1]);							// top right corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else if(col==0 && row>0 && row<numberOfRows-1)						// cells in the left column, excluding top/bottom left corner
		{
			//System.out.println("case 4");
			neighbors.add(landscape[row-1][col]);							// directly above
			neighbors.add(landscape[row+1][col]);							// directly below
			neighbors.add(landscape[row-1][col+1]);							// top right corner
			neighbors.add(landscape[row+1][col+1]);							// bottom right corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else if(col==numberOfCols-1 && row>0 && row<numberOfRows-1)			// cells in the right column, excluding top/bottom right corner
		{
			//System.out.println("case 5");
			neighbors.add(landscape[row-1][col]);							// directly above
			neighbors.add(landscape[row+1][col]);							// directly below
			neighbors.add(landscape[row][col-1]);							// directly left
			neighbors.add(landscape[row-1][col-1]);							// top left corner
			neighbors.add(landscape[row+1][col-1]);							// bottom left corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else if(col==0 && row==0)											// the top left corner cell
		{
			//System.out.println("case 6");
			neighbors.add(landscape[row+1][col]);							// directly below
			neighbors.add(landscape[row][col+1]);							// directly right
			neighbors.add(landscape[row+1][col+1]);							// bottom right corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else if(row==0 && col>0 && col==numberOfCols-1)						// the top right corner cell
		{
			//System.out.println("case 7");
			neighbors.add(landscape[row+1][col]);							// directly below
			neighbors.add(landscape[row][col-1]);							// directly left
			neighbors.add(landscape[row+1][col-1]);							// bottom left corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else if(col==0 && row==numberOfRows-1)								//the bottom left corner cell
		{
			//System.out.println("case 8");
			neighbors.add(landscape[row-1][col]);							// directly above
			neighbors.add(landscape[row][col+1]);							// directly right
			neighbors.add(landscape[row-1][col+1]);							// top right corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else if(row==numberOfRows-1 && col>0 && col==numberOfCols-1)		// the bottom right corner cell
		{
			//System.out.println("case 9");
			neighbors.add(landscape[row-1][col]);							// directly above
			neighbors.add(landscape[row][col-1]);							// directly left
			neighbors.add(landscape[row-1][col-1]);							// top left corner
			return neighbors;												// return the ArrayList of neighboring cells
		}
		else
		{
			//System.out.println("case 10 (error)");
			return neighbors;
		}
	}
	
	/* loops through the cells in the landscape grid and draws each of them using the cell draw method */
	public void draw( Graphics g, int gridScale )
	{
		for(int i=0; i<rows; i++)											// run through the grid
		{
			for (int j=0; j<cols; j++)
			{
				landscape[i][j].draw(g, i*gridScale, j*gridScale, gridScale);
			}
		}
	}
	
	/* moves all of the cells in the grid forward by one generation by updating their dead/alive statuses */
	public void advance()
	{
		grid = new Cell[rows][cols];		// create a temporary cell grid of the same size
			
		for(int i=0; i<rows; i++)											// run through the grid
		{
			for (int j=0; j<cols; j++)
			{
				boolean status = this.landscape[i][j].getAlive();
				Cell newCell = new Cell(status);		
				newCell.updateState(this.getNeighbors(i,j));
				grid[i][j] = newCell;	
			}
		}
		this.landscape = grid;
	}
	
	public void pattern()
	{
		for(int i=0; i<rows; i++)											//run through the grid
		{
			for(int j=0; j<cols; j++)
			{
				Cell cell = new Cell(true);								// create a new, dead cell object in the grid
				landscape[(i/100)+50][j] =  cell;
			}
		}
	}
	
	/* tests the methods */
	public static void main ( String[] args )			
	{ 
		Landscape theLandscape = new Landscape(10,10);
		
		System.out.println("original: ");
		System.out.println(theLandscape.toString());
		
		System.out.println("number of rows: " + theLandscape.getRows());
		System.out.println("number of columns: " + theLandscape.getCols());
		System.out.println("get cell at (4,4): " + theLandscape.getCell(4,4));
		
		System.out.println("neighbors case 1: " + theLandscape.getNeighbors(4,4));
		System.out.println("neighbors case 2: " + theLandscape.getNeighbors(0,4));
		System.out.println("neighbors case 3: " + theLandscape.getNeighbors(9,4));
		System.out.println("neighbors case 4: " + theLandscape.getNeighbors(4,0));
		System.out.println("neighbors case 5: " + theLandscape.getNeighbors(4,9));
		System.out.println("neighbors case 6: " + theLandscape.getNeighbors(0,0));
		System.out.println("neighbors case 7: " + theLandscape.getNeighbors(0,9));
		System.out.println("neighbors case 8: " + theLandscape.getNeighbors(9,0));
		System.out.println("neighbors case 9: " + theLandscape.getNeighbors(9,9));
		
		System.out.println("reset: ");
		theLandscape.reset();
		System.out.println(theLandscape.toString());
	}
}