/**
 * File: Grid.java
 * Author: Tamsin Rogers
 * Date: 2/18/20
 */	
 
import java.util.Random;
 
public class Grid
{

	/* the main function */
	public static void main ( String[] args )						
	{ 
		int rows;
		int columns;
		String[][] ranger;
		Random ran = new Random();
		
		if(args.length<1)
		{
			System.out.println("Provide at least one command line argument.");
		}
	
		for(int i = 0; i< args.length; i++)
		{
			System.out.println(args[i]);
		}
		
		rows = Integer.parseInt(args[0]);				//row size
		columns = Integer.parseInt(args[1]);			//column size
		
		ranger = new String[rows][columns];				
		
		for(int i=0; i<rows; i++)
		{
			for (int j=0; j<columns; j++)
			{
				String randomChar = "" + (char)(ran.nextInt(26) + 'a');
				ranger[i][j] = randomChar;
				
				if (j == (columns-1))
				{
					System.out.println(ranger[i][j]);
				}
				else
				{
					System.out.print(ranger[i][j]);
				}
			}
		}
	}
}