/**
 * File: Cell.java
 * Author: Tamsin Rogers
 * Date: 2/24/20
 */
 
import java.awt.Color;											// import the color package
import java.awt.Graphics;										// import the graphics package
import java.util.*; 											// import the util package
	
/* each cell object represents one specific location on the grid
	this stores the state of the cell object (dead or alive) */
public class Cell
{
	private Cell cell;											// initialize the cell object
	private boolean alive;										// initialize the alive boolean (true = alive cell, false dead cell)
	
	/* constructor method, defaults to dead cell */
	public Cell( ) 
	{
		this.alive = false;										// set the state of the cell to dead
	}
	
	/* constructor method, specifies whether the cell is alive (true) or dead (false) */
	public Cell( boolean alive ) 
	{
		if(alive)												// if the cell is alive
		{
			this.alive = true;									// set the boolean to true
		}
				
		else													// if the cell is dead
		{
			this.alive = false;									// set the boolean to false
		}
	}
	
	/* returns whether the Cell is dead or alive */
	public boolean getAlive() 
	{
		return this.alive;										// return the alive boolean that stores the dead/alive state of the cell
	}
	
	/* sets the default Cell state to dead */
	public void reset() 
	{
		this.alive = false;										// set the state of the cell to dead
	}
	
	/* sets the state of the Cell to alive */
	public void setAlive( boolean alive ) 
	{
		if(alive)
		{
			this.alive = true;
		}
		
		if(!alive)
		{
			this.alive = false;
		}
																// set the alive boolean to true
	}
	
	/* returns a string that of alive state of the Cell as a one character string, "0" = alive, " " = dead. 
	this method overrides the default toString method in the Object class */
	public String toString()
	{	
		if(this.alive)											// if the cell is alive
		{
			return "0";
		}
		else													// if the cell is dead
		{
			return " ";
		}
	}
	
	/* draws the Cell on the Graphics object at location x, y with the size scaled by scale
	draws randomly colored ovals for alive cells and white ovals for dead cells */
	public void draw(Graphics g, int x, int y, int scale)
	{
		Random rand = new Random();
		
		if(this.alive==true)											// if the cell is alive
		{
			g.setColor(new Color((int)(Math.random() * 0x1000000)));						
			g.fillOval(x,y,scale, scale);						
		}
		
		else													// if the cell is dead
		{
			g.setColor(Color.white);							// set the color of the oval to white
			g.fillOval(x,y,scale, scale);						// fill the oval in white
		}		
	
		g.drawOval(x, y, scale, scale);							// initialize the graphics oval shape
	
	}
	
	/* updates whether or not the cell is alive in the next time step, given its neighbors in the current time step */
	public void updateState(ArrayList<Cell> neighbors)
	{		
	
		ArrayList<Cell> liveNeighbors = new ArrayList<Cell>();
		
		for (int i=0; i<neighbors.size(); i++)
		{
			if(neighbors.get(i).getAlive())
			{
				liveNeighbors.add(neighbors.get(i));
			}
		}
	
	
		if(alive=true)											// if the cell is alive
		{
			if(liveNeighbors.size() == 2 || liveNeighbors.size() == 3)	// if the cell has 2 or 3 neighbors
			{
				this.alive = true;								// set the cell's state to alive
			}
			else													
			{
				this.alive = false;									// the cell remains dead
			}
		}
		else if(alive=false)									// if the cell is dead
		{
			if(liveNeighbors.size() == 3)							// if the cell has exactly 3 neighbors
			{
				this.alive = true;								// set the cell's state to dead
			}
		}
	}

	/* tests the methods */
	public static void main ( String[] args )						
	{ 
		Cell cell = new Cell();									// create a new cell object
		cell.getAlive();										// is the cell dead or alive?  (gets alive boolean)
		System.out.println(cell.toString());					// print "0" for alive, " " for dead
		cell.reset();											// kills cell
		System.out.println(cell.toString());					// prints " " 
		/*cell.setAlive(true);									// set the cell's state to alive
		System.out.println(cell.toString());					// prints "0"*/

	
	}

}